package fr.codebusters.valocb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Service pour lire et charger les informations sur les produits et clients
 * depuis Product.csv.
 */
public class ProductService {

    /**
     * Charge les informations des produits et clients à partir du fichier
     * Product.csv.
     *
     * @return Une liste de ProductClient contenant les informations sur les
     *         produits et clients.
     */
    public static List<ProductClient> loadProducts() {
        List<ProductClient> products = new ArrayList<>();

        try (InputStream inputStream = ProductService.class.getClassLoader().getResourceAsStream("Product.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String product = tokens[0].trim();
                String client = tokens[1].trim();
                int quantity = Integer.parseInt(tokens[2].trim());

                products.add(new ProductClient(product, client, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
