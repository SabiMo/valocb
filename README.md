# ValoCB

## Objectif

Développer une application pour agréger les prix des produits détenus par des clients dans différents portefeuilles.

### Fonctionnalités

- Calcul du prix d’un portefeuille en EUR.
- Calcul du prix des produits par client en EUR.

### Input

3 fichiers CSV seront fournis :

- `Prices.csv`
- `Product.csv`
- `Forex.csv`

### Output

Deux fichiers CSV doivent être générés :

- `Reporting-portfolio.csv` : contient les prix des portefeuilles (colonnes PTF et price)
- `Reporting-client.csv` : contient les capitaux des clients (colonnes Client et capital)

### Exemple de Calculs

1. **Prix d'un produit en EUR**  
   Exemple : P1 contient les underlyings U11, U12, et U13.

2. **Prix d’un portefeuille en EUR**  
   Le prix d’un portefeuille est la somme des prix des produits multipliés par leur quantité.

3. **Le prix des produits par client en EUR**  
   Le capital d’un client est la somme du prix des produits qu'il détient multiplié par les quantités.

### Contraintes Techniques

- Java 17
- Tests unitaires > 80%
