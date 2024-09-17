package fr.codebusters.valocb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.codebusters.valocb.parsers.ForexService;

/**
 * La classe {@code Product} représente un produit financier.
 * Chaque produit a un nom et une liste d'underlyings.
 */
public class Product {

    private String name;
    private List<Underlying> underlyings;

    /**
     * Constructeur de la classe {@code Product}.
     *
     * @param name        Le nom du produit.
     * @param underlyings La liste des sous-jacents associés à ce produit.
     */
    public Product(String name, List<Underlying> underlyings) {
        this.name = name;
        if (underlyings == null) {
            this.underlyings = new ArrayList<Underlying>();
        } else {
            this.underlyings = underlyings;
        }
    }

    /**
     * @return Le nom du produit.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Une liste d'objets {@code Underlying} représentant les sous-jacents
     *         du produit.
     */
    public List<Underlying> getUnderlyings() {
        return underlyings;
    }

    /**
     * Ajoute un produit avec une quantité à ce client.
     *
     * @param underlying Le sous-jacent.
     */
    public void addUnderlying(Underlying underlying) {
        this.underlyings.add(underlying);
    }

    /**
     * @return Le prix total du produit en euro.
     */
    public double calculatePrice() {
        double totalPrice = 0;
        for (Underlying underlying : underlyings) {
            double conversionRate = ForexService.getInstance().getConversionRate(underlying.getCurrency());
            totalPrice += underlying.getPrice() * conversionRate;
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
        Product product = (Product) o;
        return this.name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
