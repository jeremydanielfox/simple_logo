package model.node.turtleCommand;

import model.Turtle;
import model.node.ChildBuilder;

public class ClearScreen extends TurtleCommand {

    public ClearScreen (Turtle t) {
        super(t);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public double evaluate () {
        return getTurtle().clearScreen();
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }

    @Override
    public String toString () {
        return null;
    }
    
}
