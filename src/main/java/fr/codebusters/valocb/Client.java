package fr.codebusters.valocb;

import java.util.Map;

/**
 * La classe {@code Client} représente un client qui détient plusieurs produits
 * avec des quantités spécifiques.
 * Chaque produit est stocké dans une {@code Map<Product, Integer>} où la clé
 * est le produit
 * et la valeur représente la quantité détenue par le client.
 */
public class Client {

    /** Le nom du client. */
    private String name;

    /** Une map des produits détenus par le client et leurs quantités. */
    private Map<Product, Integer> products;

    /**
     * Constructeur de la classe {@code Client}.
     *
     * @param name     Le nom du client.
     * @param products Une map des produits détenus par le client et leurs
     *                 quantités.
     */
    public Client(String name, Map<Product, Integer> products) {
        this.name = name;
        this.products = products;
    }

    /**
     * Retourne le nom du client.
     *
     * @return Le nom du client.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du client.
     *
     * @param name Le nouveau nom du client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la map des produits détenus par le client.
     *
     * @return Une map où la clé est un produit et la valeur est la quantité
     *         détenue.
     */
    public Map<Product, Integer> getProducts() {
        return products;
    }

    /**
     * Définit la map des produits détenus par le client.
     *
     * @param products Une map où la clé est un produit et la valeur est la quantité
     *                 détenue.
     */
    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    /**
     * Calcule le capital total du client en fonction des produits qu'il possède et
     * de leurs quantités.
     * Le prix des produits est calculé en utilisant les taux de change fournis par
     * {@code ForexService}.
     *
     * @param forexService   Le service utilisé pour obtenir les taux de change
     *                       entre les devises.
     * @param targetCurrency La devise cible dans laquelle le capital doit être
     *                       calculé (toujours "EUR").
     * @return Le capital total du client dans la devise spécifiée.
     */
    public double calculateCapital(String targetCurrency) {
        double totalCapital = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalCapital += product.calculatePrice() * quantity;
        }
        return totalCapital;
    }
}
