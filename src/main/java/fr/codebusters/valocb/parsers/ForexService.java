package fr.codebusters.valocb.parsers;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fr.codebusters.valocb.exceptions.BadFileContentException;

/**
 * Service de conversion des devises pour obtenir le taux de change
 * vers EUR.
 */
public class ForexService {
    private static final Logger logger = Logger.getLogger(PriceService.class.getName());

    private String path;
    private static ForexService instance;
    private Map<String, Double> forexRates = new HashMap<>();

    /**
     * Constructeur privé.
     */
    private ForexService(String path) {
        this.path = path;
        try {
            loadForexRates();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadFileContentException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return L'instance unique de ForexService.
     */
    public static ForexService getInstance(String path) {
        if (instance == null) {
            instance = new ForexService(path);
        }
        return instance;
    }

    /**
     * Charge les taux de change à partir du fichier forex.csv.
     * Les taux sont stockés dans une Map (devise source, taux de conversion vers
     * EUR).
     */
    private void loadForexRates() throws IOException, BadFileContentException {
        logger.setLevel(Level.ALL);
        logger.info("Lecture du fichier" + path + "/Forex.csv.");

        String file = path + "/Forex.csv";
        BufferedReader reader = new BufferedReader(new FileReader(file));

        logger.info("Lecture réussite.");

        String line = reader.readLine();

        if (line.split(",").length != 3) {
            reader.close();
            throw new BadFileContentException("Le fichier ne contient pas le nombre attendu de colonnes");
        }

        forexRates.put("EUR", 1.0);
        while ((line = reader.readLine()) != null) {
            if (line.split(",").length != 3) {
                reader.close();
                throw new BadFileContentException("Le fichier ne contient pas le nombre attendu de colonnes");
            }
            String[] tokens = line.split(",");
            String fromCurrency = tokens[0].trim();
            String toCurrency = tokens[1].trim();
            double rate = Double.parseDouble(tokens[2].trim());

            if (toCurrency.equals("EUR")) {
                forexRates.put(fromCurrency, rate);
            } else if (fromCurrency.equals("EUR")) {
                forexRates.put(toCurrency, 1 / rate);
            }
        }

        reader.close();
    }

    /**
     * @param fromCurrency La devise source.
     * @return Le taux de conversion vers EUR.
     */
    public double getConversionRate(String fromCurrency) {
        return forexRates.get(fromCurrency);
    }
}
