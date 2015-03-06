package model.node.turtleCommand;

import java.util.List;
import java.util.stream.Collectors;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.node.Parameters;
import model.node.basic.Constant;
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
        List<Integer> actives = getIds();
        myTurtle.setActive(getIds());
        return actives.get(actives.size() - 1);
    }

    // messy, but necessary to reduce duplication
    protected List<Integer> getIds () {
        return (List<Integer>) ((Parameters) getChild("ids")).getList().stream()
                .map(this::convertToInteger).collect(Collectors.toList());
    }

    protected int convertToInteger (Object id) {
        Double d = ((EvalNode) id).evaluate();
        return d.intValue();
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("listStart", ListStart.class),
                                   new ChildBuilder("ids", Parameters.class),
                                   new ChildBuilder("listEnd", ListEnd.class) };
    }
}
