package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.Turtle;

public class Home extends ZeroArgOperation {

    private Turtle myTurtle;
    
    public Home (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.goHome();
    }
}