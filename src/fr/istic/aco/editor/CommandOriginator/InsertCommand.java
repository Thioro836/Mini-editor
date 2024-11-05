package fr.istic.aco.editor.CommandOriginator;

import fr.istic.aco.editor.ClassImpl.Invoker;
import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Engine;
import fr.istic.aco.editor.Interface.Memento;
import fr.istic.aco.editor.Memento.InsertMemento;

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
        public Memento getMemento() {
           return (new InsertMemento(textToInsert));
      
        }
        
        @Override
        public void setMemento(Memento memento) {
            InsertMemento insertMemento = (InsertMemento) memento;
          this.textToInsert=insertMemento.getText();
        }

        
        
    }
