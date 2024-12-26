package fr.istic.aco.editor.UI;

import java.util.Scanner;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code TextualInterface} class represents a simple command-line interface
 * (CLI) for interacting
 * with the text editor. It allows the user to perform various actions such as
 * inserting, cutting, copying,
 * pasting text, managing selections, and executing undo/redo operations. The
 * interface interacts with the
 * underlying {@code Engine}, {@code UndoManager}, and {@code Invoker} to
 * process the commands entered by the user.
 * 
 * The class runs an interactive loop where the user is prompted to enter
 * commands. Each command triggers a
 * corresponding action on the editor, such as modifying text, updating the
 * selection, or manipulating the clipboard.
 * Additionally, the user can start and stop recording commands, replay recorded
 * commands, and perform undo/redo actions.
 * 
 */
public class TextualInterface {
    private Invoker invoker;
    private Scanner scanner;
    private Engine engine;
    private UndoManager undoManager;

    /**
     * Constructs a new {@code TextualInterface} with the specified components.
     * 
     * @param invoker     the {@code Invoker} responsible for executing commands
     * @param engine      the {@code Engine} that manages the text buffer and
     *                    selection
     * @param undoManager the {@code UndoManager} responsible for handling undo and
     *                    redo operations
     */
    public TextualInterface(Invoker invoker, Engine engine, UndoManager undoManager) {
        this.invoker = invoker;
        this.engine = engine;
        this.undoManager = undoManager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the command-line interface, prompting the user for commands and
     * executing the corresponding actions.
     * The loop continues until the user chooses to exit.
     */
    public void start() {
        System.out.println("Welcome to our text editor");

        while (true) {
            System.out.println("\nPlease choose a command:");
            System.out.println("1. insert - Insert text");
            System.out.println("2. cut - Cut selected text");
            System.out.println("3. copy - Copy selected text");
            System.out.println("4. paste - Paste text");
            System.out.println("5. selection - Select a portion of text");
            System.out.println("6. start - Start Recording");
            System.out.println("7. stop - Stop Recording");
            System.out.println("8. replay - Replay recorded commands");
            System.out.println("9. undo - Start Undo");
            System.out.println("10. redo - Start Redo");
            System.out.println("9. exit - Exit");

            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "insert":
                    handleInsert();
                    break;
                case "cut":
                    invoker.playCommand("cut");
                    showClipboard();
                    showText();
                    break;
                case "copy":
                    invoker.playCommand("copy");
                    showClipboard();
                    break;
                case "paste":
                    invoker.playCommand("paste");
                    showClipboard();
                    showText();
                    break;
                case "start":
                    invoker.playCommandConcrete("start");
                    System.out.println("Recording started.");
                    break;
                case "stop":
                    invoker.playCommandConcrete("stop");
                    System.out.println("Recording stopped.");
                    break;
                case "replay":
                    System.out.println("Replaying recorded commands...");
                    invoker.playCommandConcrete("replay");
                    showText();
                    showClipboard();
                    break;
                case "selection":
                    handleSelection();
                    break;
                case "undo":
                    System.out.println("Undo commands...");
                    invoker.playCommandConcrete("undo");
                    showText();
                    break;
                case "redo":
                    System.out.println("Redo commands...");
                    invoker.playCommandConcrete("redo");
                    showText();
                    break;
                case "exit":
                    System.out.println("Thanks for using the editor. Goodbye!");
                    return;
                default:
                    System.out.println("Command not recognized, please try again.");
            }
        }
    }

    /**
     * Handles the 'insert' command by prompting the user to enter text to insert
     * into the editor.
     */
    private void handleInsert() {
        System.out.print("Enter the text to insert: ");
        String text = scanner.nextLine();
        invoker.setTextToInsert(text);
        invoker.playCommand("insert");
        showText();
    }

    /**
     * Handles the 'selection' command by prompting the user to input the start and
     * end indices of the selection.
     */
    private void handleSelection() {
        System.out.print("Start of selection index: ");
        int begin = scanner.nextInt();
        System.out.print("End of selection index: ");
        int end = scanner.nextInt();
        scanner.nextLine();
        invoker.setBeginIndex(begin);
        invoker.setEndIndex(end);
        invoker.playCommand("selection");

    }

    /**
     * Displays the current content of the text buffer.
     */
    private void showText() {
        String currentText = engine.getBufferContents();
        System.out.println("Buffer content: " + currentText);
    }

    /**
     * Displays the current contents of the clipboard.
     */
    private void showClipboard() {
        String clipboardContent = engine.getClipboardContents();
        System.out.println("Clipboard content: " + clipboardContent);
    }

}
