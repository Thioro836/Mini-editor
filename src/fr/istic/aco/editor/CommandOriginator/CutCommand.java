package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Interface.Selection;
import fr.istic.aco.editor.Memento.SelectMemento;
import fr.istic.aco.editor.Interface.Recorder;

public class CutCommand implements CommandOriginator {
    private Engine engine;
    private Selection selection;
    private Recorder recorder;
    private boolean replay;

    public CutCommand(Engine engine, Selection selection, Recorder recorder) {
        this.engine = engine;
        this.selection = selection;
        this.recorder = recorder;
        this.replay = false;
    }

    @Override
    public void execute() {
        recorder.save(this);
        System.out.println("Indices de sélection avant coupure: Begin Index: " + selection.getBeginIndex()
                + ", End Index: " + selection.getEndIndex());
        engine.cutSelectedText();
        replay = false;
    }

    @Override
    public void setMemento(Memento memento) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }

}
