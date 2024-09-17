package fr.codebusters.valocb;

import java.util.logging.Logger;
import java.util.logging.Level;

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
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Méthode qui lance l'application.
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.ALL);
        logger.info("Lancement de l'application.");
        logger.info("Génération des raports.");

        PriceService priceService = PriceService.getInstance();
        Set<Portfolio> portfolios = priceService.getPortfolios();
        Set<Client> clients = priceService.getClients();

        Reporting.generatePortfolioReport(portfolios);
        Reporting.generateClientReport(clients);

        logger.info("Fin de l'application.");
    }
}
