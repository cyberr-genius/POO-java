# POO-java
Projet java 
# Bibliothèque de prêt — Mini-projet POO

Groupe 7


Gestion de bibliothèque en java : `version-1.0`

## Description

Application Java en ligne de commande, en mémoire, qui gère des documents (livres, périodiques),
des adhérents et des prêts. Aucun framework, base de données ou réseau.

Au lancement, un menu propose :

1. une **démonstration automatique** (Karaba et Kirikou) qui enchaîne des essais sur le modèle,
   le polymorphisme, le catalogue et les prêts, avec leurs refus ;
2. un **menu interactif** pour consulter et rechercher des documents, enregistrer des adhérents
   et des auteurs, ajouter des livres et des périodiques, emprunter et retourner un document.

La démonstration n'est pas la suite de tests JUnit : celle-ci s'exécute avec `mvn test`.

## Structure

- `src/main/java` : code source (entités, interfaces `Empruntable` et `CatalogueDocuments`,
  `CatalogueMap`, `ServicePret`, exceptions métier, `Main`)
- `src/test/java` : tests JUnit 5 (`ModeleTest`, `CatalogueMapTest`, `ServicePretTest`)
- `pom.xml` : configuration Maven (Java 17, JUnit 5)

## Prérequis

JDK 17 ou plus, et Maven 3.8 ou plus pour les tests.

## Compiler, tester, lancer

Avec Maven :

```
mvn test
mvn compile
java -cp target/classes Main
```

Sans Maven (le code principal seulement) :

```
javac -d bin src/main/java/*.java
java -cp bin Main
```

Si les accents s'affichent mal dans la console : ajouter `-Dstdout.encoding=UTF-8` (Java 19 ou plus)
ou `-Dfile.encoding=UTF-8` (Java 17), ou exécuter `chcp 65001` sous Windows.

## Choix de conception

- `Document` (abstrait) implémente `Empruntable` : il protège lui-même son invariant d'emprunt.
- `Livre` est composé d'une liste d'auteurs (copie défensive, non modifiable) ; `Periodique` redéfinit `dureePret()`.
- `ServicePret` dépend de l'interface `CatalogueDocuments`, pas de `CatalogueMap`.
- Échecs métier : exceptions vérifiées dérivées de `BibliothequeException`. Données invalides : `IllegalArgumentException`.
- Atomicité : toutes les vérifications précèdent l'unique modification d'état.
- `rechercherParNumero` renvoie un `Optional` (l'absence est un cas attendu).

## Limites connues

Pas de base de donnée ni d'historique des prêts. 
Les adhérents et les auteurs du menu interactif sont gardés en mémoire par `Main`, pas par le modèle.
