import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.Hashtable;
import java.util.List;

public class Visitor3 extends DepthFirstAdapter {
    private Hashtable<String, Variable> variables;
    private Hashtable<String, List<Function>> functions;

    Visitor3(Hashtable<String, Variable> variables, Hashtable<String, List<Function>> functions) {
        this.variables = variables;
        this.functions = functions;
    }


    /**
     * Checks if a value is string (Not finished)
     * @param node
     */
//    @Override
//    public void inAPlusExpression(APlusExpression node) {
//        boolean isValueLeft  = node.getL() instanceof AValExpression;
//        boolean isValueRight  = node.getR() instanceof AValExpression;
//        if (isValueLeft){
//            boolean isString = ((AValExpression) node.getL()).getValue() instanceof AStringValue;
//            if (isString) {
//                // TODO: Find line in productions
//                System.out.println("Cannot add string value with number");
//            }
//        }
//        if (isValueRight) {
//            boolean isString = ((AValExpression) node.getR()).getValue() instanceof AStringValue;
//            if (isString) {
//                // TODO: Find line in productions
//                System.out.println("Cannot add string value with number");
//            }
//        }
//
//
//    }

    @Override
    public void inAMaxExpression(AMaxExpression node) {

    }

    //    @Override
//    public void inAPowerExpression(APowerExpression node) {
//        PExpression left = node.getL();
//        PExpression right = node.getR();
//        if (left instanceof AValExpression)
//        boolean isArithmetic = checkArithmetic((AValExpression)node.getL(), (AValExpression) node.getR());
//        System.out.println(isArithmetic + " from power");
//        if (node.getL() instanceof AIdentifierExpression) {
//            System.out.println(node.getL());
//        }
//    }
//    @Override
//    public void inAAssignEqStatement(AAssignEqStatement node) {
//        String name = node.getId().toString().trim();
//        int line = node.getId().getLine() / 2 + 1;
//        PExpression expression = node.getExpression();
//        String type = getExpressionType(expression, line);
//        Variable newVar = new Variable(name, type, line);
//        if (type != null) variables.put(name, newVar);
//    }

    private String getExpressionType(PExpression expression, int line) {
        String type = "";
        if (expression instanceof AValExpression) {
            type = getValueType(((AValExpression) expression).getValue());
        } else if (expression instanceof ALenExpression) {
            // If expression is None,
            if (getExpressionType(((ALenExpression) expression).getExpression(), line) == null) {
                return null;
            } else if (getExpressionType(((ALenExpression) expression).getExpression(), line).equals("none")) {

                System.out.println("Cannot find len() of None, line: " + line);
                return null;
            }
            type = "number";
        } else if (expression instanceof AMaxExpression) {
            type = getMaxType((AMaxExpression)expression);
            if (type == null) {
                System.out.println("Error in max, line: " + line);
                return null;
            }
        } else if (expression instanceof AMinExpression) {
            type = getMinType((AMinExpression)expression);
            if (type == null) {
                System.out.println("Error in min, line: " + line);
                return null;
            }
        } else if (expression instanceof AIdentifierExpression) {
            String variableName = ((AIdentifierExpression) expression).getId().toString().trim();
            if (!variables.containsKey(variableName)) {
                System.out.println("Error variable" + variableName + ", line: " + line + " does not exist");
                return null;
            } else {
                return variables.get(variableName).getType();
            }
        } else if (expression instanceof AFunctionExpression) {
            PFuncCall pFuncCall = ((AFunctionExpression) expression).getFuncCall();
            String name = ((AFuncCall) pFuncCall).getId().toString().trim();
            int numOfArgs = ((AFuncCall) pFuncCall).getExpression().size();
            Function f = Utils.getFunction(functions, name, numOfArgs);
            if (f == null) {
                System.out.println("Function not found, line: " + line);
            }
        } else if (expression instanceof APowerExpression) {
            PExpression left = ((APowerExpression) expression).getL();
            PExpression right = ((APowerExpression) expression).getR();
            String typeLeft =  getExpressionType(left, line);
            String typeRight =  getExpressionType(right, line);
            if (!(typeLeft.equals("number") || typeRight.equals("number"))) {
                System.out.println("Unsupported operand types, line: " + line);
                return null;
            }
            return "number";
        } else if (expression instanceof AModExpression) {

        }
//        elssion instanceof AArrayExpression) {
//     etExpressionType(((AArrayExpression) expression).getExpression(), line) == null) {
//                return null;
//            } else if (getExpressionType(((AArrayExpression expression).getExpression(), line).equals("none")) {
//                System.out.println("Cannot find` NonArray list of Noneline");
//                return null;
//            }
//        }
        return type;
    }

    private PExpression getReturnExpression(PStatement pStatement) {
        if (pStatement instanceof AReturnStatement) {
            return  ((AReturnStatement) pStatement).getExpression();
        } else if (pStatement instanceof AConditionStatement) {
            return getReturnExpression(((AConditionStatement) pStatement).getStatement());
        } else if (pStatement instanceof ALoopWhileStatement) {
            return getReturnExpression(((ALoopWhileStatement) pStatement).getStatement());
        } else if (pStatement instanceof ALoopForStatement) {
            return getReturnExpression(((ALoopForStatement) pStatement).getStatement());
        }
        return null;
    }


    private String getMaxType(AMaxExpression aMaxExpression) {
        String type = getValueType(aMaxExpression.getL());
        if (type.equals("none")) return null;
        List<PValue> values = aMaxExpression.getR();
        for (PValue v : values) {
            if (!getValueType(v).equals(type)) return null;
        }
        return type;
    }

    private String getMinType(AMinExpression aMinExpression) {
        String type = getValueType(aMinExpression.getL());
        if (type.equals("none")) return null;
        List<PValue> values = aMinExpression.getR();
        for (PValue v : values) {
            if (!getValueType(v).equals(type)) return null;
        }
        return type;
    }

    private String getValueType(PValue pValue) {
        if (pValue instanceof AStringValue) return "string";
        else if (pValue instanceof ANumberValue) return "number";
        else return "none";
    }
}