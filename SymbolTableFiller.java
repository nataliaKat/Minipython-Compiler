import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.Hashtable;

public class SymbolTableFiller extends DepthFirstAdapter {

    private Hashtable<String, Variable> variables;

    SymbolTableFiller(Hashtable<String, Variable> variables) {
        this.variables = variables;
    }

    @Override
    public void inAAssignEqStatement(AAssignEqStatement node) {
        String varName = node.getId().toString().trim();
        String type = "";
        PExpression expression = node.getExpression();
        if (expression instanceof AMinusExpression ||
            expression instanceof AMultiplicationExpression ||
            expression instanceof ALenExpression ||
            expression instanceof ADivisionExpression ||
            expression instanceof AModExpression ||
            expression instanceof APowerExpression
        ) type = "number";
        else if (expression instanceof APlusExpression) {
            if (((APlusExpression) expression).getL() instanceof AValExpression) {
                PExpression left = ((APlusExpression) expression).getL();
                if (((AValExpression)left).getValue() instanceof AStringValue) {
                    type = "string";
                } else if (((AValExpression)left).getValue() instanceof ANumberValue) {
                    type = "number";
                }
            }
        } else if (expression instanceof AValExpression) {
            if (((AValExpression) expression).getValue() instanceof AStringValue)
                type = "string";
            else if (((AValExpression)expression).getValue() instanceof ANumberValue)
                type = "number";
        } else if (expression instanceof AIdentifierExpression) {
            String id = ((AIdentifierExpression) expression).getId().toString().trim();
            System.out.println("found id" + ((AIdentifierExpression) expression).getId());
            if (variables.containsKey(id)) {
                type = ((Variable)variables.get(id)).getType();
            }
        }
        if (!variables.contains(varName))
            variables.put(varName, new Variable(varName, type));
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
        PExpression left = node.getL();
        PExpression right = node.getR();
//        if (left instanceof AValExpression)
//        boolean isArithmetic = checkArithmetic((AValExpression)node.getL(), (AValExpression) node.getR());
//        System.out.println(isArithmetic + " from power");
//        if (node.getL() instanceof AIdentifierExpression) {
//            System.out.println(node.getL());
//        }
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
    public void inAIdentifierExpression(AIdentifierExpression node) {

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
