public abstract class Document implements Empruntable {
    private final int numero; // Identifiant unique et immuable
    private String titre;
    private Auteur auteur;
    private boolean disponible;
    private Adherent emprunteur;

    public Document(int numero, String titre, Auteur auteur) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Le numero du document doit etre positif.");
        }
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
        this.emprunteur = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public Adherent getEmprunteur() {
        return emprunteur;
    }

    @Override
    public boolean estDisponible() {
        return disponible;
    }

    @Override
    public boolean emprunter(Adherent adherent) {
        if (adherent == null) {
            throw new IllegalArgumentException("L'adherent ne peut pas etre null.");
        }
        if (!disponible) {
            System.out.println("CAS LIMITE - ECHEC : Le document '" + titre + "' est deja emprunte par " + emprunteur.getNomComplet() + ".");
            return false;
        }
        this.disponible = false;
        this.emprunteur = adherent;
        System.out.println("SUCCES : Le document '" + titre + "' a ete emprunte par " + adherent.getNomComplet() + ".");
        return true;
    }

    @Override
    public void retourner() {
        if (!disponible) {
            System.out.println("SUCCES : Le document '" + titre + "' a ete rendu par " + emprunteur.getNomComplet() + ".");
            this.disponible = true;
            this.emprunteur = null;
        } else {
            System.out.println("CAS LIMITE - INFORMATION : Le document '" + titre + "' est deja disponible.");
        }
    }

    // Methode abstraite pour le polymorphisme
    public abstract int dureeMaxPret();

    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String statut = disponible ? "Disponible" : "Emprunte par " + (emprunteur != null ? emprunteur.getNomComplet() : "inconnu");
        return "Document [N=" + numero + ", Titre='" + titre + "', Auteur=" + auteur.getNom() + " " + auteur.getPrenom() + ", Statut=" + statut + "]";
    }
}