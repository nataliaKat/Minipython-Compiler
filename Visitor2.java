import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.Hashtable;
import java.util.List;

public class Visitor2 extends DepthFirstAdapter {

    private Hashtable<String, Variable> variables;
    private Hashtable<String, List<Function>> functions;

    Visitor2(Hashtable<String, Variable> variables, Hashtable<String, List<Function>> functions) {
        this.variables = variables;
        this.functions = functions;
    }

    //Q1
    @Override
    public void inAIdentifierExpression(AIdentifierExpression node) {
        String variable_name = node.getId().getText();
        int line = node.getId().getLine();
        int pos = node.getId().getPos();
        if (!variables.containsKey(variable_name)) {
            System.out.println("Error variable " + variable_name + ", line: " + line + " pos: "+ pos+ " does not exist");
        }
    }

    @Override
    public void inAAssignEqStatement(AAssignEqStatement node) {
        String name = node.getId().getText();
        variables.put(name, new Variable(name,""));
    }

    @Override
    public void inAFuncCall(AFuncCall node) {
        String function_name
                = node.getId().getText();
        int numOfArgs = ((AFuncCall) node).getExpression().size();
        int line = node.getId().getLine();
        int pos = node.getId().getPos();
        //Q2
        if (!functions.containsKey(function_name)) {
            System.out.println("Error function " + function_name + ", line: " + line + " pos: " + pos + " does not exist");
        } else {
            //Q3
            Function f = Utils.getFunction(functions, function_name, numOfArgs);
            if (f == null) {
                System.out.println("Error function " + function_name + " with " + numOfArgs + " parameters in line: " + line + " pos: " + pos + " does not exist");
            }
        }
    }
}
