package model.node.turtleCommand;

import java.util.List;
import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.Parameters;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;
import model.turtle.Turtle;
import model.turtle.TurtleList;

public class Ask extends Tell {

    private TurtleList myTurtle;
    
    public Ask (Turtle turtle) {
        super(turtle);
        myTurtle = (TurtleList) turtle;
        // TODO Auto-generated constructor stub
    }

    public double evaluate () {
        List<Integer> queried = getIds();
        return myTurtle.ask(queried, (CommandList) getEvalChild("commands"));
    }


    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("listStart", ListStart.class),
                                   new ChildBuilder("ids", Parameters.class),
                                   new ChildBuilder("listEnd", ListEnd.class),
                                   new ChildBuilder("listStart", ListStart.class),
                                   new ChildBuilder("commands", CommandList.class),
                                   new ChildBuilder("listEnd", ListEnd.class)};
    }
}
