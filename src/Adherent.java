import java.util.Objects;

/** Adhérent de la bibliothèque. Immuable : le prêt est géré par ServicePret et Document. */
public class Adherent {
    private final int id;
    private final String nom;
    private final String prenom;

    public Adherent(int id, String nom, String prenom) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'identifiant de l'adhérent doit être positif.");
        }
        if (nom == null || nom.trim().isEmpty() || prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom et le prénom ne peuvent pas être vides.");
        }
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getNomComplet() { return prenom + " " + nom; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id == ((Adherent) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Adherent [ID=" + id + ", Nom=" + getNomComplet() + "]";
    }
}
