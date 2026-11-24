import java.util.Objects;

/** Auteur d'un livre. Immuable : aucun setter. */
public class Auteur {
    private final int id;
    private final String nom;
    private final String prenom;

    public Auteur(int id, String nom, String prenom) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'identifiant de l'auteur doit être positif.");
        }
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'auteur ne peut pas être vide.");
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom de l'auteur ne peut pas être vide.");
        }
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id == ((Auteur) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
