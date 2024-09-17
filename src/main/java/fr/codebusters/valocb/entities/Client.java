package fr.codebusters.valocb.entities;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * La classe {@code Client} représente un client qui détient plusieurs produits
 * avec des quantités spécifiques.
 */
@Data
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
        if (products == null) {
            this.products = new HashMap<>();
        } else {
            this.products = products;
        }
    }

    /**
     * Ajoute un produit avec une quantité à ce client.
     *
     * @param product  Le produit.
     * @param quantity Sa quantité.
     */
    public void addProduct(Product product, int quantity) {
        this.products.put(product, quantity);
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
