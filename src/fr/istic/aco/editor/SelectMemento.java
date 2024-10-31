    package fr.istic.aco.editor;

    public class SelectMemento implements Memento {
    private  int beginIndex, endIndex; 

    public SelectMemento(){
        this.beginIndex=0;
        this.endIndex=0;
    }
    // getteurs et setteurs pour la selection

    public int getBeginIndex() {
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

    }
