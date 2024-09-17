package fr.codebusters.valocb;

import java.io.IOException;
import java.util.Set;

import fr.codebusters.valocb.entities.Client;
import fr.codebusters.valocb.entities.Portfolio;
import fr.codebusters.valocb.parsers.PriceService;
import fr.codebusters.valocb.reporting.Reporting;

/**
 * Point d'entrée de l'application.
 */
public class Main {

    /**
     * Méthode qui lance l'application.
     */
    public static void main(String[] args) throws IOException {
        PriceService priceService = PriceService.getInstance();
        Set<Portfolio> portfolios = priceService.getPortfolios();
        Set<Client> clients = priceService.getClients();

        Reporting.generatePortfolioReport(portfolios);
        Reporting.generateClientReport(clients);
    }
}
