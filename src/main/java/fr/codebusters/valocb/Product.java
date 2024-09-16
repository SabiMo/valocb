package fr.codebusters.valocb;

import java.util.List;
import java.util.Objects;

/**
 * La classe {@code Product} représente un produit financier composé de
 * plusieurs sous-jacents (underlyings).
 * Chaque produit a un nom et une liste d'underlyings, avec un prix qui peut
 * être calculé.
 */
public class Product {

    private String name;
    private List<Underlying> underlings;

    /**
     * Constructeur de la classe {@code Product}.
     *
     * @param name       Le nom du produit.
     * @param underlings La liste des sous-jacents associés à ce produit.
     */
    public Product(String name, List<Underlying> underlings) {
        this.name = name;
        this.underlings = underlings;
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
    public List<Underlying> getUnderlings() {
        return underlings;
    }

    /**
     * @return Le prix total du produit en euro.
     */
    public double calculatePrice() {
        double totalPrice = 0;
        for (Underlying underling : underlings) {
            double conversionRate = ForexService.getInstance().getConversionRate(underling.getCurrency());
            totalPrice += underling.getPrice() * conversionRate;
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
