package fr.istic.aco.editor.Memento;

import fr.istic.aco.editor.Interface.Memento;

/**
 * The {@code SelectMemento} class is a concrete implementation of the
 * {@link Memento} interface
 * used to store the state of a text selection within the editor.
 * This class encapsulates the indices marking the start and end of the selected
 * text range.
 */

public class SelectMemento implements Memento {
    private int beginIndex, endIndex;

    /**
     * Constructs a {@code SelectMemento} with the specified beginning and ending
     * indices
     * representing the selected text range.
     * 
     * @param begin the beginning index of the selection
     * @param end   the ending index of the selection
     */

    public SelectMemento(int begin, int end) {
        this.beginIndex = begin;
        this.endIndex = end;
    }

    /**
     * Gets the beginning index of the text selection.
     * 
     * @return the beginning index of the selection
     */

    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * Gets the ending index of the text selection.
     * 
     * @return the ending index of the selection
     */

    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Sets the beginning index of the text selection.
     * 
     * @param beginIndex the new beginning index of the selection
     */

    public void setBeginIndex(int beginIndex) {

        this.beginIndex = beginIndex;
    }

    /**
     * Sets the ending index of the text selection.
     * 
     * @param endIndex the new ending index of the selection
     */

    public void setEndIndex(int endIndex) {

        this.endIndex = endIndex;
    }

}
