import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Livre composé de un ou plusieurs auteurs.
 * Invariant : la liste d'auteurs est non vide, sans élément null, et non modifiable de l'extérieur.
 */
public class Livre extends Document {
    private final List<Auteur> auteurs;

    public Livre(int numero, String titre, List<Auteur> auteurs) {
        super(numero, titre);
        if (auteurs == null || auteurs.isEmpty()) {
            throw new IllegalArgumentException("Un livre doit avoir au moins un auteur.");
        }
        List<Auteur> copie = new ArrayList<>(auteurs); // copie défensive
        if (copie.contains(null)) {
            throw new IllegalArgumentException("Un livre ne peut pas contenir d'auteur null.");
        }
        this.auteurs = List.copyOf(copie); // liste non modifiable
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    @Override
    public int dureePret() {
        return 14;
    }

    @Override
    public String toString() {
        String noms = auteurs.stream().map(Auteur::toString).collect(Collectors.joining(", "));
        return "Livre [N°=" + getNumero() + ", Titre='" + getTitre() + "', Auteurs=" + noms
             + ", Statut=" + libelleStatut() + "]";
    }
}
