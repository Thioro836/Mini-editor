package fr.istic.aco.editor.ClassImpl;

import fr.istic.aco.editor.Interface.CommandOriginator;
import fr.istic.aco.editor.Interface.Memento;

public class Pair<CommandOriginator,Memento> {
       

        private CommandOriginator command;
        private Memento memento;

        Pair(CommandOriginator c, Memento m) {
            this.command = c;
            this.memento = m;
        }

        CommandOriginator getCommandOriginator() {
            return command;
        }

        Memento getMemento() {
            return memento;
        }

    }

