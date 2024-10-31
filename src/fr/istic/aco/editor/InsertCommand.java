package fr.istic.aco.editor;

    public class InsertCommand implements CommandOriginator{
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

        @Override
        public Memento getMemento() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getMemento'");
        }

        @Override
        public void setMemento(Memento memento) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setMemento'");
        }
        
        
    }
