package model.node.turtleCommand;

import model.Turtle;
import model.node.ChildBuilder;

public class Home extends TurtleCommand {

    public Home (Turtle t) {
        super(t);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public double evaluate () {
        return getTurtle().goHome();
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
