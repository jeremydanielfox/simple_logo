package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.SingleTurtle;

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