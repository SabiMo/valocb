package fr.codebusters.valocb;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.IOException;
import java.util.Set;

import fr.codebusters.valocb.entities.Client;
import fr.codebusters.valocb.entities.Portfolio;
import fr.codebusters.valocb.exceptions.BadArgumentsException;
import fr.codebusters.valocb.parsers.ForexService;
import fr.codebusters.valocb.parsers.PriceService;
import fr.codebusters.valocb.reporting.Reporting;

/**
 * Point d'entrée de l'application.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Méthode qui vérifie et récupère les bons arguments.
     */
    public static String[] getArguments(String[] args) throws BadArgumentsException {
        System.out.println(args.length);
        for (String s : args) {
            System.out.println(s);
        }
        if (args.length != 4) {
            throw new BadArgumentsException(
                    "Usage: java -jar valocb-1.0-SNAPSHOT.jar -i <input_directory> -o <output_directory>");
        }

        String[] dir = new String[2];

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    dir[0] = args[i + 1]; // input
                    i++;
                    break;
                case "-o":
                    dir[1] = args[i + 1]; // output
                    i++;
                    break;
                default:
                    throw new BadArgumentsException(
                            "Usage: java -jar valocb-1.0-SNAPSHOT.jar -i <input_directory> -o <output_directory>");
            }
        }
        return dir;
    }

    /**
     * Méthode qui lance l'application.
     */
    public static void main(String[] args) throws IOException, BadArgumentsException {
        logger.setLevel(Level.ALL);
        logger.info("Lancement de l'application.");

        String[] dir = getArguments(args);
        String inputDirectory = dir[0];
        String outputDirectory = dir[1];

        logger.info("Récupération des portfolios et clients.");

        PriceService priceService = PriceService.getInstance(inputDirectory);
        Set<Portfolio> portfolios = priceService.getPortfolios();
        Set<Client> clients = priceService.getClients();

        logger.info("Génération des raports.");

        ForexService forexService = ForexService.getInstance(inputDirectory);
        Reporting.generatePortfolioReport(portfolios, forexService, outputDirectory);
        Reporting.generateClientReport(clients, forexService, outputDirectory);

        logger.info("Fin de l'application.");
    }
}
