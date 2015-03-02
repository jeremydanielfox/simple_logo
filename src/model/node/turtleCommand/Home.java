package model.node.turtleCommand;

import model.Turtle;
import model.node.ZeroArgOperation;

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