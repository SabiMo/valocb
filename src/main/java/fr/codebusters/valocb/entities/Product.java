package fr.codebusters.valocb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import fr.codebusters.valocb.parsers.ForexService;
import lombok.Data;

/**
 * La classe {@code Product} représente un produit financier.
 * Chaque produit a un nom et une liste d'underlyings.
 */
@Data
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
    public double price(ForexService forexService) {
        double totalPrice = 0;
        for (Underlying underlying : underlyings) {
            double conversionRate = forexService.getConversionRate(underlying.getCurrency());
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
