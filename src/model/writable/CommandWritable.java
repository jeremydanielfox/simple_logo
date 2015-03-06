package model.writable;

import java.util.List;
import java.util.stream.Collectors;
import model.node.CommandList;
import model.node.Parameters;
import model.node.database.Variable;

public class CommandWritable extends Writable {
    private String name;
    private Parameters params;
    private CommandList commands;

    public CommandWritable (String name, Parameters params, CommandList cmds) {
        this.name = name;
        this.params = params;
        this.commands = cmds;
        System.out.println(getValue());
    }

    public String getName () {
        return name;
    }

    public List<Variable> getParameters () {
        return params.getList();
    }

    public CommandList getCommands () {
        return commands;
    }

    @Override
    public String getValue () {
       return String.format("[ %s] [ %s]", params.toString(), commands.toString());
    }
    
}
