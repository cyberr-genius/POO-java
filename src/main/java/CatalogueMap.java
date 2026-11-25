import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Catalogue basé sur une Map numéro -> document.
 * Complexité : ajout et recherche par numéro en O(1) ; recherche par titre en O(n).
 * LinkedHashMap garde l'ordre d'insertion, donc des parcours reproductibles.
 */
public class CatalogueMap implements CatalogueDocuments {
    private final Map<Integer, Document> documents = new LinkedHashMap<>();

    @Override
    public void ajouterDocument(Document doc) throws DocumentDejaPresentException {
        if (doc == null) {
            throw new IllegalArgumentException("Le document à ajouter ne peut pas être null.");
        }
        if (documents.containsKey(doc.getNumero())) {
            throw new DocumentDejaPresentException(doc.getNumero());
        }
        documents.put(doc.getNumero(), doc);
    }

    @Override
    public Optional<Document> rechercherParNumero(int numero) {
        return Optional.ofNullable(documents.get(numero));
    }

    @Override
    public List<Document> rechercherParTitre(String titre) {
        List<Document> resultats = new ArrayList<>();
        if (titre == null || titre.trim().isEmpty()) {
            return resultats;
        }
        String motif = titre.trim().toLowerCase();
        for (Document doc : documents.values()) {
            if (doc.getTitre().toLowerCase().contains(motif)) {
                resultats.add(doc);
            }
        }
        return resultats;
    }

    @Override
    public int getNombreDocuments() {
        return documents.size();
    }

    @Override
    public List<Document> tousLesDocuments() {
        return new ArrayList<>(documents.values());
    }
}
