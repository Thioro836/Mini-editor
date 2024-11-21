    package fr.istic.aco.editor.Memento;

import fr.istic.aco.editor.Interface.Memento;

public class EditorMemento implements Memento {
        private String bufferContent;
        private int beginIndex;
        private int endIndex;
        private String clipboard;

         public EditorMemento(String buffer, int begin,int end, String clipboard){
            this.bufferContent=buffer;
            this.beginIndex=begin;
            this.endIndex=end;
         }
         public String getBufferContent(){
            return bufferContent.toString();
         
         }
         //demander à adrien si nécessaire
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
            return clipboard.toString();
         
         }
          //demander à adrien si nécessaire
         public void setClipboardContent(String clipboard){
            this.clipboard=clipboard;
         }
    }
