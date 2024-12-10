package fr.istic.aco.editor.UI;

import java.util.Scanner;
import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

public class TextualInterface {
    private Invoker invoker;
    private Scanner scanner;
    private Engine engine;
    private UndoManager undoManager;

    public TextualInterface(Invoker invoker, Engine engine) {
        this.invoker = invoker;
        this.engine = engine;
        this.scanner = new Scanner(System.in);
    }

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
                    invoker.playCommand("undo");
                    showText();
                      break;
                case "redo":
                      System.out.println("Redo commands...");
                      invoker.playCommand("redo");
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

    private void handleInsert() {
        System.out.print("Enter the text to insert: ");
        String text = scanner.nextLine();
        invoker.setTextToInsert(text);
        invoker.playCommand("insert");
        showText();
    }

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

    private void showText() {
        String currentText = engine.getBufferContents();
        System.out.println("Buffer content: " + currentText);
    }

    private void showClipboard() {
        String clipboardContent = engine.getClipboardContents();
        System.out.println("Clipboard content: " + clipboardContent);
    }
    
}
