package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private Engine engine;
    private Invoker invoker; // Instance d'Invoker
    private Selection selection;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
        invoker = new Invoker(engine, selection);
    }

    private void todo() {
        fail("Unimplemented test");
    }

    @Test
    void insertCommandTest() {
        // in the beginning buffer is empty
        assertEquals("", engine.getBufferContents());

        // Define the text to insert before executing the insert command
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");
        // Check that the text "abcde" has been inserted into the buffer
        assertEquals("abcde", invoker.getTextToInsert());
        assertEquals("abcde", engine.getBufferContents());
        // repeat the process
        invoker.setTextToInsert("fgh");
        invoker.playCommand("insert");

        assertEquals("fgh", invoker.getTextToInsert());
        assertEquals("abcdefgh", engine.getBufferContents());
    }

    @Test
    void SelectionCommandTest() {
        // Define the text to insert before executing the selection command
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");

        // Setting the selection indices
        invoker.setBeginIndex(2); // Index 2
        invoker.setEndIndex(4); // Index 4

        invoker.playCommand("selection");

        assertEquals(2, selection.getBeginIndex());
        assertEquals(4, selection.getEndIndex());
    }

    @Test
    void CopyCommandTest() {
        invoker.setTextToInsert("abcd");
          invoker.playCommand("insert");
        // Avant d'exécuter la commande de copie
        invoker.setBeginIndex(0);
        invoker.setEndIndex(2); // Sélectionne tout le texte disponible
        invoker.playCommand("selection");

        invoker.playCommand("copy");

        assertEquals("ab", engine.getClipboardContents());
        assertEquals("abcd", engine.getBufferContents());

    }

    @Test
    void cutCommandTest() {
        invoker.setTextToInsert("abcdefg");
        invoker.playCommand("insert");

        // Avant d'exécuter la commande de copie
        invoker.setBeginIndex(2);
        invoker.setEndIndex(5);
        invoker.playCommand("selection");

        invoker.playCommand("cut");

        assertEquals("cde", engine.getClipboardContents());
        assertEquals("abfg", engine.getBufferContents());

    }
    @Test 
    void pasteCommandTest(){
        //insérer dans le buffer
        invoker.setTextToInsert("abcdef");
        invoker.playCommand("insert");
        //faire la sélection
        invoker.setBeginIndex(2);
        invoker.setEndIndex(4);
        invoker.playCommand("selection");
        //copier le texte
        invoker.playCommand("copy");
        //coller le texte a cette position
        invoker.setBeginIndex(0);
        invoker.setEndIndex(2);
        invoker.playCommand("selection");
        //appeler la commande pour coller le texte
        invoker.playCommand("delete");
        invoker.playCommand("paste");
        assertEquals("cd", engine.getClipboardContents());
        assertEquals("cdcdef", engine.getBufferContents());
        //assertEquals(invoker.getBeginIndex(), 2);
        assertEquals(invoker.getEndIndex(), 2);

    }
    @Test
    void deleteCommandTest(){
        invoker.setTextToInsert("abcde");
        invoker.playCommand("insert");
        //selectionner le texte à supprimer
        invoker.setBeginIndex(2);
        invoker.setEndIndex(4);
        invoker.playCommand("selection");
        //supprimer le texte
        invoker.playCommand("delete");
        assertEquals("abe", engine.getBufferContents());
        assertEquals(2, invoker.getBeginIndex());
        //assertEquals(2,invoker.getEndIndex());
    }
}
