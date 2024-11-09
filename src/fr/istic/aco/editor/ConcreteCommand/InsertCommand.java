package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.ClasseImpl.Invoker;
import fr.istic.aco.editor.Interface.Command;
import fr.istic.aco.editor.Interface.Engine;

public class InsertCommand implements Command{
    private Engine engine;
    private Invoker inv;
    private String  textToInsert;
    public InsertCommand(Engine engine, Invoker inv){
        this.engine=engine;
        this.inv = inv;
        
    }

        @Override
        public void execute() {
        engine.insert(inv.getTextToInsert());
        }
        
    }
