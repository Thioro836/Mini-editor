package fr.istic.aco.editor;

public class InsertMemento implements Memento {
    private String text;
    public InsertMemento(){
        this.text="";
    }
    // Getter to retrieve the text to insert
    public String getMemento(){
        return text;
    }
    // Setter to define the text to insert before executing the insert command
    public void setMemento(String t){
        this.text=t;
    }

}
