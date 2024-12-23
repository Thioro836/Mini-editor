package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Memento.InsertMemento;
import fr.istic.aco.editor.Interface.Recorder;

/**
 * The {@code InsertCommand} class implements the {@link CommandOriginator}
 * interface
 * and is responsible for inserting text into the engine. It interacts with the
 * engine
 * to perform the insert operation, and uses the recorder to save the command
 * state
 * when recording is enabled.
 */
public class InsertCommand implements CommandOriginator {
    private Engine engine;
    private Invoker inv;
    private String textToInsert;
    private Recorder recorder;
    private boolean recording;

    /**
     * Constructs an {@code InsertCommand} with the specified engine, invoker, and
     * recorder.
     *
     * @param engine   the engine to perform the insert operation
     * @param inv      the invoker that provides the text to insert
     * @param recorder the recorder to save the state of the command when recording
     *                 is enabled
     */
    public InsertCommand(Engine engine, Invoker inv, Recorder recorder) {
        this.engine = engine;
        this.inv = inv;
        this.recorder = recorder;

    }

    /**
     * Executes the insert operation by invoking the engine's insert method with the
     * text
     * to insert from the invoker. If recording is enabled, it saves the command
     * state
     * using the recorder.
     */

    @Override
    public void execute() {
        engine.insert(inv.getTextToInsert());
        if (this.recording) {
            recorder.save(this);

        }
    }

    /**
     * Gets the memento for this command. It creates and returns an
     * {@code InsertMemento}
     * with the text to insert.
     *
     * @return an {@code InsertMemento} containing the text to insert
     */
    @Override
    public Memento getMemento() {
        return new InsertMemento(textToInsert);

    }

    /**
     * Sets the memento for this command. It extracts the text from the provided
     * {@code InsertMemento} and updates the {@code textToInsert} property.
     *
     * @param memento the memento to set, expected to be an instance of
     *                {@code InsertMemento}
     */
    @Override
    public void setMemento(Memento memento) {
        InsertMemento insertMemento = (InsertMemento) memento;
        this.textToInsert = insertMemento.getText();
    }

}
