package fr.codebusters.valocb.entities;

import java.util.Set;
import java.util.HashSet;
import lombok.Data;

/**
 * La classe {@code Portfolio} représente un portefeuille contenant plusieurs
 * produits.
 * Chaque produit est associé à une quantité spécifique.
 */
@Data
public class Portfolio {

    private String name;
    private Set<Product> products;

    /**
     * Constructeur de la classe {@code Portfolio}.
     *
     * @param name     Le nom du portefeuille.
     * @param products Une map des produits dans le portefeuille et leurs quantités.
     */
    public Portfolio(String name, Set<Product> products) {
        this.name = name;
        if (products == null) {
            this.products = new HashSet<>();
        } else {
            this.products = products;
        }
    }

    /**
     * Ajoute un produit avec une quantité à ce client.
     *
     * @param product Le produit.
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }

    /**
     * @return Le prix total du portefeuille en euro.
     */
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.calculatePrice();
        }
        return totalPrice;
    }
}
