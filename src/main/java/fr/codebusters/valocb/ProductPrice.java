package fr.codebusters.valocb;

/**
 * Classe qui représente le prix d'un produit avec ses détails.
 */
public class ProductPrice {

    private String portfolio;
    private String product;
    private String underlying;
    private String currency;
    private double price;

    /**
     * Constructeur de la classe ProductPrice.
     *
     * @param portfolio  Le nom du portefeuille.
     * @param product    Le nom du produit.
     * @param underlying Le nom de l'underlying (sous-jacent).
     * @param currency   La devise utilisée.
     * @param price      Le prix de l'underlying.
     */
    public ProductPrice(String portfolio, String product, String underlying, String currency, double price) {
        this.portfolio = portfolio;
        this.product = product;
        this.underlying = underlying;
        this.currency = currency;
        this.price = price;
    }

    /**
     * @return Le nom du portefeuille.
     */
    public String getPortfolio() {
        return portfolio;
    }

    /**
     * @return Le nom du produit.
     */
    public String getProduct() {
        return product;
    }

    /**
     * @return Le nom de l'underlying.
     */
    public String getUnderlying() {
        return underlying;
    }

    /**
     * @return La devise de l'underlying.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return Le prix de l'underlying.
     */
    public double getPrice() {
        return price;
    }
}
