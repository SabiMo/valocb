package fr.codebusters.valocb.reporting;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import fr.codebusters.valocb.entities.Client;
import fr.codebusters.valocb.entities.Portfolio;
import fr.codebusters.valocb.parsers.ForexService;

/**
 * Classe Reporting pour générer les fichiers Reporting-portfolio.csv et
 * Reporting-client.csv
 */
public class Reporting {

    /**
     * Génère le fichier Reporting-portfolio.csv.
     *
     * @param portfolios Set de portefeuilles.
     * @throws IOException
     */
    public static void generatePortfolioReport(Set<Portfolio> portfolios, ForexService forexService, String path)
            throws IOException {
        try (FileWriter writer = new FileWriter(path + "/Reporting-portfolio.csv")) {
            writer.append("PTF,price\n");

            for (Portfolio portfolio : portfolios) {
                double portfolioPrice = portfolio.price(forexService);
                writer.append(portfolio.getName())
                        .append(",")
                        .append(String.valueOf(portfolioPrice))
                        .append("\n");
            }
        }
    }

    /**
     * Génère le fichier Reporting-client.csv.
     *
     * @param clients Set de clients.
     * @throws IOException
     */
    public static void generateClientReport(Set<Client> clients, ForexService forexService, String path)
            throws IOException {
        FileWriter writer = new FileWriter(path + "/Reporting-client.csv");
        writer.append("Client,capital\n");

        for (Client client : clients) {
            double clientCapital = client.capital(forexService);
            writer.append(client.getName())
                    .append(",")
                    .append(String.valueOf(clientCapital))
                    .append("\n");
        }

        writer.close();
    }
}
