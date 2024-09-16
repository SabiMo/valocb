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

    /** Le nom du produit. */
    private String name;

    /** La liste des sous-jacents (underlyings) associés au produit. */
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
     * Retourne le nom du produit.
     *
     * @return Le nom du produit.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du produit.
     *
     * @param name Le nouveau nom du produit.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la liste des sous-jacents associés au produit.
     *
     * @return Une liste d'objets {@code Underlying} représentant les sous-jacents
     *         du produit.
     */
    public List<Underlying> getUnderlings() {
        return underlings;
    }

    /**
     * Définit la liste des sous-jacents associés au produit.
     *
     * @param underlings La nouvelle liste des sous-jacents.
     */
    public void setUnderlings(List<Underlying> underlings) {
        this.underlings = underlings;
    }

    /**
     * Calcule le prix total du produit en fonction des prix de ses sous-jacents
     * et des taux de conversion fournis par {@code ForexService}.
     *
     * @param forexService   Le service utilisé pour obtenir les taux de change
     *                       entre les devises.
     * @param targetCurrency La devise cible dans laquelle le prix doit être calculé
     *                       (en général "EUR").
     * @return Le prix total du produit dans la devise spécifiée.
     */
    public double calculatePrice() {
        double totalPrice = 0;
        for (Underlying underling : underlings) {
            double conversionRate = ForexService.getInstance().getConversionRate(underling.getCurrency());
            totalPrice += underling.getPrice() * conversionRate;
        }
        return totalPrice;
    }

    /**
     * Vérifie l'égalité entre deux objets {@code Product}.
     * Deux produits sont considérés égaux s'ils ont le même nom.
     *
     * @param o L'objet à comparer.
     * @return {@code true} si les objets sont égaux, {@code false} sinon.
     */
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

    /**
     * Calcule le hash code du produit basé sur son nom.
     *
     * @return Le hash code du produit.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
