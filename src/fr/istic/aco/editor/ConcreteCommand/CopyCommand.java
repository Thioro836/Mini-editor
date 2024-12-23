package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Selection;

/**
 * The {@code CopyCommand} class implements the  Command interface
 * and is responsible for copying the currently selected text in the engine.
 */
public class CopyCommand implements Command {
  private Engine engine;
  private Selection selection;

  /**
   * 
   * @param engine    the engine to perform the copy operation
   * @param selection the selection object that provides the selected text range
   */
  public CopyCommand(Engine engine, Selection selection) {
    this.engine = engine;
    this.selection = selection;
  }

  /**
   * Executes the copy command by invoking the copy operation on the engine.
   */
  @Override
  public void execute() {
    engine.copySelectedText();

  }

}
