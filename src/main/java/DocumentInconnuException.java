/** Levée quand un prêt vise un document absent du catalogue. */
public class DocumentInconnuException extends BibliothequeException {
    private static final long serialVersionUID = 1L;

    public DocumentInconnuException(int numero) {
        super("Aucun document N°" + numero + " dans le catalogue.");
    }
}
