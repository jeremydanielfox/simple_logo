package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.ZeroArgOperation;

public class Home extends ZeroArgOperation {

    private SingleTurtle myTurtle;
    
    public Home (SingleTurtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.goHome();
    }
}