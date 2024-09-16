package fr.codebusters.valocb;

/**
 * La classe {@code Underlying} représente un sous-jacent d'un produit
 * financier.
 * Chaque sous-jacent a un nom, une devise associée et un prix.
 */
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

    /**
     * @return Le nom du sous-jacent.
     */
    public String getName() {
        return name;
    }

    /**
     * @return La devise du sous-jacent.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return Le prix du sous-jacent.
     */
    public double getPrice() {
        return price;
    }

}
