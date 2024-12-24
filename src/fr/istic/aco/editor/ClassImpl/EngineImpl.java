package fr.istic.aco.editor.ClassImpl;

import fr.istic.aco.editor.CommandOriginator.DeleteCommand;
import fr.istic.aco.editor.CommandOriginator.InsertCommand;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Recorder;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.EditorMemento;

public class EngineImpl implements Engine {
    private StringBuilder buffer;
    private String clipboard;
    private SelectionImpl selection;
    private int begin, end;
    private UndoManager undoManager;
    

    /* constructeur de la classe */
    public EngineImpl() {
        buffer = new StringBuilder();
        clipboard = "";
        selection = new SelectionImpl(buffer);
        undoManager = new UndoManager(this);
        
      
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
        //undoManager.store();
        buffer.replace(selection.getBeginIndex(), selection.getEndIndex(), s);
        int end = selection.getBeginIndex() + s.length();
        selection.setBeginIndex(end);
        selection.setEndIndex(end);

    }

    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {

        return selection;
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override

    public String getBufferContents() {
        return buffer.toString();
    }
    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        return clipboard.toString();
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        if (selection.getBeginIndex() >= 0 && selection.getEndIndex() <= buffer.length()) {
            clipboard = buffer.substring(selection.getBeginIndex(), selection.getEndIndex());
            delete();
        }

    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        if (selection.getBeginIndex() >= 0 && selection.getEndIndex() <= buffer.length()) {
            clipboard = buffer.substring(selection.getBeginIndex(), selection.getEndIndex());
        } else {
            System.out.println("Indices de sélection incorrects pour la copie");
        }
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        undoManager.store();
        int insertPos = selection.getBeginIndex();
        buffer.insert(insertPos, clipboard);
        int newEndIndex = insertPos + clipboard.length();
        selection.setBeginIndex(newEndIndex);
        selection.setEndIndex(newEndIndex);

    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
       undoManager.store();
        buffer.delete(selection.getBeginIndex(), selection.getEndIndex());
        selection.setEndIndex(selection.getBeginIndex());
    }

    @Override
    public Memento getMemento() {
        String currentBuffer = buffer.toString();
        String currentClipboard = clipboard != null ? clipboard : "";
        return new EditorMemento(
            currentBuffer,
            selection.getBeginIndex(),
            selection.getEndIndex(),
            currentClipboard
        );
    }

    @Override
    public void setMemento(Memento m) {
        if (m == null) {
            System.out.println("Warning: Null memento received");
            return;
        }
        
        EditorMemento editorMemento = (EditorMemento) m;
        String content = editorMemento.getBufferContent();
        if (content == null) {
            System.out.println("Warning: Null buffer content in memento");
            content = "";
        }
        
        System.out.println("SetMemento: Restoring buffer to: '" + content + "'");
        
        buffer.setLength(0);
        buffer.append(content);
        selection.setBeginIndex(editorMemento.getBeginIndex());
        selection.setEndIndex(editorMemento.getEndIndex());
        this.clipboard = editorMemento.getClipboardContent();
        
        System.out.println("Buffer content after restoration: '" + buffer.toString() + "'");
    }

}
