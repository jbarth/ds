package com.ubs.gtp.data.jsonp;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Wraps {@code application/javascript} requests in a callback.
 *
 * @author Jakub D Kozlowski
 * @see <a href="http://www.mkyong.com/spring/spring-quartz-scheduler-example/">Spring Quartz Example</a>
 * @since 0.1
 */
public final class MappingJacksonJsonpView extends MappingJacksonJsonView {

    /**
     * Default constructor.
     */
    public MappingJacksonJsonpView() {
    }

    public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

    public static final String DEFAULT_CALLBACK_PARAMETER_NAME = "callback";

    public static final byte[] DEFAULT_FOOTER = ");".getBytes();


    /**
     * Prepares the view given the specified model, wrapping it with a javascript call to a function provided in the
     * {@link MappingJacksonJsonpView#DEFAULT_CALLBACK_PARAMETER_NAME} parameter and sets the content type to
     * {@link MappingJacksonJsonpView#DEFAULT_CONTENT_TYPE}. The actual rendering is delegate to the superclass.
     */
    @Override
    public void render(final Map<String, ?> model, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        @SuppressWarnings("unchecked")
        final Map<String, String[]> params = request.getParameterMap();

        if (RequestMethod.GET.name().equals(request.getMethod()) &&
                params.containsKey(DEFAULT_CALLBACK_PARAMETER_NAME) &&
                1 == params.get(DEFAULT_CALLBACK_PARAMETER_NAME).length) {

            final StringBuilder b = new StringBuilder(params.get(DEFAULT_CALLBACK_PARAMETER_NAME)[0]).append("(");
            final byte[] header = b.toString().getBytes();

            response.getOutputStream().write(header);
            super.render(model, request, response);
            response.getOutputStream().write(DEFAULT_FOOTER);
            response.setContentType(DEFAULT_CONTENT_TYPE);
        } else {
            super.render(model, request, response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentType() {
        return DEFAULT_CONTENT_TYPE;
    }
}