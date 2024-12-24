    package fr.istic.aco.editor.Memento;

import fr.istic.aco.editor.Interface.Memento;

public class EditorMemento implements Memento {
        private String bufferContent;
        private int beginIndex;
        private int endIndex;
        private String clipboard;

         public EditorMemento(String buffer, int begin,int end, String clipboard){
            this.bufferContent = buffer != null ? buffer : "";
            this.beginIndex = begin;
            this.endIndex = end;
            this.clipboard = clipboard != null ? clipboard : "";
         }
         public String getBufferContent(){
            return bufferContent;
         
         }
      
         public void setBufferContent(String buffer){
            this.bufferContent=buffer;
         }
         public int getBeginIndex(){
            return beginIndex;
         }
         public int getEndIndex() {
            return endIndex;
        }
        public void setBeginIndex(int beginIndex) {
    
            this.beginIndex = beginIndex;
        }
    
        public void setEndIndex(int endIndex) {
    
            this.endIndex = endIndex;
        }

        public String getClipboardContent(){
            return clipboard;
         
         }
        
         public void setClipboardContent(String clipboard){
            this.clipboard=clipboard;
         }
    }
