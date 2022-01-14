import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.Hashtable;

public class Visitor extends DepthFirstAdapter {

    private Hashtable symtable;

    Visitor(Hashtable symtable) {
        this.symtable = symtable;
    }

    /**
     * Checks if a value is string (Not finished)
     * @param node
     */
    @Override
    public void inAPlusExpression(APlusExpression node) {
        boolean isValueLeft  = node.getL() instanceof AValExpression;
        boolean isValueRight  = node.getR() instanceof AValExpression;
        if (isValueLeft){
            boolean isString = ((AValExpression) node.getL()).getValue() instanceof AStringValue;
            if (isString) {
                // TODO: Find line in productions
                System.out.println("Cannot add string value with number");
            }
        }
        if (isValueRight) {
            boolean isString = ((AValExpression) node.getR()).getValue() instanceof AStringValue;
            if (isString) {
                // TODO: Find line in productions
                System.out.println("Cannot add string value with number");
            }
        }

    }

}
