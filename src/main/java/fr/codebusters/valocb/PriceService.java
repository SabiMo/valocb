package fr.codebusters.valocb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Service pour lire et charger les prix depuis le fichier Prices.csv.
 */
public class PriceService {

    /**
     * Charge les prix des produits à partir du fichier Prices.csv.
     *
     * @return Une liste de ProductPrice contenant les informations sur les prix.
     */
    public static List<ProductPrice> loadPrices() {
        List<ProductPrice> prices = new ArrayList<>();

        try (InputStream inputStream = PriceService.class.getClassLoader().getResourceAsStream("Prices.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String portfolio = tokens[0].trim();
                String product = tokens[1].trim();
                String underlying = tokens[2].trim();
                String currency = tokens[3].trim();
                double price = Double.parseDouble(tokens[4].trim());

                prices.add(new ProductPrice(portfolio, product, underlying, currency, price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prices;
    }
}
