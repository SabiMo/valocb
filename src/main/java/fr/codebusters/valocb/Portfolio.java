package fr.codebusters.valocb;

import java.util.Map;

/**
 * La classe {@code Portfolio} représente un portefeuille contenant plusieurs
 * produits.
 * Chaque produit est associé à une quantité spécifique.
 */
public class Portfolio {

    private String name;
    private Map<Product, Integer> products;

    /**
     * Constructeur de la classe {@code Portfolio}.
     *
     * @param name     Le nom du portefeuille.
     * @param products Une map des produits dans le portefeuille et leurs quantités.
     */
    public Portfolio(String name, Map<Product, Integer> products) {
        this.name = name;
        this.products = products;
    }

    /**
     * @return Le nom du portefeuille.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Une map (produit, quantité).
     */
    public Map<Product, Integer> getProducts() {
        return products;
    }

    /**
     * @return Le prix total du portefeuille en euro.
     */
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.calculatePrice() * quantity;
        }
        return totalPrice;
    }
}
