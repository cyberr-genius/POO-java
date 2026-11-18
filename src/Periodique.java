public class Periodique extends Document {
    private int numeroParution;

    public Periodique(int numero, String titre, Auteur auteur, int numeroParution) {
        super(numero, titre, auteur);
        if (numeroParution <= 0) {
            throw new IllegalArgumentException("Le numero de parution doit etre positif.");
        }
        this.numeroParution = numeroParution;
    }

    public int getNumeroParution() {
        return numeroParution;
    }

    @Override
    public int dureeMaxPret() {
        return 7; // 7 jours pour un periodique
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Periodique [N° Parution=" + numeroParution + "]";
    }
}