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
        selection = new SelectionImpl();
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
        System.out.println(engine.getBufferContents());
        // Avant d'exécuter la commande de sélection
        System.out.println("Sélection avant commande copy: Begin Index: " + selection.getBeginIndex() + ", End Index: "
                + selection.getEndIndex());

        // Avant d'exécuter la commande de copie
        invoker.setBeginIndex(0);
        selection.setEndIndex(2); // Sélectionne tout le texte disponible
        invoker.playCommand("selection");

        System.out.println("Indices de sélection avant copie: Begin Index: " + selection.getBeginIndex()
                + ", End Index: " + selection.getEndIndex());
        invoker.playCommand("copy");
        System.out.println("Clipboard après copy: " + engine.getClipboardContents());

        assertEquals("ab", engine.getClipboardContents());

    }

}
