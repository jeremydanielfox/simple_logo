package model.writable;

import java.util.List;
import model.node.CommandList;
import model.node.database.Variable;

public class CommandWritable extends Writable {
    private String name;
    private List<Variable> params;
    private CommandList commands;

    public CommandWritable (String name, List<Variable> params, CommandList cmds) {
        this.name = name;
        this.params = params;
        this.commands = cmds;
    }

    public String getName () {
        // perhaps would return name and params
        return name;
    }

    public List<Variable> getParameters () {
        return params;
    }

    public CommandList getCommands () {
        return commands;
    }

    @Override
    public String getValue () {
        // return command.toString
        return null;
    }
}
