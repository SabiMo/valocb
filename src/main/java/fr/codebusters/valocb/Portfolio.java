package fr.codebusters.valocb;

import java.util.Map;

/**
 * La classe {@code Portfolio} représente un portefeuille contenant plusieurs
 * produits.
 * Chaque produit est associé à une quantité spécifique.
 */
public class Portfolio {

    /** Le nom du portefeuille. */
    private String name;

    /** Une map des produits dans le portefeuille et leurs quantités. */
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
     * Retourne le nom du portefeuille.
     *
     * @return Le nom du portefeuille.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du portefeuille.
     *
     * @param name Le nouveau nom du portefeuille.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la map des produits du portefeuille.
     *
     * @return Une map où la clé est un produit et la valeur est la quantité
     *         détenue.
     */
    public Map<Product, Integer> getProducts() {
        return products;
    }

    /**
     * Définit la map des produits dans le portefeuille.
     *
     * @param products Une map où la clé est un produit et la valeur est la quantité
     *                 détenue.
     */
    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    /**
     * Calcule le prix total du portefeuille en fonction des produits et de leurs
     * quantités.
     * Le prix des produits est calculé en utilisant les taux de change fournis par
     * {@code ForexService}.
     *
     * @param forexService   Le service utilisé pour obtenir les taux de change
     *                       entre les devises.
     * @param targetCurrency La devise cible dans laquelle le prix doit être calculé
     *                       (généralement "EUR").
     * @return Le prix total du portefeuille dans la devise spécifiée.
     */
    public double calculateTotalPrice(String targetCurrency) {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.calculatePrice() * quantity;
        }
        return totalPrice;
    }
}
