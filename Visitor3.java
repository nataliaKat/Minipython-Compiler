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

    @Override
    public void inAAssignEqStatement(AAssignEqStatement node) {
        String name = node.getId().getText();
        int line = node.getId().getLine();
        PExpression expression = node.getExpression();
        String type = getExpressionType(expression, line);
        variables.put(name, new Variable(name, type, line));
    }

    @Override
    public void inAAssignEqOperationStatement(AAssignEqOperationStatement node) {
        String name = node.getId().getText();
        int line = node.getId().getLine();
        PExpression expression = node.getExpression();
        storeForArithmeticStatements(expression, line, name);
    }

    @Override
    public void inAPrintStatement(APrintStatement node) {
        int line = getLine(node.getL());
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
        int line = getLine(node.getL());
        String typeLeft = getExpressionType(node.getL(), line);
        LinkedList<PExpression> expressions = node.getR();
        if (typeLeft != null) {
            for (PExpression expression : expressions) {
                getExpressionType(expression, line);
            }
        }
    }

    @Override
    public void inAFuncCallStatement(AFuncCallStatement node) {
        AFuncCall functionCall = (AFuncCall) node.getFuncCall();
        String type = getFunctionCallType(functionCall);
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
        } else if (expression instanceof AArithmeticOperationExpression) {
            return getLine(((AArithmeticOperationExpression) expression).getL());
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
                return null;
            } else {
                return variables.get(variableName).getType();
            }
        } else if (expression instanceof AArithmeticOperationExpression) {
            type = getArithmeticType((AArithmeticOperationExpression) expression, line);
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
                System.out.println("Error: Unsupported operand types for +, line: " + line);
                type = null;
            }
        } else if (expression instanceof AFunctionExpression) {
            type = getFunctionCallType((AFuncCall) ((AFunctionExpression) expression).getFuncCall());
        }
        return type;
}

    private String getFunctionCallType(AFuncCall functionCall) {
        String function_name = functionCall.getId().getText();
        int line = functionCall.getId().getLine();
        int numOfArgs = functionCall.getExpression().size();
        Function f = Utils.getFunction(functions, function_name, numOfArgs);
        if (f != null) {
            Variable[] arguments = f.getParameters();
            List<PExpression> argExpressions = functionCall.getExpression();
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
        return getExpressionType(returnExpression, line);
    }

    private String getArithmeticType(AArithmeticOperationExpression node, int line) {
        PExpression left = node.getL();
        PExpression right = node.getR();
        String typeLeft = getExpressionType(left, line);
        String typeRight = getExpressionType(right, line);
        if (typeLeft == null || typeRight == null) {
            return null;
        } else if (!(typeLeft.equals("number") && typeRight.equals("number"))) {
            System.out.println("Error: Unsupported operand types, line: " + line);
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
}