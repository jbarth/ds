package com.ubs.gtp.data.resource;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.OutputSupplier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Rewrites {@code GET} requests sent to it into {@code POST} requests.
 * <p/>
 * <p>{@link PostGateway} makes a request to {@code path}, passes on all the headers
 * and writes {@code json} parameter to the body of the request. When the request is received,
 * the headers and the body are passed back.</p>
 *
 * @author Jakub D Kozlowski
 * @since 0.4
 */
@Controller
@Repository
public class PostGateway {

    @RequestMapping(value = "/post", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getClientInfo(final @RequestParam(value = "json", required = true) String json,
                                final @RequestParam(value = "path", required = true) String path,
                                final @RequestParam(value = "callback", required = true) String callback,
                                final @RequestHeader HttpHeaders headers,
                                final UriComponentsBuilder builder,
                                final HttpServletResponse reply) {

        final RestTemplate template = new RestTemplate();

        final URI absolutePath = builder.path(path).build().toUri();

        final HttpEntity<String> response = template.execute(absolutePath, HttpMethod.POST, new RequestCallback() {
                    public void doWithRequest(final ClientHttpRequest request) throws IOException {

                        ByteStreams.write(json.getBytes(), new OutputSupplier<OutputStream>() {
                            public OutputStream getOutput() throws IOException {
                                return request.getBody();
                            }
                        });
                        request.getHeaders().putAll(headers);
                    }
                }, new ResponseExtractor<HttpEntity<String>>() {
                    public HttpEntity<String> extractData(final ClientHttpResponse response) throws IOException {
                        reply.setStatus(response.getRawStatusCode());
                        return new HttpEntity<String>(CharStreams.toString(new InputStreamReader(response.getBody())), response.getHeaders());
                    }
                }
        );

        for (final Map.Entry<String, List<String>> e : response.getHeaders().entrySet()) {
            for (final String value : e.getValue()) {
                reply.setHeader(e.getKey(), value);
            }
        }

        return callback + "(" + response.getBody() + ");";
    }
}
