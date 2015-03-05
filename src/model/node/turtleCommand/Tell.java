package model.node.turtleCommand;

import java.util.List;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.node.Ids;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;
import model.turtle.Turtle;
import model.turtle.TurtleList;


public class Tell extends EvalNode {

    private TurtleList myTurtle;

    public Tell (Turtle turtle) {
        myTurtle = (TurtleList) turtle;
    }

    @Override
    public double evaluate () {
        List<Integer> actives = ((Ids) getEvalChild("ids")).getList();
        myTurtle.setActive(actives);
        return actives.get(actives.size()-1);
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("ids", Ids.class),
                                    new ChildBuilder("listEnd", ListEnd.class)};
    }
}
