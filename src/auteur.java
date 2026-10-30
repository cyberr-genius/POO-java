public class Auteur {
    private final int identifiant; // Identifiant unique  immuable
    private String nom; //  nom de l'auteur du document 
    private String prenom; //prenom de l'auteur du document 
    private String mail; //mail de l'auteur du document 

     //Constructeur d'initialisation de l'objet Auteur.     
    public Auteur(int identifiant, String nom, String prenom, String mail) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'auteur ne peut pas être vide.");
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

    // Accesseurs (getters)
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

    // description de l'auteur 
    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Auteur [ID=" + identifiant + ", Nom=" + nom + " " + prenom + ", Mail=" + mail + "]";
    }
}
