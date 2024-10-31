package fr.istic.aco.editor;

public interface CommandOriginator extends Command {
    Memento getMemento();
    void setMemento(Memento memento);

}
