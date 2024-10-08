package fr.codebusters.valocb.entities;

import java.util.Set;

import fr.codebusters.valocb.parsers.ForexService;

import java.util.HashSet;
import java.util.Objects;
import lombok.Data;

/**
 * La classe {@code Portfolio} représente un portefeuille contenant plusieurs
 * produits.
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
    public double price(ForexService forexService) {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.price(forexService);
        }
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Portfolio portfolio = (Portfolio) o;
        return this.name.equals(portfolio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
