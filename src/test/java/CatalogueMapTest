import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogueMapTest {
    private CatalogueMap catalogue;
    private Livre livre;
    private Periodique revue;

    @BeforeEach
    void init() throws DocumentDejaPresentException {
        catalogue = new CatalogueMap();
        livre = new Livre(101, "Le monde s'effondre", List.of(new Auteur(1, "Achebe", "Chinua")));
        revue = new Periodique(102, "Jeune Afrique", 1500);
        catalogue.ajouterDocument(livre);
        catalogue.ajouterDocument(revue);
    }

    @Test
    void ajout_puis_recherche_par_numero_retrouve_le_meme_document() {
        assertEquals(2, catalogue.getNombreDocuments());
        assertSame(livre, catalogue.rechercherParNumero(101).get());
    }

    @Test
    void recherche_d_un_numero_absent_renvoie_un_optional_vide() {
        assertEquals(Optional.empty(), catalogue.rechercherParNumero(999));
    }

    @Test
    void ajout_d_un_doublon_leve_une_exception_et_laisse_le_catalogue_inchange() {
        Document doublon = new Periodique(101, "Autre titre", 5);
        assertThrows(DocumentDejaPresentException.class, () -> catalogue.ajouterDocument(doublon));
        assertEquals(2, catalogue.getNombreDocuments());
        assertSame(livre, catalogue.rechercherParNumero(101).get());
    }

    @Test
    void recherche_par_titre_ignore_la_casse_et_accepte_une_sous_chaine() {
        List<Document> resultats = catalogue.rechercherParTitre("AFRIQUE");
        assertEquals(1, resultats.size());
        assertSame(revue, resultats.get(0));
    }

    @Test
    void la_liste_renvoyee_par_tousLesDocuments_est_une_copie() {
        catalogue.tousLesDocuments().clear();
        assertEquals(2, catalogue.getNombreDocuments());
    }
}
