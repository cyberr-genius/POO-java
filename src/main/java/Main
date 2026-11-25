import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Point d'entrée en ligne de commande.
 * Menu de démarrage : démonstration automatique (Karaba et Kirikou) ou menu interactif.
 * Les adhérents et les auteurs enregistrés au menu interactif sont gardés ici, en mémoire.
 */
public class Main {
    private static final String LIGNE = "==================================================";
    private static final Scanner SC = new Scanner(System.in);

    private static final CatalogueDocuments catalogue = new CatalogueMap();
    private static final ServicePret service = new ServicePret(catalogue);
    private static final Map<Integer, Adherent> adherents = new LinkedHashMap<>();
    private static final Map<Integer, Auteur> auteurs = new LinkedHashMap<>();

    public static void main(String[] args) {
        while (true) {
            afficherMenuDemarrage();
            int choix = lireEntier("Votre choix : ");
            if (choix == 1) {
                demonstrationAutomatique();
            } else if (choix == 2) {
                menuInteractif();
                return;
            } else if (choix == 0) {
                System.out.println("Au revoir.");
                return;
            } else {
                System.out.println("Choix invalide.\n");
            }
        }
    }

    // ------------------------------------------------------------------
    // Menus
    // ------------------------------------------------------------------

    private static void afficherMenuDemarrage() {
        System.out.println(LIGNE);
        System.out.println("   === SYSTEME DE GESTION DE LA BIBLIOTHEQUE ===");
        System.out.println(LIGNE);
        System.out.println("Comment souhaitez-vous démarrer ?");
        System.out.println("1- Lancer la démonstration automatique (Tests Jalons 1 à 4)");
        System.out.println("2- Accéder directement au menu interactif");
        System.out.println("0- Quitter");
        System.out.println(LIGNE);
    }

    private static void afficherMenuInteractif() {
        System.out.println(LIGNE);
        System.out.println("  === MENU INTERACTIF DE LA BIBLIOTHEQUE ===");
        System.out.println(LIGNE);
        System.out.println("1- Consulter le catalogue complet");
        System.out.println("2- Rechercher un document par titre/numéro");
        System.out.println("3- Enregistrer un nouvel adhérent");
        System.out.println("4- Enregistrer un nouvel auteur");
        System.out.println("5- Ajouter un document (Livre/Périodique)");
        System.out.println("6- Emprunter un document");
        System.out.println("7- Retourner un document");
        System.out.println("0- Quitter");
        System.out.println(LIGNE);
    }

    private static void menuInteractif() {
        while (true) {
            afficherMenuInteractif();
            int choix = lireEntier("Votre choix : ");
            System.out.println();
            switch (choix) {
                case 1: consulterCatalogue(); break;
                case 2: rechercherDocument(); break;
                case 3: enregistrerAdherent(); break;
                case 4: enregistrerAuteur(); break;
                case 5: ajouterDocument(); break;
                case 6: emprunterDocument(); break;
                case 7: retournerDocument(); break;
                case 0:
                    System.out.println("Au revoir.");
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
            System.out.println();
        }
    }

    // ------------------------------------------------------------------
    // Actions du menu interactif
    // ------------------------------------------------------------------

    private static void consulterCatalogue() {
        List<Document> docs = catalogue.tousLesDocuments();
        if (docs.isEmpty()) {
            System.out.println("Le catalogue est vide.");
            return;
        }
        System.out.println("Catalogue (" + docs.size() + " document(s)) :");
        afficherDocuments(docs);
    }

    private static void rechercherDocument() {
        System.out.println("1- Par titre");
        System.out.println("2- Par numéro");
        int mode = lireEntier("Votre choix : ");
        if (mode == 1) {
            String titre = lireLigne("Titre (ou partie du titre) : ");
            List<Document> resultats = catalogue.rechercherParTitre(titre);
            if (resultats.isEmpty()) {
                System.out.println("Aucun document ne correspond à ce titre.");
            } else {
                afficherDocuments(resultats);
            }
        } else if (mode == 2) {
            int numero = lireEntier("Numéro du document : ");
            Document doc = catalogue.rechercherParNumero(numero).orElse(null);
            System.out.println(doc == null ? "Aucun document N°" + numero + " dans le catalogue." : " -> " + doc);
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private static void enregistrerAdherent() {
        int id = lireEntier("Identifiant de l'adhérent : ");
        if (adherents.containsKey(id)) {
            System.out.println("Refusé : un adhérent N°" + id + " existe déjà.");
            return;
        }
        String nom = lireLigne("Nom : ");
        String prenom = lireLigne("Prénom : ");
        try {
            Adherent a = new Adherent(id, nom, prenom);
            adherents.put(id, a);
            System.out.println("Adhérent enregistré : " + a);
        } catch (IllegalArgumentException e) {
            System.out.println("Refusé : " + e.getMessage());
        }
    }

    private static void enregistrerAuteur() {
        int id = lireEntier("Identifiant de l'auteur : ");
        if (auteurs.containsKey(id)) {
            System.out.println("Refusé : un auteur N°" + id + " existe déjà.");
            return;
        }
        String nom = lireLigne("Nom : ");
        String prenom = lireLigne("Prénom : ");
        try {
            Auteur a = new Auteur(id, nom, prenom);
            auteurs.put(id, a);
            System.out.println("Auteur enregistré : " + a);
        } catch (IllegalArgumentException e) {
            System.out.println("Refusé : " + e.getMessage());
        }
    }

    private static void ajouterDocument() {
        System.out.println("1- Livre");
        System.out.println("2- Périodique");
        int type = lireEntier("Type de document : ");
        if (type != 1 && type != 2) {
            System.out.println("Choix invalide.");
            return;
        }
        if (type == 1 && auteurs.isEmpty()) {
            System.out.println("Refusé : aucun auteur enregistré. Utilisez d'abord l'option 4.");
            return;
        }
        int numero = lireEntier("Numéro du document : ");
        String titre = lireLigne("Titre : ");
        try {
            Document doc;
            if (type == 1) {
                List<Auteur> choisis = lireAuteurs();
                if (choisis == null) {
                    return; // message déjà affiché, rien n'a été créé
                }
                doc = new Livre(numero, titre, choisis);
            } else {
                int parution = lireEntier("Numéro de parution : ");
                doc = new Periodique(numero, titre, parution);
            }
            catalogue.ajouterDocument(doc);
            System.out.println("Document ajouté : " + doc);
        } catch (IllegalArgumentException | DocumentDejaPresentException e) {
            System.out.println("Refusé : " + e.getMessage());
        }
    }

    private static void emprunterDocument() {
        Adherent adherent = choisirAdherent();
        if (adherent == null) {
            return;
        }
        int numero = lireEntier("Numéro du document à emprunter : ");
        try {
            service.emprunter(numero, adherent);
            int jours = catalogue.rechercherParNumero(numero).get().dureePret();
            System.out.println("Emprunt enregistré pour " + adherent.getNomComplet()
                    + " (durée de prêt : " + jours + " jours).");
        } catch (BibliothequeException e) {
            System.out.println("Refusé : " + e.getMessage());
        }
    }

    private static void retournerDocument() {
        Adherent adherent = choisirAdherent();
        if (adherent == null) {
            return;
        }
        int numero = lireEntier("Numéro du document à retourner : ");
        try {
            service.retourner(numero, adherent);
            System.out.println("Retour enregistré : le document N°" + numero + " est de nouveau disponible.");
        } catch (BibliothequeException e) {
            System.out.println("Refusé : " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // Démonstration automatique (Karaba et Kirikou)
    // ------------------------------------------------------------------

    private static void demonstrationAutomatique() {
        System.out.println();
        System.out.println(LIGNE);
        System.out.println("   === DEMONSTRATION AUTOMATIQUE ===");
        System.out.println(LIGNE);

        CatalogueDocuments cat = new CatalogueMap();
        ServicePret srv = new ServicePret(cat);
        Auteur achebe = new Auteur(1, "Achebe", "Chinua");
        Adherent karaba = new Adherent(1, "LaSorciere", "Karaba");
        Adherent kirikou = new Adherent(2, "LePetit", "Kirikou");

        System.out.println("\n--- Jalon 1 : modèle et invariants ---");
        System.out.println("Créés : " + karaba + " et " + kirikou);
        essayer("Adhérent avec un nom vide", () -> new Adherent(3, "  ", "Jean"));
        essayer("Auteur avec un identifiant nul", () -> new Auteur(0, "Camara", "Laye"));
        essayer("Livre avec un titre vide", () -> new Livre(105, "   ", List.of(achebe)));

        System.out.println("\n--- Jalon 2 : héritage et polymorphisme ---");
        Document livre = new Livre(101, "Le monde s'effondre", List.of(achebe));
        Document revue = new Periodique(102, "Jeune Afrique", 1500);
        for (Document d : List.of(livre, revue)) {
            System.out.println(d + " -> durée de prêt : " + d.dureePret() + " jours");
        }
        essayer("Livre sans auteur", () -> new Livre(103, "Sans auteur", new ArrayList<>()));

        System.out.println("\n--- Jalon 3 : catalogue, equals/hashCode et recherche ---");
        try {
            cat.ajouterDocument(livre);
            cat.ajouterDocument(revue);
            System.out.println("Documents au catalogue : " + cat.getNombreDocuments());
            Document memeNumero = new Livre(101, "Une autre édition", List.of(achebe));
            System.out.println("Deux documents de même numéro sont égaux : " + livre.equals(memeNumero));
            cat.ajouterDocument(memeNumero);
        } catch (DocumentDejaPresentException e) {
            System.out.println("Refusé (attendu) : " + e.getMessage());
        }
        System.out.println("Recherche par titre \"afrique\" : " + cat.rechercherParTitre("afrique").size() + " résultat");
        System.out.println("Recherche du N°999 : " + (cat.rechercherParNumero(999).isPresent() ? "trouvé" : "absent"));

        System.out.println("\n--- Jalon 4 : prêts, exceptions et atomicité ---");
        pret("Karaba emprunte le livre 101", () -> srv.emprunter(101, karaba));
        System.out.println("   " + livre);
        pret("Kirikou emprunte le même livre", () -> srv.emprunter(101, kirikou));
        System.out.println("   Karaba reste l'emprunteur : " + livre.getEmprunteur().get().getNomComplet());
        pret("Kirikou retourne un livre qu'il n'a pas emprunté", () -> srv.retourner(101, kirikou));
        pret("Emprunt du document 999 (inexistant)", () -> srv.emprunter(999, kirikou));
        pret("Karaba retourne le livre", () -> srv.retourner(101, karaba));
        pret("Kirikou emprunte le livre", () -> srv.emprunter(101, kirikou));
        System.out.println("   " + livre);

        System.out.println("\n=== Fin de la démonstration ===\n");
    }

    @FunctionalInterface
    private interface Creation {
        void executer();
    }

    @FunctionalInterface
    private interface Operation {
        void executer() throws BibliothequeException;
    }

    private static void essayer(String description, Creation creation) {
        try {
            creation.executer();
            System.out.println(description + " : accepté (inattendu)");
        } catch (IllegalArgumentException e) {
            System.out.println(description + " : refusé (attendu) -> " + e.getMessage());
        }
    }

    private static void pret(String description, Operation operation) {
        try {
            operation.executer();
            System.out.println(description + " : OK");
        } catch (BibliothequeException e) {
            System.out.println(description + " : refusé -> " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // Utilitaires de saisie et d'affichage
    // ------------------------------------------------------------------

    private static Adherent choisirAdherent() {
        if (adherents.isEmpty()) {
            System.out.println("Refusé : aucun adhérent enregistré. Utilisez d'abord l'option 3.");
            return null;
        }
        int id = lireEntier("Identifiant de l'adhérent : ");
        Adherent a = adherents.get(id);
        if (a == null) {
            System.out.println("Refusé : aucun adhérent N°" + id + ".");
        }
        return a;
    }

    /** Lit une liste d'identifiants d'auteurs ; renvoie null (après message) si l'un d'eux est inconnu. */
    private static List<Auteur> lireAuteurs() {
        System.out.println("Auteurs enregistrés :");
        for (Auteur a : auteurs.values()) {
            System.out.println(" -> " + a);
        }
        String saisie = lireLigne("Numéros des auteurs du livre (séparés par des virgules) : ");
        List<Auteur> choisis = new ArrayList<>();
        for (String morceau : saisie.split(",")) {
            try {
                int id = Integer.parseInt(morceau.trim());
                Auteur a = auteurs.get(id);
                if (a == null) {
                    System.out.println("Refusé : aucun auteur N°" + id + ".");
                    return null;
                }
                choisis.add(a);
            } catch (NumberFormatException e) {
                System.out.println("Refusé : \"" + morceau.trim() + "\" n'est pas un numéro valide.");
                return null;
            }
        }
        return choisis;
    }

    private static void afficherDocuments(List<Document> docs) {
        for (Document d : docs) {
            System.out.println(" -> " + d);
        }
    }

    private static String lireLigne(String invite) {
        System.out.print(invite);
        if (!SC.hasNextLine()) {
            System.out.println("\nFin de la saisie. Au revoir.");
            System.exit(0);
        }
        return SC.nextLine();
    }

    private static int lireEntier(String invite) {
        while (true) {
            String ligne = lireLigne(invite).trim();
            try {
                return Integer.parseInt(ligne);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide : un nombre entier est attendu.");
            }
        }
    }
}
