    package fr.istic.aco.editor;

    public class EngineImpl implements Engine {
        private StringBuilder buffer;
        private String clipboard;
        private SelectionImpl selection ; 
        /*constructeur de la classe */
        public EngineImpl(){
            buffer= new StringBuilder();
            clipboard ="";
            selection = new SelectionImpl( buffer);
        }

        /**
         * Inserts a string in the buffer, which replaces the contents of the selection
         *
         * @param s the text to insert
         */
        @Override
        public void insert(String s) {
        buffer.append(s);
        int end =selection.getBeginIndex() +s.length();
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
            // TODO
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
            }
        
        delete();
        
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
                System.out.println("Clipboard updated to: " + clipboard);
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
            buffer.delete(selection.getBeginIndex(),selection.getEndIndex());
            selection.setEndIndex(selection.getBeginIndex());
        }
    }
