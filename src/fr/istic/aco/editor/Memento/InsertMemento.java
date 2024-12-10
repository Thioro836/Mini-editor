package fr.istic.aco.editor.Memento;

import fr.istic.aco.editor.Interface.Memento;

public class InsertMemento implements Memento {
    private String text;
    public InsertMemento(String text){
        this.text=text;
    }
    // Getter to retrieve the text to insert
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text=text;
    }
}
