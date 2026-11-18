import java.util.Objects;

public class Auteur {
    private final int id;
    private String nom;
    private String prenom;

    public Auteur(int id, String nom, String prenom) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'identifiant de l'auteur doit etre positif.");
        }
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'auteur ne peut pas etre vide.");
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prenom de l'auteur ne peut pas etre vide.");
        }
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auteur auteur = (Auteur) o;
        return id == auteur.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (ID: " + id + ")";
    }
}