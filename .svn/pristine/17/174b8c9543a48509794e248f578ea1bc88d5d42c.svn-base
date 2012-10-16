package com.ubs.gtp.data.resource.portfolio;


import java.math.BigDecimal;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ubs.gtp.data.domain.client.*;
import com.ubs.gtp.data.domain.dao.ClientDao;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is the controller for returning portfolio URLs
 *
 * @author Joshua Barth
 * @author Mihkel Aamer
 * @version since 0.3
 */

@Controller
@Transactional
@Repository
public class PortfolioController {

    @Autowired
    private ClientDao clients;

    /**
     * Gets portfolios for this {@code clientId}.
     *
     * @param clientId id of the client.
     * @return portfolios for this {@code clientId}.
     */
    @RequestMapping(value = "/portfolio/{clientId}", method = RequestMethod.GET)
    public ModelAndView getPortfolios(final @PathVariable int clientId, final HttpServletResponse response) {
        final Optional<ClientDto> client = clients.getById(clientId);
        if (!client.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ModelAndView();
        } else {
        	// Calculate portfolio values
        	BigDecimal value;
        	PortfolioDto[] portfolios = client.get().getPortfolios().toArray(new PortfolioDto[0]);
        	for (int i=0; i<portfolios.length; i++)
        	{
        		value = new BigDecimal(0);
        		PortfolioItemDto[] items =  portfolios[i].getItems().toArray(new PortfolioItemDto[0]);
        		for (int j=0; j<items.length; j++)
        		{        			
        			value = value.add(new BigDecimal(items[j].getVolume()).multiply(items[j].getStock().getPrice()));        			
        		}
        		portfolios[i].setValue(value);
        	}
            return new ModelAndView("portfolios", "portfolios", portfolios);
        }
    }
}
