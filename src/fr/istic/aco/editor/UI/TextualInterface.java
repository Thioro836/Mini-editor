package fr.istic.aco.editor.UI;

import java.util.Scanner;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code TextualInterface} class provides a textual user interface for
 * interacting with a text editor.
 * This class allows the user to issue commands such as inserting text, cutting,
 * copying, pasting,
 * selecting portions of text, starting/stopping recording, and replaying
 * recorded commands.
 * <p>
 * It uses the {@link Invoker} class to execute commands and the {@link Engine}
 * class to manage
 * the text buffer and clipboard contents.
 * </p>
 */
public class TextualInterface {
    private Invoker invoker;
    private Scanner scanner;
    private Engine engine;

    /**
     * Constructs a new {@code TextualInterface}.
     * 
     * @param invoker the {@link Invoker} instance used to execute commands
     * @param engine  the {@link Engine} instance used to manage the buffer and
     *                clipboard
     */

    public TextualInterface(Invoker invoker, Engine engine) {
        this.invoker = invoker;
        this.engine = engine;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the textual interface and continuously prompts the user for commands.
     * The loop continues until the user chooses to exit.
     * Available commands include insert, cut, copy, paste, selection, start, stop,
     * replay, and exit.
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
                case "exit":
                    System.out.println("Thanks for using the editor. Goodbye!");
                    return;
                default:
                    System.out.println("Command not recognized, please try again.");
            }
        }
    }

    /**
     * Handles the "insert" command by prompting the user to enter text to insert
     * into the buffer.
     */
    private void handleInsert() {
        System.out.print("Enter the text to insert: ");
        String text = scanner.nextLine();
        invoker.setTextToInsert(text);
        invoker.playCommand("insert");
        showText();
    }

    /**
     * Handles the "selection" command by prompting the user to enter the start and
     * end indices
     * for text selection.
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
     * Displays the current contents of the text buffer.
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
