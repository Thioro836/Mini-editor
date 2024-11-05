package fr.istic.aco.editor.ConcreteCommand;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.Invoker;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;

public class InsertCommand implements CommandOriginator{
    private Engine engine;
    private Invoker inv;
    private String  textToInsert;
    //Rajouter un objet recorder et appeler save dans execute
    public InsertCommand(Engine engine, Invoker inv){
        this.engine=engine;
        this.inv = inv;
        
    }

        @Override
        public void execute() {
        engine.insert(inv.getTextToInsert());
        }

        @Override
        public void setMemento(Memento memento) {
           // this.textToInsert=memento;
        }

        @Override
        public Memento getMemento() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getMemento'");
        }
        
        
    }
