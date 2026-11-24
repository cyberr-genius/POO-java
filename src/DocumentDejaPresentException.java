/** Levée quand on ajoute au catalogue un document dont le numéro existe déjà. */
public class DocumentDejaPresentException extends BibliothequeException {
    private static final long serialVersionUID = 1L;

    public DocumentDejaPresentException(int numero) {
        super("Un document avec le numéro " + numero + " existe déjà.");
    }
}
