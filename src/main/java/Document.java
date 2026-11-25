import java.util.Objects;
import java.util.Optional;

/**
 * Base commune des documents.
 * Invariants : numéro strictement positif et immuable, titre non vide,
 * et un document est soit disponible (emprunteur absent), soit emprunté par un seul adhérent.
 * L'état d'emprunt est stocké à un seul endroit (l'emprunteur) : pas de booléen redondant.
 */
public abstract class Document implements Empruntable {
    private final int numero;
    private final String titre;
    private Adherent emprunteur; // null quand le document est disponible

    protected Document(int numero, String titre) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Le numéro du document doit être positif.");
        }
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre du document ne peut pas être vide.");
        }
        this.numero = numero;
        this.titre = titre;
    }

    public int getNumero() { return numero; }
    public String getTitre() { return titre; }

    public Optional<Adherent> getEmprunteur() {
        return Optional.ofNullable(emprunteur);
    }

    @Override
    public boolean estDisponible() {
        return emprunteur == null;
    }

    @Override
    public void emprunter(Adherent adherent) throws DocumentIndisponibleException {
        // Toutes les vérifications avant toute modification : atomicité
        if (adherent == null) {
            throw new IllegalArgumentException("L'adhérent ne peut pas être null.");
        }
        if (emprunteur != null) {
            throw new DocumentIndisponibleException(numero);
        }
        emprunteur = adherent;
    }

    @Override
    public void retourner() throws DocumentNonEmprunteException {
        if (emprunteur == null) {
            throw new DocumentNonEmprunteException(numero);
        }
        emprunteur = null;
    }

    /** Durée maximale de prêt en jours (polymorphisme : dépend du type de document). */
    public abstract int dureePret();

    /** Libellé de statut partagé par les toString des sous-classes. */
    protected String libelleStatut() {
        return emprunteur == null ? "Disponible" : "Emprunté par " + emprunteur.getNomComplet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return numero == ((Document) o).numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Document [N°=" + numero + ", Titre='" + titre + "', Statut=" + libelleStatut() + "]";
    }
}
