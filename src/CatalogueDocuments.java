import java.util.List;
import java.util.Optional;

/**
 * Contrat d'un catalogue de documents. ServicePret ne dépend que de cette interface.
 * Les numéros de documents sont uniques dans un catalogue.
 */
public interface CatalogueDocuments {

    /**
     * Ajoute un document.
     * Précondition : doc non null (sinon IllegalArgumentException).
     * Postcondition : le nombre de documents augmente de 1.
     * En cas d'échec, le catalogue n'est pas modifié.
     *
     * @throws DocumentDejaPresentException si le numéro existe déjà
     */
    void ajouterDocument(Document doc) throws DocumentDejaPresentException;

    /** @return le document de ce numéro, ou Optional vide s'il est absent (absence attendue) */
    Optional<Document> rechercherParNumero(int numero);

    /**
     * Recherche par sous-chaîne du titre, sans tenir compte de la casse.
     * @return une liste (éventuellement vide) ; vide si titre est null ou vide
     */
    List<Document> rechercherParTitre(String titre);

    int getNombreDocuments();

    /** @return une copie : la modifier n'affecte pas le catalogue */
    List<Document> tousLesDocuments();
}
