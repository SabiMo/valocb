package fr.codebusters.valocb.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import fr.codebusters.valocb.exceptions.BadFileContentException;

/**
 * Service de conversion des devises pour obtenir le taux de change
 * vers EUR.
 */
public class ForexService {

    private static ForexService instance;
    private Map<String, Double> forexRates = new HashMap<>();

    /**
     * Constructeur privé.
     */
    private ForexService() {
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
    public static synchronized ForexService getInstance() {
        if (instance == null) {
            instance = new ForexService();
        }
        return instance;
    }

    /**
     * Charge les taux de change à partir du fichier forex.csv.
     * Les taux sont stockés dans une Map (devise source, taux de conversion vers
     * EUR).
     */
    private void loadForexRates() throws IOException, BadFileContentException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Forex.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = reader.readLine();

        if (line.split(",").length != 3) {
            throw new BadFileContentException("Le fichier ne contient pas le nombre attendu de colonnes");
        }

        forexRates.put("EUR", 1.0);
        while ((line = reader.readLine()) != null) {
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

    }

    /**
     * @param fromCurrency La devise source.
     * @return Le taux de conversion vers EUR.
     */
    public double getConversionRate(String fromCurrency) {
        return forexRates.get(fromCurrency);
    }
}
