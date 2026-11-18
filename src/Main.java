import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("LANCEMENT DES 12 TESTS");

        // Collections principales gerees directement dans le Main
        Map<Integer, Document> catalogue = new HashMap<>(); // HashMap pour le catalogue par ID
        Set<Adherent> adherents = new HashSet<>();          // HashSet pour les adhérents uniques
        List<String> historique = new ArrayList<>();        // ArrayList pour l'historique chronologique

        // Creation des auteurs
        Auteur auteur1 = new Auteur(1, "Bloch", "Joshua");
        Auteur auteur2 = new Auteur(2, "Leye", "Babacar");

        // Creation des documents
        Document livre = new Livre(101, "Effective Java", auteur1, 416);
        Document periodique = new Periodique(202, "Revue Scientifique", auteur2, 12);

        // Creation des adherents
        Adherent adh1 = new Adherent(1, "Ouedraogo", "Alice");
        Adherent adh2 = new Adherent(2, "Sawadogo", "Bob");
        Adherent adhDuplique = new Adherent(1, "Fofana", "Alpha"); // Même ID que adh1

        //TEST 1 : Ajout d'un livre valide
        System.out.println("\n[Test 1] Ajout d'un livre au catalogue :");
        catalogue.put(livre.getNumero(), livre);
        historique.add("Ajout du livre : " + livre.getTitre());
        System.out.println("SUCCES : Livre ajoute.");

        //TEST 2 : Ajout d'un periodique valide
        System.out.println("\n[Test 2] Ajout d'un periodique au catalogue :");
        catalogue.put(periodique.getNumero(), periodique);
        historique.add("Ajout du periodique : " + periodique.getTitre());
        System.out.println("SUCCES : Periodique ajoute.");

        //TEST 3 (Cas limite) : Ajout d'un document avec un numero deja existant
        System.out.println("\n[Test 3] Cas limite - Ajout d'un doublon de document (ID 101) :");
        Document livreDoublon = new Livre(101, "Autre Livre", auteur1, 100);
        if (catalogue.containsKey(livreDoublon.getNumero())) {
            System.out.println("CAS LIMITE - ERREUR : Un document avec le numero 101 existe deja.");
        } else {
            catalogue.put(livreDoublon.getNumero(), livreDoublon);
        }

        //TEST 4 : Inscription d'un premier adherent
        System.out.println("\n[Test 4] Inscription de l'adherent Alice :");
        boolean inscrit1 = adherents.add(adh1);
        if (inscrit1) {
            historique.add("Inscription de l'adherent : " + adh1.getNomComplet());
            System.out.println("SUCCES : Adherent inscrit.");
        }

        //TEST 5 : Inscription d'un second adherent
        System.out.println("\n[Test 5] Inscription de l'adherent Bob :");
        boolean inscrit2 = adherents.add(adh2);
        if (inscrit2) {
            historique.add("Inscription de l'adherent : " + adh2.getNomComplet());
            System.out.println("SUCCES : Adherent inscrit.");
        }

        //TEST 6 (Cas limite) : Inscription d'un adherent avec un ID en double
        System.out.println("\n[Test 6] Cas limite - Inscription d'un adherent avec ID en double (ID 1) :");
        boolean inscritDuplique = adherents.add(adhDuplique);
        if (!inscritDuplique) {
            System.out.println("CAS LIMITE - ERREUR : L'adherent avec l'ID 1 est deja inscrit.");
        }

        //TEST 7 : Emprunt nominal d'un document par un adherent inscrit
        System.out.println("\n[Test 7] Emprunt nominal du livre 101 par Alice :");
        Document docEmprunt = catalogue.get(101);
        if (docEmprunt != null && adherents.contains(adh1)) {
            boolean resultatEmprunt = docEmprunt.emprunter(adh1);
            if (resultatEmprunt) {
                historique.add("Emprunt du document 101 par " + adh1.getNomComplet());
            }
        }

        //TEST 8 (Cas limite) : Tentative de double emprunt du meme document
        System.out.println("\n[Test 8] Cas limite - Tentative de double emprunt du livre 101 par Bob :");
        if (docEmprunt != null && adherents.contains(adh2)) {
            docEmprunt.emprunter(adh2);
        }

        //TEST 9 (Cas limite) : Emprunt par un adherent non inscrit
        System.out.println("\n[Test 9] Cas limite - Emprunt par un non-inscrit (ID 99) :");
        Adherent nonInscrit = new Adherent(99, "Inconnu", "Test");
        if (!adherents.contains(nonInscrit)) {
            System.out.println("CAS LIMITE - ERREUR : L'adherent " + nonInscrit.getNomComplet() + " n'est pas inscrit.");
        }

        //TEST 10 : Retour valide du document emprunte
        System.out.println("\n[Test 10] Retour du livre 101 :");
        if (docEmprunt != null) {
            docEmprunt.retourner();
            historique.add("Retour du document 101");
        }

        //TEST 11 (Cas limite) : Retour d'un document qui est deja disponible
        System.out.println("\n[Test 11] Cas limite - Retour d'un document deja disponible :");
        if (docEmprunt != null) {
            docEmprunt.retourner();
        }

        //TEST 12 (Cas limite) : Recherche d'un document inexistant
        System.out.println("\n[Test 12] Cas limite - Recherche d'un document inexistant (ID 999) :");
        Document rechercheInexistante = catalogue.get(999);
        if (rechercheInexistante == null) {
            System.out.println("SUCCES : Aucun document trouve pour le numero 999.");
        }

        //Affichage final du Catalogue et de l'Historique
        System.out.println("\n--- CATALOGUE FINAL (Polymorphisme) ---");
        for (Document doc : catalogue.values()) {
            System.out.println(doc.toString() + " | Duree pret max : " + doc.dureeMaxPret() + " jours");
        }

        System.out.println("\n--- HISTORIQUE CHRONOLOGIQUE (ArrayList) ---");
        for (String action : historique) {
            System.out.println("- " + action);
        }
    }
}