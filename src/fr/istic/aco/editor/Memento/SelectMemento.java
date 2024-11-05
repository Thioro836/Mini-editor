    package fr.istic.aco.editor.Memento;

import fr.istic.aco.editor.Interface.Memento;

public class SelectMemento implements Memento {
    private  int beginIndex, endIndex; 

    public SelectMemento(int begin, int end){
        this.beginIndex=begin;
        this.endIndex=end;
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
