/**
 * Service de prêt : orchestre les emprunts et retours.
 * Dépend de l'interface CatalogueDocuments, pas de son implémentation.
 *
 * Atomicité : chaque opération fait toutes ses vérifications (adhérent, document connu,
 * état du document) AVANT l'unique modification d'état, qui est la dernière instruction.
 * Un échec laisse donc le système inchangé.
 */
public class ServicePret {
    private final CatalogueDocuments catalogue;

    public ServicePret(CatalogueDocuments catalogue) {
        if (catalogue == null) {
            throw new IllegalArgumentException("Le catalogue ne peut pas être null.");
        }
        this.catalogue = catalogue;
    }

    /**
     * @throws IllegalArgumentException     si adherent est null (donnée invalide)
     * @throws DocumentInconnuException     si le numéro n'est pas au catalogue
     * @throws DocumentIndisponibleException si le document est déjà emprunté
     */
    public void emprunter(int numeroDocument, Adherent adherent)
            throws DocumentInconnuException, DocumentIndisponibleException {
        if (adherent == null) {
            throw new IllegalArgumentException("L'adhérent ne peut pas être null.");
        }
        Document doc = trouver(numeroDocument);
        doc.emprunter(adherent);
    }

    /**
     * Seul l'adhérent qui a emprunté le document peut le retourner.
     *
     * @throws IllegalArgumentException    si adherent est null
     * @throws DocumentInconnuException    si le numéro n'est pas au catalogue
     * @throws DocumentNonEmprunteException si le document n'est pas emprunté par cet adhérent
     */
    public void retourner(int numeroDocument, Adherent adherent)
            throws DocumentInconnuException, DocumentNonEmprunteException {
        if (adherent == null) {
            throw new IllegalArgumentException("L'adhérent ne peut pas être null.");
        }
        Document doc = trouver(numeroDocument);
        if (!doc.getEmprunteur().filter(adherent::equals).isPresent()) {
            throw new DocumentNonEmprunteException(numeroDocument, adherent);
        }
        doc.retourner();
    }

    private Document trouver(int numeroDocument) throws DocumentInconnuException {
        return catalogue.rechercherParNumero(numeroDocument)
                .orElseThrow(() -> new DocumentInconnuException(numeroDocument));
    }
}
