package fr.codebusters.valocb.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import fr.codebusters.valocb.entities.Underlying;
import fr.codebusters.valocb.exceptions.BadFileContentException;
import fr.codebusters.valocb.entities.Client;
import fr.codebusters.valocb.entities.Portfolio;
import fr.codebusters.valocb.entities.Product;

/**
 * Service Singleton pour lire et charger les informations depuis Prices.csv.
 */
public class PriceService {

    private static PriceService instance;
    private Set<Portfolio> portfolios;
    private Set<Client> clients;

    // Constructeur privé
    private PriceService() {
        portfolios = new HashSet<>();
        clients = new HashSet<>();
        try {
            loadPrices();
            loadProducts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadFileContentException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return L'instance unique de PriceService.
     */
    public static synchronized PriceService getInstance() {
        if (instance == null) {
            instance = new PriceService();
        }
        return instance;
    }

    /**
     * Charge les informations des prix des produits à partir du fichier Prices.csv.
     * Les portefeuilles sont stockés dans un Set.
     */
    public void loadPrices() throws IOException, BadFileContentException {
        InputStream inputStream = PriceService.class.getClassLoader().getResourceAsStream("Prices.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = reader.readLine();

        if (line.split(",").length != 5) {
            throw new BadFileContentException("Le fichier ne contient pas le nombre attendu de colonnes");
        }

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            if (tokens.length != 5) {
                throw new BadFileContentException("Le fichier ne contient pas le nombre attendu de colonnes");
            }

            String portfolioName = tokens[0].trim();
            String productName = tokens[1].trim();
            String underlyingName = tokens[2].trim();
            String currency = tokens[3].trim();
            double price = Double.parseDouble(tokens[4].trim());

            Underlying underlying = new Underlying(underlyingName, currency, price);

            // Chercher le portfolio dans le HashSet
            Portfolio existingPortfolio = portfolios.stream()
                    .filter(p -> p.getName().equals(portfolioName))
                    .findFirst()
                    .orElse(null);

            if (existingPortfolio == null) {
                Portfolio newPortfolio = new Portfolio(portfolioName, null);
                Product newProduct = new Product(productName, null);
                newProduct.addUnderlying(underlying);
                newPortfolio.addProduct(newProduct);
                portfolios.add(newPortfolio);
            } else {
                Product existingProduct = existingPortfolio.getProducts().stream()
                        .filter(p -> p.getName().equals(productName))
                        .findFirst()
                        .orElse(null);

                if (existingProduct == null) {
                    Product newProduct = new Product(productName, null);
                    newProduct.addUnderlying(underlying);
                    existingPortfolio.addProduct(newProduct);
                } else {
                    existingProduct.addUnderlying(underlying);
                }
            }
        }

    }

    /**
     * Charge les informations des produits et clients à partir du Product.csv.
     */
    public void loadProducts() throws IOException, BadFileContentException {
        InputStream inputStream = PriceService.class.getClassLoader().getResourceAsStream("Product.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = reader.readLine();

        if (line.split(",").length != 3) {
            throw new BadFileContentException("La ligne ne contient pas le nombre attendu de colonnes");
        }

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            if (tokens.length != 3) {
                throw new BadFileContentException("Le fichier ne contient pas le nombre attendu de colonnes");
            }
            String productName = tokens[0].trim();
            String clientName = tokens[1].trim();
            int quantity = Integer.parseInt(tokens[2].trim());

            // Chercher le produit dans les portfolio
            Product existingProduct = portfolios.stream()
                    .flatMap(portfolio -> portfolio.getProducts().stream())
                    .filter(product -> product.getName().equals(productName))
                    .findFirst()
                    .orElse(new Product(productName, null));

            // Chercher le client dans le HashSet
            Client existingClient = clients.stream()
                    .filter(client -> client.getName().equals(clientName))
                    .findFirst()
                    .orElse(null);

            if (existingClient == null) {
                Client newClient = new Client(clientName, null);
                newClient.addProduct(existingProduct, quantity);
                clients.add(newClient);
            } else {
                existingClient.addProduct(existingProduct, quantity);
            }
        }

    }

    /**
     * @return Un Set de portefeuilles.
     */
    public Set<Portfolio> getPortfolios() {
        return portfolios;
    }

    /**
     * @return L'ensemble de clients.
     */
    public Set<Client> getClients() {
        return clients;
    }
}
