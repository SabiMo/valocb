package fr.codebusters.valocb;

import java.util.Map;

/**
 * La classe {@code Client} représente un client qui détient plusieurs produits
 * avec des quantités spécifiques.
 */
public class Client {

    private String name;
    private Map<Product, Integer> products;

    /**
     * Constructeur de la classe {@code Client}.
     *
     * @param name     Le nom du client.
     * @param products Une map: (produits, quantités).
     */
    public Client(String name, Map<Product, Integer> products) {
        this.name = name;
        this.products = products;
    }

    /**
     * @return Le nom du client.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Une map: (produit, quantité).
     */
    public Map<Product, Integer> getProducts() {
        return products;
    }

    /**
     * @return Le capital total du client en euro.
     */
    public double calculateCapital() {
        double totalCapital = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalCapital += product.calculatePrice() * quantity;
        }
        return totalCapital;
    }
}
