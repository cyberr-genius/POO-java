import java.util.Objects;

public class Adherent {
    private final int id;
    private String nom;
    private String prenom;

    public Adherent(int id, String nom, String prenom) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'identifiant de l'adherent doit etre positif.");
        }
        if (nom == null || nom.isBlank() || prenom == null || prenom.isBlank()) {
            throw new IllegalArgumentException("Le nom et le prenom ne peuvent pas etre vides.");
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

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adherent adherent = (Adherent) o;
        return id == adherent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Adherent [ID=" + id + ", Nom=" + prenom + " " + nom + "]";
    }
}