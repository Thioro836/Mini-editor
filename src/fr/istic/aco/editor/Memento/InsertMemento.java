package fr.istic.aco.editor.Memento;

import fr.istic.aco.editor.Interface.Memento;

/**
 * The {@code InsertMemento} class is a concrete implementation of the
 * {@link Memento} interface
 * used to store the state of the text that is about to be inserted.
 * This class encapsulates the text string that needs to be inserted in the
 * editor.
 */
public class InsertMemento implements Memento {
    private String text;

    /**
     * Constructs an {@code InsertMemento} with the specified text to insert.
     * 
     * @param text the text to insert, which is stored in the memento
     */
    public InsertMemento(String text) {
        this.text = text;
    }

    /**
     * Gets the text stored in this memento.
     * 
     * @return the text to be inserted
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text in this memento.
     * 
     * @param text the new text to store in the memento
     */
    public void setText(String text) {
        this.text = text;
    }
}
