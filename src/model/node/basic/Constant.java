package model.node.basic;

import java.text.DecimalFormat;
import model.node.ZeroArgOperation;

/**
 * TreeNode representing a constant
 * @author Nathan Prabhu
 *
 */
public class Constant extends ZeroArgOperation {

    private double value;
    
    public Constant (String value) {
        this.value = Double.parseDouble(value);
    }

    public double evaluate () {
        return value;
    }

    @Override
    public String toString () {
        String result = String.valueOf(value);
        if (Math.abs(Math.round(value)-value)<.0001){
            DecimalFormat df = new DecimalFormat("###.#");
            result = df.format(value);
        }
        return String.format("%s ", result);
    }
}
