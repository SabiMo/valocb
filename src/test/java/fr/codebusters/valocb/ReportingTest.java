package fr.codebusters.valocb;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportingTest {

    @Test
    public void testGeneratePortfolioReport() throws IOException {

        File portfolioReport = new File("reporting/Reporting-portfolio.csv");

        // Vérifier que le fichier a été généré
        assertTrue(portfolioReport.exists(), "Le fichier Reporting-portfolio.csv n'a pas été généré.");

        try (BufferedReader reader = new BufferedReader(new FileReader(portfolioReport))) {
            // Vérifier l'en-tête
            String header = reader.readLine();
            assertEquals("PTF,price", header, "L'en-tête du fichier est incorrect.");

            // Vérifier les données
            String[] expectedLines = {
                    "PTF2,990.0",
                    "PTF1,475.5"
            };
            for (String expectedLine : expectedLines) {
                String actualLine = reader.readLine();
                assertEquals(expectedLine, actualLine, "Les données du portfolio sont incorrectes.");
            }

            assertEquals(null, reader.readLine());
        }
    }

    @Test
    public void testGenerateClientReport() throws IOException {

        File clientReport = new File("reporting/Reporting-client.csv");

        // Vérifier que le fichier a été généré
        assertTrue(clientReport.exists(), "Le fichier Reporting-client.csv n'a pas été généré.");

        try (BufferedReader reader = new BufferedReader(new FileReader(clientReport))) {
            // Vérifier l'en-tête
            String header = reader.readLine();
            assertEquals("Client,capital", header, "L'en-tête du fichier est incorrect.");

            // Vérifier les données
            String[] expectedLines = {
                    "C4,350.0",
                    "C5,87610.0",
                    "C6,13500.0",
                    "C7,34400.0",
                    "C8,57600.0",
                    "C1,990.0",
                    "C2,64600.0",
                    "C3,36250.0"
            };

            for (String expectedLine : expectedLines) {
                String actualLine = reader.readLine();
                assertEquals(expectedLine, actualLine, "Les données du client sont incorrectes.");
            }

            assertEquals(null, reader.readLine());
        }
    }
}
