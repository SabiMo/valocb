package fr.codebusters.valocb;

/**
 * La classe {@code Underlying} représente un sous-jacent d'un produit financier.
 * Chaque sous-jacent a un nom, une devise associée et un prix.
 */
public class Underlying {

    /** Le nom du sous-jacent. */
    private String name;

    /** La devise dans laquelle le sous-jacent est évalué. */
    private String currency;

    /** Le prix du sous-jacent. */
    private double price;

    /**
     * Constructeur de la classe {@code Underlying}.
     *
     * @param name      Le nom du sous-jacent.
     * @param currency  La devise associée au sous-jacent.
     * @param price     Le prix du sous-jacent.
     */
    public Underlying(String name, String currency, double price) {
        this.name = name;
        this.currency = currency;
        this.price = price;
    }

    /**
     * Retourne le nom du sous-jacent.
     *
     * @return Le nom du sous-jacent.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du sous-jacent.
     *
     * @param name Le nouveau nom du sous-jacent.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la devise dans laquelle le sous-jacent est évalué.
     *
     * @return La devise du sous-jacent.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Définit la devise associée au sous-jacent.
     *
     * @param currency La nouvelle devise du sous-jacent.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Retourne le prix du sous-jacent.
     *
     * @return Le prix du sous-jacent.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Définit le prix du sous-jacent.
     *
     * @param price Le nouveau prix du sous-jacent.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
