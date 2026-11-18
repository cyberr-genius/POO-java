public class Livre extends Document {
    private int nombrePages;

    public Livre(int numero, String titre, Auteur auteur, int nombrePages) {
        super(numero, titre, auteur);
        if (nombrePages <= 0) {
            throw new IllegalArgumentException("Le nombre de pages doit etre superieur a 0.");
        }
        this.nombrePages = nombrePages;
    }

    public int getNombrePages() {
        return nombrePages;
    }

    @Override
    public int dureeMaxPret() {
        return 21; // 21 jours pour un livre
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Livre [Pages=" + nombrePages + "]";
    }
}