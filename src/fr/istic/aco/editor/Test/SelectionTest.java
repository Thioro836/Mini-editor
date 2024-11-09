package fr.istic.aco.editor.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.ClasseImpl.EngineImpl;
import fr.istic.aco.editor.ClasseImpl.SelectionImpl;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

import static org.junit.jupiter.api.Assertions.*;
public class SelectionTest {
    private Selection selection;
    private Engine engine;
    private  StringBuilder b;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine=new EngineImpl();
        selection = engine.getSelection();

    }

    private void todo() {
        fail("Unimplemented test");
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
         b = new StringBuilder("123456");
         selection =new SelectionImpl(b);
        assertEquals(selection.getBufferEndIndex(), b.length(),"L'indice de fin du tampon devrait être égal à la longueur du StringBuilder.");


    }
    @Test
    @DisplayName("Test de mise à jour de l'indice de fin")
    void testEndIndex(){
        int newEndIndex=2;
         b = new StringBuilder("1234");
         selection =new SelectionImpl(b);
        selection.setEndIndex(newEndIndex);
        assertEquals(selection.getEndIndex(),newEndIndex,"L'indice de fin n'est pas correctement mis à jour");
    }
    @Test
    @DisplayName("Test de mise à jour de l'indice de début")
    void testBeginIndex(){
        int begin=2, end =3;
         b = new StringBuilder("1234");
         selection =new SelectionImpl(b);
         selection.setBeginIndex(begin);
         selection.setEndIndex(end);
        assertEquals(selection.getBeginIndex(), begin, "L'indice de début n'est pas correctement mis à jour");
        
    }
    @Test
    @DisplayName("Test d'erreur si l'indice de début est négatif")
    void testBeginIndexNegative(){
         b = new StringBuilder("1234");
         selection =new SelectionImpl(b);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            selection.setBeginIndex(selection.getBufferBeginIndex() - 1); // Déclenchement de l'exception attendu
        });
    }
    @Test
    @DisplayName("Test d'erreur si l'indice de fin est inférieur à l'indice de début")
    void testEndIndexLessThanBeginIndex() {
     b = new StringBuilder("1234");
     selection = new SelectionImpl(b);
     selection.setBeginIndex(2);
    assertThrows(IndexOutOfBoundsException.class, () -> {
        selection.setEndIndex(1); // Déclenchement de l'exception attendu car endIndex < beginIndex
    });
}

    
}
