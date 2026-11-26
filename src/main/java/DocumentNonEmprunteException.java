/** Levée quand on retourne un document qui n'est pas emprunté (ou pas par cet adhérent). */
public class DocumentNonEmprunteException extends BibliothequeException {
    private static final long serialVersionUID = 1L;

    public DocumentNonEmprunteException(int numero) {
        super("Le document N°" + numero + " n'est pas emprunté.");
    }

    public DocumentNonEmprunteException(int numero, Adherent adherent) {
        super("Le document N°" + numero + " n'est pas emprunté par " + adherent.getNomComplet() + ".");
    }
}
