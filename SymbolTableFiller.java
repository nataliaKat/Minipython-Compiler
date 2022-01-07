import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.Hashtable;

public class SymbolTableFiller extends DepthFirstAdapter {

    private Hashtable symtable;

    SymbolTableFiller(Hashtable symtable) {
        this.symtable = symtable;
    }

    @Override
    public void inAAssignEqStatement(AAssignEqStatement node) {
        String varName = node.getId().toString().trim();
        String type = "";
        if (node.getExpression() instanceof AMinusExpression ||
            node.getExpression() instanceof AMultiplicationExpression ||
            node.getExpression() instanceof ADivisionExpression ||
            node.getExpression() instanceof AModExpression ||
            node.getExpression() instanceof APowerExpression ||
            node.getExpression() instanceof ALenExpression
        ) type = "number";
        if (!symtable.contains(varName))
            symtable.put(varName, new Variable(varName, type));
    }
//
//    @Override
//    public void inAStringValue(AStringValue node) {
//        System.out.println("hello from string" + node.getStringLiteral());
//        Node parent = node;
//        do {
//            parent = parent.parent();
//        } while (!parent.getClass().equals(AAssignEqStatement.class));
//        System.out.print("Identifier is: ");
//        String name = ((AAssignEqStatement) parent).getId().toString().trim();
//        System.out.println(name);
//        if (!symtable.containsKey(name))
//            symtable.put(name, "string");
//    }
//
//    @Override
//    public void inANumberValue(ANumberValue node) {
//        System.out.println("hello from num" + node.getNumber());
//        Node parent = node;
//        do {
//            parent = parent.parent();
//        } while (parent != null  && !parent.getClass().equals(AAssignEqStatement.class));
//        if (parent != null) {
//            System.out.print("Identifier is: ");
//            String name = ((AAssignEqStatement) parent).getId().toString().trim();
//            System.out.println(name);
//            if (!symtable.containsKey(name))
//                symtable.put(name, "number");
//        }
//    }



//    @Override
//    public void inAPlusExpression(APlusExpression node) {
//        if ()
//        System.out.println(checkArithmetic((AValExpression)node.getL(), (AValExpression) node.getR()));
//    }

    private boolean checkArithmetic(AValExpression left, AValExpression right) {
        if (left.getValue() instanceof AStringValue || right.getValue() instanceof AStringValue)
            return true;
        return false;
    }

    @Override
    public void inAPowerExpression(APowerExpression node) {
        boolean isArithmetic = checkArithmetic((AValExpression)node.getL(), (AValExpression) node.getR());
        System.out.println(isArithmetic + " from power");
    }

    //TODO: Make method with duplicate code and make special checks for plus
    @Override
    public void inAModExpression(AModExpression node) {
        if (node.getL() instanceof AValExpression) {
            if (((AValExpression) node.getL()).getValue() instanceof AStringValue) {
                System.out.println("problem in multiplication left");
            }
        }
        if (node.getR() instanceof AValExpression) {
            if (((AValExpression) node.getR()).getValue() instanceof AStringValue) {
                System.out.println("problem in multiplication right");
            }
        }
    }

    @Override
    public void inAMultiplicationExpression(AMultiplicationExpression node) {
        if (node.getL() instanceof AValExpression) {
            if (((AValExpression) node.getL()).getValue() instanceof AStringValue) {
                System.out.println("problem in multiplication left");
            }
        }
        if (node.getR() instanceof AValExpression) {
            if (((AValExpression) node.getR()).getValue() instanceof AStringValue) {
                System.out.println("problem in multiplication right");
            }
        }
    }
}
