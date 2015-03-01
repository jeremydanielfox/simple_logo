package model.node.turtleCommand;

import model.Turtle;
import model.node.ChildBuilder;

public class PenUp extends TurtleCommand {

    public PenUp (Turtle t) {
        super(t);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double evaluate () {
        return getTurtle().setPenUp();
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

}
