public class Adherent {
    private final int identifiant; // Identifiant unique  immuable
    private String nom; //nom de l'adherent
    private String prenom; //prenom de l'adherent
    private String mail; //mail de l'adherent
    
    // Constructeur de la classe Adherent.
    public Adherent(int identifiant, String nom, String prenom, String mail) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'adhérent ne peut pas être vide");
        }
        this.identifiant = identifiant;
        this.nom = nom;

        if (prenom != null) {
            this.prenom = prenom;
        } else {
            this.prenom = "";
        }

        if (mail != null) {
            this.mail = mail;
        } else {
            this.mail = "";
        }
    }
    // Accesseurs
    public int getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    // classe emprunter document
    public boolean emprunter(Document doc) {
        if (doc == null) {
            System.out.println("Erreur : Document invalide.");
            return false;
        }

        if (doc.estDisponible()) {
            doc.changerDisponibilite(false);
            System.out.println("Succès : " + nom + " " + prenom + " a emprunté le document '" + doc.getTitre() + "'.");
            return true;
        } else {
            System.out.println("Échec emprunt : Le document " + doc.getTitre() + " est déjà emprunté !");
            return false;
        }
    }

    // classe retourner document
    public void retourner(Document doc) {
        if (doc == null) {
            System.out.println("Erreur : Document invalide.");
            return;
        }
        doc.changerDisponibilite(true);
        System.out.println("Retour effectué : " + nom + " " + prenom + " a rendu le document '" + doc.getTitre() + "'.");
    }

    // afficher l'adherent
    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Adherent [ID=" + identifiant + ", Nom=" + nom + " " + prenom + ", Mail=" + mail + "]";
    }
}
