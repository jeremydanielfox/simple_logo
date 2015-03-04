package model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import model.turtle.SingleTurtle;
import org.junit.Test;


public class ParserTest {

    private List<Entry<String, Pattern>> myPatterns;
    private Pattern pattern;
    private SingleTurtle myTurtle;

    // @Test
    // public void testMultiply () {
    // Parser tester = new Parser(myPatterns, new Turtle());
    // assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
    // }

    @SuppressWarnings("deprecation")
    @Test
    public void testMover () {
        myTurtle = new SingleTurtle();
        myTurtle.rotate(45);
        myTurtle.translate(10);
//        UnboundedMover mover = new UnboundedMover();
//        mover.moveTurtle(myTurtle, new PolarVector(10, 180));
        //System.out.println(myTurtle.getPosition());
        assertEquals("locations", 5.0, myTurtle.getPosition().getX());

    }
}
