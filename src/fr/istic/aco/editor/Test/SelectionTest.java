package fr.istic.aco.editor.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClassImpl.EngineImpl;
import fr.istic.aco.editor.ClassImpl.SelectionImpl;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

import static org.junit.jupiter.api.Assertions.*;
public class SelectionTest {
    private Selection selection;
    private Engine engine;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine=new EngineImpl();
        selection = engine.getSelection();

    }

   
    @Test
    @DisplayName("Test de création de la sélection par défaut")
    void createSelection(){
        assertEquals(selection.getBeginIndex(), 0,"L'indice de début devrait être 0 à la création.");
        assertEquals(selection.getEndIndex(), 0,"L'indice de fin devrait être 0 à la création.");
        assertEquals(selection.getBufferBeginIndex(), 0,"L'indice de début du tampon devrait être 0");
        assertEquals(selection.getBufferEndIndex(), 0,"L'indice de fin du tampon devrait être 0.");

    }
    @Test
    @DisplayName("Test de l'indice de fin du tampon")
    void testBufferEndIndex(){
        StringBuilder b = new StringBuilder("123456");
        Selection s =new SelectionImpl(b);
        assertEquals(s.getBufferEndIndex(), b.length(),"L'indice de fin du tampon devrait être égal à la longueur du StringBuilder.");


    }
    @Test
    @DisplayName("Test de mise à jour de l'indice de fin")
    void testEndIndex(){
        int newEndIndex=2;
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        s.setEndIndex(newEndIndex);
        assertEquals(s.getEndIndex(),newEndIndex,"L'indice de fin n'est pas correctement mis à jour");
    }
    @Test
    @DisplayName("Test de mise à jour de l'indice de début")
    void testBeginIndex(){
        int begin=2, end =3;
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        s.setBeginIndex(begin);
        s.setEndIndex(end);
        assertEquals(s.getBeginIndex(), begin, "L'indice de début n'est pas correctement mis à jour");
        
    }
    @Test
    @DisplayName("Test d'erreur si l'indice de début est négatif")
    void testBeginIndexNegative(){
        StringBuilder b = new StringBuilder("1234");
        Selection s =new SelectionImpl(b);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            s.setBeginIndex(s.getBufferBeginIndex() - 1); // Déclenchement de l'exception attendu
        });
    }
    // @Test
    // @DisplayName("Test d'erreur si l'indice de début dépasse la longueur du tampon")
    // void testBeginBiggerThanEnd(){
    //     StringBuilder b = new StringBuilder("1234");
    //     Selection s =new SelectionImpl(b);
    //     assertThrows(IndexOutOfBoundsException.class, () -> {
    //         s.setBeginIndex(20); // Déclenchement de l'exception attendu
    //     });

    // }
    @Test
    @DisplayName("Test d'erreur si l'indice de fin est inférieur à l'indice de début")
    void testEndIndexLessThanBeginIndex() {
    StringBuilder b = new StringBuilder("1234");
    Selection s = new SelectionImpl(b);
    s.setBeginIndex(2);
    assertThrows(IndexOutOfBoundsException.class, () -> {
        s.setEndIndex(1); // Déclenchement de l'exception attendu car endIndex < beginIndex
    });
}

    
}
