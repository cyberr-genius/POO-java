import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ModeleTest {
    private final Auteur achebe = new Auteur(1, "Achebe", "Chinua");

    @Test
    void adherent_avec_nom_vide_est_refuse() {
        assertThrows(IllegalArgumentException.class, () -> new Adherent(1, "  ", "Moussa"));
    }

    @Test
    void document_avec_titre_vide_est_refuse() {
        assertThrows(IllegalArgumentException.class, () -> new Livre(1, "   ", List.of(achebe)));
    }

    @Test
    void livre_sans_auteur_est_refuse() {
        assertThrows(IllegalArgumentException.class, () -> new Livre(1, "Titre", List.of()));
        assertThrows(IllegalArgumentException.class, () -> new Livre(1, "Titre", null));
    }

    @Test
    void livre_copie_la_liste_d_auteurs_recue() {
        List<Auteur> auteurs = new ArrayList<>(List.of(achebe));
        Livre livre = new Livre(1, "Titre", auteurs);
        auteurs.add(new Auteur(2, "Sembène", "Ousmane"));
        assertEquals(1, livre.getAuteurs().size());
    }

    @Test
    void documents_de_meme_numero_sont_egaux_avec_le_meme_hashCode() {
        Document a = new Livre(101, "Une édition", List.of(achebe));
        Document b = new Livre(101, "Une autre édition", List.of(achebe));
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void duree_de_pret_depend_du_type_via_une_reference_Document() {
        Document livre = new Livre(1, "Titre", List.of(achebe));
        Document revue = new Periodique(2, "Revue", 10);
        assertEquals(14, livre.dureePret());
        assertEquals(7, revue.dureePret());
    }
}
