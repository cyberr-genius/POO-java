/**
 * Contrat d'un objet pouvant être prêté.
 * L'état d'emprunt ne change que par ces deux opérations, qui préservent l'invariant :
 * un objet est soit disponible, soit emprunté par exactement un adhérent.
 */
public interface Empruntable {

    /** @return true si l'objet peut être emprunté */
    boolean estDisponible();

    /**
     * Marque l'objet comme emprunté par l'adhérent.
     * Précondition : adherent non null (sinon IllegalArgumentException).
     * Postcondition : l'objet est indisponible.
     * En cas d'échec, l'état n'est pas modifié.
     *
     * @throws DocumentIndisponibleException si l'objet est déjà emprunté
     */
    void emprunter(Adherent adherent) throws DocumentIndisponibleException;

    /**
     * Rend l'objet disponible.
     * Postcondition : l'objet est disponible.
     * En cas d'échec, l'état n'est pas modifié.
     *
     * @throws DocumentNonEmprunteException si l'objet n'était pas emprunté
     */
    void retourner() throws DocumentNonEmprunteException;
}
