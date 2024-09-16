package fr.codebusters.valocb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Service de conversion des devises pour obtenir le taux de change
 * vers EUR.
 */
public class ForexService {

    private static ForexService instance;
    private Map<String, Double> forexRates = new HashMap<>();

    private ForexService() {
        loadForexRates();
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
    private void loadForexRates() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Forex.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            reader.readLine();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fromCurrency La devise source.
     * @return Le taux de conversion vers EUR.
     */
    public double getConversionRate(String fromCurrency) {
        if (fromCurrency.equals("EUR")) {
            return 1.0;
        }
        return forexRates.getOrDefault(fromCurrency, 1.0);
    }
}
