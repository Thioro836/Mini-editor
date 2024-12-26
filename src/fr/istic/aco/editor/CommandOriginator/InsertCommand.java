package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.ClassImpl.UndoManager;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Memento.InsertMemento;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The InsertCommand class encapsulates the logic for inserting text into the
 * editor.
 * It supports undo/redo functionality and command recording for replaying.
 */

public class InsertCommand implements CommandOriginator {
    private Engine engine;
    private Invoker inv;
    private String textToInsert;
    private Recorder recorder;
    private UndoManager undoManager;

    /**
     * Constructs an InsertCommand with the specified components.
     *
     * @param engine      the engine that performs editor operations
     * @param invoker     the invoker that provides user input
     * @param recorder    the recorder for saving and replaying commands
     * @param undoManager the undo manager for managing undo/redo operations
     */
    public InsertCommand(Engine engine, Invoker inv, Recorder recorder, UndoManager undoManager) {
        this.engine = engine;
        this.inv = inv;
        this.recorder = recorder;
        this.undoManager = undoManager;

    }

    /**
     * Executes the insert operation.
     * Retrieves the text to insert from the invoker (if not replaying), performs
     * the insertion,
     * stores the current state, and saves the command to the recorder.
     */
    @Override
    public void execute() {
        if (!recorder.isReplaying()) {
            this.textToInsert = inv.getTextToInsert();

        }

        engine.insert(textToInsert);
        undoManager.store();
        recorder.save(this);

    }

    /**
     * Creates a memento to capture the state of the command.
     *
     * @return a memento containing the text to insert
     */
    @Override
    public Memento getMemento() {
        return new InsertMemento(textToInsert);

    }

    /**
     * Restores the state of the command from the provided memento.
     *
     * @param memento the memento containing the saved state
     */
    @Override
    public void setMemento(Memento memento) {
        InsertMemento insertMemento = (InsertMemento) memento;
        this.textToInsert = insertMemento.getText();

    }

}
