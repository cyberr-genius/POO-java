public class Document {
    private final int numero; // Identifiant unique et immuable
    private String titre; // titre du document
    private Auteur auteur; //auteur du document 
    private boolean disponible; // État de disponibilité

    //constructeur
    public Document(int numero, String titre, Auteur auteur) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre du document ne peut pas être vide.");
        }
        if (auteur == null) {
            throw new IllegalArgumentException("Le document doit avoir un auteur.");
        }
        this.numero = numero;
        this.titre = titre;
        this.auteur = auteur;
        this.disponible = true; 
    }

    // Accesseurs
    public int getNumero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    
    public boolean estDisponible() {
        return disponible;
    }

    public void changerDisponibilite(boolean disponible) {
        this.disponible = disponible;
    }

    // affiche les informations du document
    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
    String statut;
    if (disponible) {
        statut = "Disponible";
    } else {
        statut = "emprunté";
    }
    
    return "Document [N=" + numero + ", Titre='" + titre + "', Auteur=" + auteur.getNom() + " " + auteur.getPrenom() + ", Statut=" + statut + "]";
    }
}
