# Cahier des charges — Bibliothèque de prêt

**Mini-projet de programmation orientée objet — Groupe 7**
Membres : Ristur, Ero, Jasmine
Dépôt : https://github.com/cyberr-genius/POO-java — tag: `version-1.0`

---

## 1. Contexte et objectif

Une bibliothèque doit suivre l'état de ses documents et de ses prêts : savoir quels documents existent, lesquels sont disponibles et quel adhérent détient chacun de ceux qui sont sortis.

L'objectif du projet est de réaliser une application Java qui gère des **documents** (livres et périodiques), des **adhérents** et des **prêts**, en garantissant à tout moment un état cohérent : un document n'est jamais prêté à deux personnes, un document non emprunté ne peut pas être retourné, et une opération refusée ne modifie rien.

Le projet sert aussi d'application de la conception orientée objet : encapsulation, invariants, héritage, composition, polymorphisme, interfaces, collections, exceptions et tests automatisés.

## 2. Périmètre

### Inclus

- gestion en mémoire de documents, d'adhérents et d'auteurs ;
- recherche de documents par numéro et par titre ;
- emprunt et retour de documents ;
- utilisation en ligne de commande, avec une démonstration automatique et un menu interactif ;
- tests automatisés JUnit.

### Exclu

- interface graphique ;
- base de données, sauvegarde sur disque ou persistance de toute nature ;
- accès réseau ;
- historique des prêts, dates d'échéance et gestion des retards ;
- limite du nombre d'emprunts par adhérent.

## 3. Utilisateurs

Le personnel de la bibliothèque, qui utilise l'application en ligne de commande pour enregistrer les documents, les adhérents et les prêts.

## 4. Règles de gestion

 Réf | Règle |

 G1 | Un document a un numéro unique dans le catalogue et un titre non vide. |
 G2 | Un document disponible peut être emprunté par un adhérent ; il devient alors indisponible. |
 G3 | Un document déjà emprunté ne peut pas être emprunté une seconde fois. |
 G4 | Seul l'adhérent qui détient un document peut le retourner ; le document redevient disponible. |
 G5 | Toute opération refusée laisse le catalogue et les documents exactement dans l'état précédent. |
 G6 | Un livre a au moins un auteur ; un périodique n'en a pas. |
 G7 | La durée de prêt dépend du type de document : 14 jours pour un livre, 7 jours pour un périodique. |

## 5. Exigences fonctionnelles

 Réf | Exigence | Règles liées |

 EF1 | Enregistrer un adhérent (identifiant positif, nom et prénom non vides). | — |
 EF2 | Enregistrer un auteur (identifiant positif, nom et prénom non vides). | — |
 EF3 | Ajouter un livre au catalogue en l'associant à un ou plusieurs auteurs enregistrés. | G1, G6 |
 EF4 | Ajouter un périodique au catalogue avec son numéro de parution. | G1 |
 EF5 | Refuser l'ajout d'un document dont le numéro existe déjà. | G1 |
 EF6 | Consulter le catalogue complet. | — |
 EF7 | Rechercher un document par numéro. | — |
 EF8 | Rechercher un document par titre, en ignorant la casse et en acceptant une partie du titre. | — |
 EF9 | Emprunter un document disponible pour un adhérent enregistré. | G2, G3, G7 |
 EF10 | Retourner un document par l'adhérent qui l'a emprunté. | G4 |
 EF11 | Refuser, avec un message précis, un emprunt ou un retour impossible (document inconnu, déjà emprunté, non emprunté, emprunté par un autre). 
 EF12 | Proposer au démarrage le choix entre une démonstration automatique et un menu interactif. | — |

## 6. Exigences non fonctionnelles et contraintes

- **Langage et plateforme** : Java 17 ou supérieur, Maven 3.x pour la construction et les tests.
- **Encapsulation** : aucun champ métier public ; l'état des objets est protégé par des invariants explicites.
- **Invariants** : numéro de document positif et immuable ; titre non vide ; un document est soit disponible, soit emprunté par un seul adhérent ; liste d'auteurs d'un livre non vide et non modifiable de l'extérieur ; numéros uniques dans un catalogue.
- **Répartition des responsabilités** : entités, catalogue et service de prêt sont séparés.
- **Faible couplage** : le service de prêt dépend de l'interface du catalogue, pas de son implémentation.
- **Robustesse** : trois types de situations distinguées : donnée invalide (`IllegalArgumentException`), absence attendue (`Optional` vide) et échec métier (exception vérifiée dérivée de `BibliothequeException`). Une opération échouée ne laisse jamais l'état partiellement modifié.
- **Lisibilité** : nommage clair, documentation des contrats des interfaces.

## 7. Architecture

| Élément | Nature | Rôle |
|---|---|---|
| `Document` | classe abstraite | numéro, titre, état d'emprunt, durée de prêt |
| `Livre` | classe | document composé d'une liste d'auteurs |
| `Periodique` | classe | document avec un numéro de parution |
| `Auteur`, `Adherent` | classes immuables | personnes identifiées par un numéro |
| `Empruntable` | interface | contrat du prêt : `estDisponible`, `emprunter`, `retourner` |
| `CatalogueDocuments` | interface | contrat du catalogue |
| `CatalogueMap` | classe | catalogue fondé sur une `Map` (`LinkedHashMap`) |
| `ServicePret` | classe | orchestre les emprunts et les retours |
| `BibliothequeException` et 4 sous-classes | exceptions vérifiées | échecs métier |
| `Main` | classe | point d'entrée : démonstration et menu interactif |

Choix principaux : l'emprunteur est stocké dans `Document`, ce qui évite de maintenir deux structures ; la collection est une `Map` indexée par le numéro, pour un accès direct et une unicité garantie par la clé.

## 8. Utilisation

Au lancement, le programme propose :

1. une **démonstration automatique** avec les adhérents Karaba et Kirikou, qui enchaîne des essais sur le modèle, le polymorphisme, le catalogue et les prêts, y compris les refus ;
2. un **menu interactif** : consulter le catalogue, rechercher un document, enregistrer un adhérent, enregistrer un auteur, ajouter un document, emprunter, retourner.

Commandes : voir le `README.md` du dépôt (`mvn test`, `mvn compile`, `java -cp target/classes Main`).

## 9. Critères de recette

Le système est accepté lorsque :

- le code compile sans erreur ni avertissement ;
- la suite de 20 tests JUnit s'exécute entièrement avec succès ;
- chaque règle de gestion est vérifiée par au moins un test, avec des cas nominaux, limites et invalides ;
- au moins quatre cas d'échec vérifient aussi que l'état est resté intact après l'exception ;
- la démonstration automatique s'exécute de bout en bout et affiche les refus attendus ;
- le menu interactif traite correctement les saisies valides et invalides.

## 10. Livrables

- dépôt GitHub : code source, tests automatisés, `README.md` ; tag `version-1.0` ;
- rapport final au format PDF ;
- présentation PDF ;
- Un cahier de charge du système demandé;
- capsule vidéo de démonstration (8 minutes au maximum), remise sur clé USB.

## 11. Organisation du travail

| Membre | Fichiers pris en charge |
| Edson  | `Auteur`, `Adherent`, `Document`, `Livre`, `Periodique`, `Readme.md` |
| Ristur | `ServicePret`, `CatalogueMap`, `BibliothequeException` et ses 4 sous-classes `BibliothequeException`|
| Jasmine| `Empruntable`, `CatalogueDocuments`, `ModeleTest`, `CatalogueMapTest`, `ServicePretTest`, `Main`, `diagramme-classe.pdf`, , `cahier-charge.md`, |

Le projet est découpé en quatre jalons, publiés sur le dépôt : jalon 1 (modèle et invariants), jalon 2 (composition et polymorphisme), jalon 3 (interfaces et collections), jalon 4 (exceptions, tests et version finale). 

