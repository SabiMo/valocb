package fr.codebusters.valocb.entities;

import java.util.Objects;
import lombok.Data;

/**
 * La classe {@code Underlying} représente un sous-jacent d'un produit
 * financier.
 * Chaque sous-jacent a un nom, une devise associée et un prix.
 */
@Data
public class Underlying {

    private String name;
    private String currency;
    private double price;

    /**
     * Constructeur de la classe {@code Underlying}.
     *
     * @param name     Le nom du sous-jacent.
     * @param currency La devise associée au sous-jacent.
     * @param price    Le prix du sous-jacent.
     */
    public Underlying(String name, String currency, double price) {
        this.name = name;
        this.currency = currency;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Underlying underlying = (Underlying) o;
        return this.name.equals(underlying.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
