/** Levée quand on emprunte un document déjà emprunté. */
public class DocumentIndisponibleException extends BibliothequeException {
    private static final long serialVersionUID = 1L;

    public DocumentIndisponibleException(int numero) {
        super("Le document N°" + numero + " est déjà emprunté.");
    }
}
