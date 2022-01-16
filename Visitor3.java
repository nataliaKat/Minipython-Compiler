import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.Hashtable;
import java.util.LinkedList;
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
     *
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


    @Override
    public void inAAssignEqStatement(AAssignEqStatement node) {
        String name = node.getId().getText();
        int line = node.getId().getLine() / 2 + 1;
        PExpression expression = node.getExpression();
        String type = getExpressionType(expression, line);
        variables.put(name, new Variable(name, type, line));
    }

    @Override
    public void inAAssignDiveqStatement(AAssignDiveqStatement node) {
        String name = node.getId().getText();
        int line = node.getId().getLine() / 2 + 1;
        PExpression expression = node.getExpression();
        storeForArithmeticStatements(expression, line, name);
    }

    @Override
    public void inAAssignMineqStatement(AAssignMineqStatement node) {
        String name = node.getId().getText();
        int line = node.getId().getLine() / 2 + 1;
        PExpression expression = node.getExpression();
        storeForArithmeticStatements(expression, line, name);
    }

    @Override
    public void inAPrintStatement(APrintStatement node) {
        int line = getLine(node.getL()) / 2 + 1;
        String typeLeft = getExpressionType(node.getL(), line);
        LinkedList<PExpression> expressions = node.getR();
        if (typeLeft != null) {
            for (PExpression expression : expressions) {
                getExpressionType(expression, line);
            }
        }
    }

    @Override
    public void inAAssertStatement(AAssertStatement node) {
        int line = getLine(node.getL()) / 2 + 1;
        String typeLeft = getExpressionType(node.getL(), line);
        LinkedList<PExpression> expressions = node.getR();
        if (typeLeft != null) {
            for (PExpression expression : expressions) {
                getExpressionType(expression, line);
            }
        }
    }

    private int getLine(PExpression expression) {
        if (expression instanceof AValExpression) {
            return getValueLine(((AValExpression) expression).getValue());
        } else if (expression instanceof AMinExpression) {
            return getValueLine(((AMinExpression) expression).getL());
        } else if (expression instanceof AMaxExpression) {
            return getValueLine(((AMaxExpression) expression).getL());
        } else if (expression instanceof ALenExpression) {
            return getLine(((ALenExpression) expression).getExpression());
        } else if (expression instanceof ASubscriptionExpression) {
            return getLine(((ASubscriptionExpression) expression).getExpression());
        } else if (expression instanceof AFunctionExpression) {
            PFuncCall pFuncCall = ((AFunctionExpression) expression).getFuncCall();
            return ((AFuncCall) pFuncCall).getId().getLine();
        } else if (expression instanceof AIdentifierExpression) {
            return ((AIdentifierExpression) expression).getId().getLine();
        } else if (expression instanceof AArrayExpression) {
            return getLine(((AArrayExpression) expression).getL());
        } else if (expression instanceof ArithmeticOperation) {
            return getLine(((ArithmeticOperation) expression).getL());
        } else if (expression instanceof APlusExpression) {
            return getLine(((APlusExpression) expression).getL());
        }
        return -1;
    }

    private int getValueLine(PValue pValue) {
        if (pValue instanceof AStringValue) {
            return ((AStringValue) pValue).getStringLiteral().getLine();
        } else if (pValue instanceof ANumberValue) {
            return ((ANumberValue) pValue).getNumber().getLine();
        } else if (pValue instanceof ANoneValue) {
            return ((ANoneValue) pValue).getNone().getLine();
        } else if (pValue instanceof AFCallValue) {
            return ((AFCallValue) pValue).getId().getLine();
        }
        return -1;
    }


    private void storeForArithmeticStatements(PExpression expression, int line, String name) {
        String type = getExpressionType(expression, line);
        if (!type.equals("number")) {
            System.out.println("Non numeric expression, line: " + line);
            return;
        }
        variables.put(name, new Variable(name, type, line));
    }

//    @Override
//    public void inAAssignEqStatement(AAssignEqStatement node) {
//        String name = node.getId().getText();
//        int line = node.getId().getLine();
//        if(node.getExpression() instanceof AValExpression){
//            PValue value = ((AValExpression) node.getExpression()).getValue();
//            if(value instanceof ANoneValue){
//                // none
//                variables.put(name,new Variable(name,"None", line));
//            }
//            else if(value instanceof ANumberValue){
//                //number
//                variables.put(name,new Variable(name,"Number", line));
//            }
//            else if(value instanceof AStringValue){
//                //string
//                variables.put(name,new Variable(name,"String", line));
//            }
//        }
//        else if(node.getExpression() instanceof AArrayExpression){
//            variables.put(name,new Variable(name,"Array", line));
//        }
//        else if(node.getExpression() instanceof AIdentifierExpression){
//            variables.put(name,new Variable(name,"Identifier", line));
//        }
//        else if(node.getExpression() instanceof AFunctionExpression){
//            variables.put(name,new Variable(name,"Function Call", line));
//        }
//        else if(node.getExpression() instanceof APlusExpression|
//                node.getExpression() instanceof AMultiplicationExpression|
//                node.getExpression() instanceof ADivisionExpression|
//                node.getExpression() instanceof AModExpression|
//                node.getExpression() instanceof APowerExpression|
//                node.getExpression() instanceof AMinusExpression|
//                //TODO minmaxlen ola sxedon
//                node.getExpression() instanceof AMinExpression|
//                node.getExpression() instanceof AMaxExpression|
//                node.getExpression() instanceof ALenExpression){
//            variables.put(name,new Variable(name,"Number", line));
//        }
//        else if(node.getExpression() instanceof ASubscriptionExpression){
//            variables.put(name,new Variable(name,"Subscription", line));
//        }
//        else{
//            System.out.println("Error " + name);
//        }
//    }

/*
inAFuncCall
περνουμε απο ταβλε το φθντιον μετα βαζουμε τιμες στα αρισματα στο ταβλε με τις μεταβλητες
ψαχνουμε για ρετθρν και αμα δεν εχει επιστρεφει Νονε αλλιως επιστρεφει τυπο
*/


    private String getExpressionType(PExpression expression, int line) {
        String type = null;
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
            type = getMaxType((AMaxExpression) expression);
            if (type == null) {
                System.out.println("Error in max, line: " + line);
                return null;
            }
        } else if (expression instanceof AMinExpression) {
            type = getMinType((AMinExpression) expression);
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
        } else if (expression instanceof APowerExpression) {
            type = getArithmeticType((APowerExpression) expression, line);
        } else if (expression instanceof AModExpression) {
            type = getArithmeticType((AModExpression) expression, line);
        } else if (expression instanceof AMultiplicationExpression) {
            type = getArithmeticType((AMultiplicationExpression) expression, line);
        } else if (expression instanceof AMinusExpression) {
            type = getArithmeticType((AMinusExpression) expression, line);
        } else if (expression instanceof APlusExpression) {
            PExpression left = ((APlusExpression) expression).getL();
            PExpression right = ((APlusExpression) expression).getR();
            String typeLeft = getExpressionType(left, line);
            String typeRight = getExpressionType(right, line);
            if (typeLeft == null || typeRight == null) {
                type = null;
            } else if (typeLeft.equals("number") && typeRight.equals("number")) {
                type = "number";
            } else if (typeLeft.equals("string") && typeRight.equals("string")) {
                type = "string";

            } else {
                System.out.println("Unsupported operand types for +, line: " + line);
                type = null;
            }
        } else if (expression instanceof AFunctionExpression) {
            String function_name = ((AFuncCall) ((AFunctionExpression) expression).getFuncCall()).getId().getText();
            int numOfArgs = ((AFuncCall) ((AFunctionExpression) expression).getFuncCall()).getExpression().size();
            Function f = Utils.getFunction(functions, function_name, numOfArgs);
            if (f != null) {
                Variable[] arguments = f.getParameters();
                AArgument argument;
                List<PExpression> argExpressions = ((AFuncCall) ((AFunctionExpression) expression).getFuncCall()).getExpression();
                for (int i = 0; i < f.getNumOfAllParameters(); i++) {
                    String argName = arguments[i].getName();
                    if (i < numOfArgs) {
                        String argTypeFromFCall = getExpressionType(argExpressions.get(i), line);
                        variables.put(argName, new Variable(argName, argTypeFromFCall, line));
                    } else {
                        variables.put(argName, new Variable(argName, arguments[i].getType(), line));
                    }
                }
            }
            PExpression returnExpression = getReturnExpression(f.getStatement());
            if (returnExpression != null)
                type = getExpressionType(returnExpression, line);
        }

//                    if (f == null) {
//        System.out.println("Function not found, line: " + line);
//    }
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

    private String getArithmeticType(ArithmeticOperation node, int line) {
        PExpression left = node.getL();
        PExpression right = node.getR();
        String typeLeft = getExpressionType(left, line);
        String typeRight = getExpressionType(right, line);
        if (typeLeft == null || typeRight == null) {
            return null;
        } else if (!(typeLeft.equals("number") && typeRight.equals("number"))) {
            System.out.println("Unsupported operand types, line: " + line);
            return null;
        } else return "number";
    }

    private PExpression getReturnExpression(PStatement pStatement) {
        if (pStatement instanceof AReturnStatement) {
            return ((AReturnStatement) pStatement).getExpression();
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

    @Override
    public void inAPlusExpression(APlusExpression node) {

    }
}