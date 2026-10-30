public class Main {
    public static void main(String[] args) {
        System.out.println("Tests de fonctionnement de la bibliothèque");

      //creation d'objets
        System.out.println("essai d'instanciation initiale des objets");
        Auteur auteur1 = new Auteur(1, "Achebe", "Chinue", "chinua.achebe@gmail.com");
        Auteur auteur2 = new Auteur(2, "Sembène", "Ousmane", "ousmane.sembene@gmail.com");

        Document doc1 = new Document(1, "Le monde s'éffondre", auteur1);
        Document doc2 = new Document(2, "Maimouna", auteur2);

        Adherent adh1 = new Adherent(1, "Karaba", "LaSorciere", "karaba@gmail.com");
        Adherent adh2 = new Adherent(2, "Kirikou", "LePetit", "kirikou@gmail.com");
        System.out.println("Vous avez créer les objets "+doc1.getTitre()+" et "+ doc2.getTitre() +"\n");
      
        //test d'affichages des objets
        System.out.println("tests d'affichage des objets créés ");
        doc1.afficher();
        doc2.afficher();
        adh1.afficher();
        adh2.afficher();
        System.out.println();

      // tests de la fonction emprunter avec paramètres valides
        System.out.println("essai d'Emprunt d'un document disponible ");
        System.out.println("statut initial du document : " + doc1);
        adh1.emprunter(doc1);
        System.out.println("Statut du document après l'emprunt : " + doc1);
        System.out.println();

        System.out.println("essai d'un deuxième emprunt du même document ");
        adh2.emprunter(doc1); 
        System.out.println("statut du document : " + doc1);
        System.out.println();
      
        //test de la fonction retourner avec paramètres valides
        System.out.println("test de Retour du document ");
        adh1.retourner(doc1);
        System.out.println("Statut du document après retour : " + doc1);
        System.out.println();

        //test d'exceptions avec des paramètres invalides paramètres 
        System.out.println("test  création d'un document avec un titre vide ");
        try {
            Document docInvalide = new Document(3, "  ", auteur1);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception de document avec titre vide:" + e.getMessage());
        }

        try {
            System.out.println("Tentative de création d'un adhérent sans prenom");
            Adherent adhInvalide = new Adherent(3, "kabore", null, "kabore@gmail.com");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception de document avec adhérent sans nom " + e.getMessage());
        }
        try {
            System.out.print("Test Adherent avec nom vide: ");
            new Adherent(2, "  ", "Jean", "jean@gmail.com");
            System.out.println("succès");
        } catch (IllegalArgumentException e) {
            System.out.println( e.getMessage());
        }
        //test d'emprunt d'un document inexistant
        System.out.print("Test emprunter(null) : ");
        adh2.emprunter(null);

        //  Retour d'un document inexistant
        System.out.print("Test retourner(null) : ");
        adh1.retourner(null);
        System.out.println("\n");
        System.out.println("fin des tests ");
    }
}
