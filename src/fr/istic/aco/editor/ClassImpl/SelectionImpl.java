package fr.istic.aco.editor.ClassImpl;

import fr.istic.aco.editor.Interface.Selection;

/**
 * Implementation of the Selection interface, representing a selection in the
 * buffer.
 * Provides methods for accessing and modifying the start and end indices of the
 * selection,
 * as well as managing the buffer's bounds.
 */

public class SelectionImpl implements Selection {

    private StringBuilder buffer;
    private Integer beginIndex;
    private Integer endIndex;
    private Integer bufferBeginIndex;

    /**
     * Default constructor initializing an empty selection with default indices.
     */

    public SelectionImpl() {
        this.buffer = new StringBuilder();
        this.beginIndex = 0;
        this.endIndex = 0;
        this.bufferBeginIndex = 0;

    }

    /**
     * Constructor that initializes the selection with a provided buffer.
     *
     * @param buffer the buffer associated with this selection
     */

    public SelectionImpl(StringBuilder buffer) {
        this.buffer = buffer;
        this.beginIndex = 0;
        this.endIndex = 0;
        this.bufferBeginIndex = 0;
    }

    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return the begin index
     */
    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the end index
     */
    public int getEndIndex() {
        return endIndex;

    }

    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    public int getBufferBeginIndex() {
        return bufferBeginIndex;

    }

    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */

    public int getBufferEndIndex() {
        return bufferBeginIndex + buffer.length();
    }

    /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    public void setBeginIndex(int beginIndex) {
        if (beginIndex < bufferBeginIndex) {
            throw new IndexOutOfBoundsException("Begin index out of bounds");
        }
        this.beginIndex = beginIndex;
    }

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */

    public void setEndIndex(int endIndex) {
        if (endIndex < beginIndex || endIndex > getBufferEndIndex()) {
            throw new IndexOutOfBoundsException(
                    "End index must be greater than or equal to beginIndex and within buffer range");
        }
        this.endIndex = endIndex;
    }

}
