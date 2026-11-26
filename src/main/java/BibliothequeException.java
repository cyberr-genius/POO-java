/**
 * Classe mère des échecs métier de la bibliothèque (exception vérifiée).
 * Le client peut l'attraper pour tout traiter d'un coup, ou attraper
 * une sous-classe pour distinguer les cas.
 * Les données invalides (null, titre vide...) restent des IllegalArgumentException.
 */
public class BibliothequeException extends Exception {
    private static final long serialVersionUID = 1L;

    public BibliothequeException(String message) {
        super(message);
    }
}
