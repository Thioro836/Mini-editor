package fr.istic.aco.editor.Memento;

import java.util.Objects;

import fr.istic.aco.editor.Interface.Memento;

/**
 * The {@code EditorMemento} class is an implementation of the {@link Memento}
 * interface.
 * It captures and stores the state of the text editor, including the buffer
 * content, the selected text range,
 * and the clipboard content. This class is used to facilitate undo/redo
 * operations by storing snapshots of
 * the editor's state.
 * 
 * @author fatoumata bah
 * @version 3.0
 */
public class EditorMemento implements Memento {
   private String bufferContent;
   private int beginIndex;
   private int endIndex;
   private String clipboard;

   /**
    * Constructs a new {@code EditorMemento} with the specified state.
    * 
    * @param buffer    the content of the text buffer
    * @param begin     the start index of the current selection
    * @param end       the end index of the current selection
    * @param clipboard the content of the clipboard
    */
   public EditorMemento(String buffer, int begin, int end, String clipboard) {
      this.bufferContent = buffer;
      this.beginIndex = begin;
      this.endIndex = end;
      this.clipboard = clipboard != null ? clipboard : "";
   }

   /**
    * Returns the content of the text buffer stored in this memento.
    * 
    * @return the content of the text buffer
    */
   public String getBufferContent() {
      return bufferContent;

   }

   /**
    * Sets the content of the text buffer stored in this memento.
    * 
    * @param buffer the content to set for the text buffer
    */
   public void setBufferContent(String buffer) {
      this.bufferContent = buffer;
   }

   /**
    * Returns the start index of the selection stored in this memento.
    * 
    * @return the start index of the selection
    */
   public int getBeginIndex() {
      return beginIndex;
   }

   /**
    * Returns the end index of the selection stored in this memento.
    * 
    * @return the end index of the selection
    */
   public int getEndIndex() {
      return endIndex;
   }

   /**
    * Sets the start index of the selection stored in this memento.
    * 
    * @param beginIndex the start index to set for the selection
    */
   public void setBeginIndex(int beginIndex) {

      this.beginIndex = beginIndex;
   }

   /**
    * Sets the end index of the selection stored in this memento.
    * 
    * @param endIndex the end index to set for the selection
    */
   public void setEndIndex(int endIndex) {

      this.endIndex = endIndex;
   }

   /**
    * Returns the content of the clipboard stored in this memento.
    * 
    * @return the content of the clipboard
    */
   public String getClipboardContent() {
      return clipboard;

   }

   /**
    * Sets the content of the clipboard stored in this memento.
    * 
    * @param clipboard the content to set for the clipboard
    */
   public void setClipboardContent(String clipboard) {
      this.clipboard = clipboard;
   }

   /**
    * Compares this {@code EditorMemento} to another object for equality.
    * Two mementos are considered equal if their buffer content, selection indices,
    * and clipboard content are all the same.
    * 
    * @param obj the object to compare to
    * @return {@code true} if this memento is equal to the specified object,
    *         {@code false} otherwise
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null || getClass() != obj.getClass())
         return false;
      EditorMemento other = (EditorMemento) obj;
      return beginIndex == other.beginIndex &&
            endIndex == other.endIndex &&
            Objects.equals(bufferContent, other.bufferContent) &&
            Objects.equals(clipboard, other.clipboard);
   }

   /**
    * Returns the hash code value for this {@code EditorMemento}.
    * The hash code is computed based on the buffer content, selection indices,
    * and clipboard content.
    * 
    * @return the hash code value for this memento
    */
   @Override
   public int hashCode() {
      return Objects.hash(bufferContent, beginIndex, endIndex, clipboard);
   }
}
