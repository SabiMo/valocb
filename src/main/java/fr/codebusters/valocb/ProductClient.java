package fr.codebusters.valocb;

/**
 * Classe représentant la relation entre un produit et un client ainsi que la
 * quantité détenue.
 */
public class ProductClient {

    private String product;
    private String client;
    private int quantity;

    /**
     * Constructeur de la classe ProductClient.
     *
     * @param product  Le nom du produit.
     * @param client   Le nom du client.
     * @param quantity La quantité détenue par le client.
     */
    public ProductClient(String product, String client, int quantity) {
        this.product = product;
        this.client = client;
        this.quantity = quantity;
    }

    /**
     * @return Le nom du produit.
     */
    public String getProduct() {
        return product;
    }

    /**
     * @return Le nom du client.
     */
    public String getClient() {
        return client;
    }

    /**
     * @return La quantité détenue.
     */
    public int getQuantity() {
        return quantity;
    }
}
