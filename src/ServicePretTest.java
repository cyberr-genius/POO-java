import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicePretTest {
    private CatalogueMap catalogue;
    private ServicePret service;
    private Livre livre;
    private Periodique revue;
    private Adherent karaba;
    private Adherent kirikou;

    @BeforeEach
    void init() throws DocumentDejaPresentException {
        catalogue = new CatalogueMap();
        service = new ServicePret(catalogue);
        livre = new Livre(101, "Le monde s'effondre", List.of(new Auteur(1, "Achebe", "Chinua")));
        revue = new Periodique(102, "Jeune Afrique", 1500);
        catalogue.ajouterDocument(livre);
        catalogue.ajouterDocument(revue);
        karaba = new Adherent(1, "LaSorciere", "Karaba");
        kirikou = new Adherent(2, "LePetit", "Kirikou");
    }

    // --- cas nominaux ---

    @Test
    void emprunter_un_document_disponible_le_rend_indisponible_pour_cet_adherent() throws Exception {
        service.emprunter(101, karaba);
        assertFalse(livre.estDisponible());
        assertEquals(karaba, livre.getEmprunteur().get());
    }

    @Test
    void retourner_un_document_emprunte_le_rend_disponible() throws Exception {
        service.emprunter(101, karaba);
        service.retourner(101, karaba);
        assertTrue(livre.estDisponible());
        assertTrue(livre.getEmprunteur().isEmpty());
    }

    @Test
    void un_document_retourne_peut_etre_emprunte_par_un_autre_adherent() throws Exception {
        service.emprunter(101, karaba);
        service.retourner(101, karaba);
        service.emprunter(101, kirikou);
        assertEquals(kirikou, livre.getEmprunteur().get());
    }

    // --- cas d'échec : l'état est vérifié après l'exception ---

    @Test
    void emprunter_un_document_deja_emprunte_leve_une_exception_et_conserve_le_premier_emprunteur()
            throws Exception {
        service.emprunter(101, karaba);
        assertThrows(DocumentIndisponibleException.class, () -> service.emprunter(101, kirikou));
        assertEquals(karaba, livre.getEmprunteur().get());
    }

    @Test
    void emprunter_un_document_inconnu_leve_une_exception_et_ne_modifie_aucun_document() {
        assertThrows(DocumentInconnuException.class, () -> service.emprunter(999, karaba));
        assertTrue(livre.estDisponible());
        assertTrue(revue.estDisponible());
        assertEquals(2, catalogue.getNombreDocuments());
    }

    @Test
    void emprunter_avec_un_adherent_null_est_refuse_et_le_document_reste_disponible() {
        assertThrows(IllegalArgumentException.class, () -> service.emprunter(101, null));
        assertTrue(livre.estDisponible());
    }

    @Test
    void retourner_un_document_non_emprunte_leve_une_exception_et_il_reste_disponible() {
        assertThrows(DocumentNonEmprunteException.class, () -> service.retourner(101, karaba));
        assertTrue(livre.estDisponible());
    }

    @Test
    void retourner_par_un_autre_adherent_leve_une_exception_et_conserve_l_emprunt() throws Exception {
        service.emprunter(101, karaba);
        assertThrows(DocumentNonEmprunteException.class, () -> service.retourner(101, kirikou));
        assertEquals(karaba, livre.getEmprunteur().get());
    }

    @Test
    void retourner_un_document_inconnu_leve_une_exception_et_ne_modifie_aucun_document() {
        assertThrows(DocumentInconnuException.class, () -> service.retourner(999, karaba));
        assertTrue(livre.estDisponible());
        assertTrue(revue.estDisponible());
    }
}
