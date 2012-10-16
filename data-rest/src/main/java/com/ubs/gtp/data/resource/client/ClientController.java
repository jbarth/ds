package com.ubs.gtp.data.resource.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Optional;
import com.ubs.gtp.data.domain.client.ClientDto;
import com.ubs.gtp.data.domain.dao.AdvisorDao;
import com.ubs.gtp.data.domain.dao.ClientDao;

/**
 * Controller for the client resource.
 *
 * @author Joshua Barth
 * @author Mihkel Aamer
 * @author Jakub D Kozlowski
 * @since 0.3
 */
@Controller
@Transactional
@Repository
public class ClientController {

    @Autowired
    private ClientDao clients;

    @Autowired
    private AdvisorDao advisors;

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getClientInfo(final @PathVariable int id, final HttpServletRequest request, final HttpServletResponse response) {
        final Optional<ClientDto> client = clients.getById(id);
        if (!client.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView();
        } else {
            return new ModelAndView("client", "client", client.get());
        }
    }

    @RequestMapping(value = "/faClient/{faId}", method = RequestMethod.GET)
    public ModelAndView getFAClients(final @PathVariable Integer faId, final HttpServletResponse response) {
        final Optional<List<ClientDto>> clients = advisors.getClientsByAdvisorId(faId);
        if (!clients.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView();
        } else {
            List<Integer> ids = new ArrayList<Integer>();
            for (ClientDto c : clients.get()) {
                ids.add(c.getClientId());
            }
            return new ModelAndView("clients", "clients", ids);
        }
    }

}
