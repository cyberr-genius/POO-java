/** Périodique (revue, journal) identifié par son numéro de parution. */
public class Periodique extends Document {
    private final int numeroParution;

    public Periodique(int numero, String titre, int numeroParution) {
        super(numero, titre);
        if (numeroParution <= 0) {
            throw new IllegalArgumentException("Le numéro de parution doit être supérieur à 0.");
        }
        this.numeroParution = numeroParution;
    }

    public int getNumeroParution() { return numeroParution; }

    @Override
    public int dureePret() {
        return 7;
    }

    @Override
    public String toString() {
        return "Periodique [N°=" + getNumero() + ", Titre='" + getTitre() + "', N° Parution="
             + numeroParution + ", Statut=" + libelleStatut() + "]";
    }
}
