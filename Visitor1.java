import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Visitor1 extends DepthFirstAdapter {

    private Hashtable symtable;

    Visitor1(Hashtable symtable) {
        this.symtable = symtable;
    }

    private String getValueType(PValue value) {
        if (value instanceof AStringValue) return "string";
        else if (value instanceof ANumberValue) return "number";
        else if (value instanceof ANoneValue) return "none";
        return null;
    }

    private void putToFunctions(Function f) {
        boolean check = true;
        if (!symtable.containsKey(f.getName())) {
            List functions = new LinkedList();
            functions.add(f);
            symtable.put(f.getName(), functions);
        } else {
            List<Function> functions = (List) symtable.get(f.getName());
            int allArgs = f.getNumOfAllParameters();
            int nonDefaults = allArgs - f.getNumOfDefaultParameters();
            for (Function function : functions) {
                if (function.getNumOfAllParameters() == allArgs ||
                        (function.getNumOfAllParameters() - function.getNumOfDefaultParameters()) == nonDefaults) {
                    check = false;
                    System.out.println("Error on line: "+ f.getLine()+", Function " + f.getName() + " with "+ f.getNumOfAllParameters() + " arguments is already defined on line: " + function.getLine());
                }
            }
            if (check) {
                functions.add(f);
                symtable.put(f.getName(), functions);
            }
        }
    }

    // TODO: Check if parameter already exists in argumentsArray
    @Override
    public void inAFunction(AFunction node) {
        String name = node.getId().toString().trim();
        int line = node.getId().getLine();
        LinkedList<AArgument> arguments = node.getArgument(); //size = 0 or 1
        AArgument argument;
        Variable[] argumentsArray = null;
        int defaultArgsNum = 0;
        if (arguments.size() > 0) {
            argument = arguments.getFirst();
            LinkedList<ANotFirstArgument> nfargs = argument.getNotFirstArgument();
            argumentsArray = new Variable[nfargs.size() + 1];
            String varName = argument.getId().toString().trim();
            String varType = null;
            if (argument.getValue().size() > 0) {
                varType = getValueType((PValue) (argument.getValue().getFirst()));
                defaultArgsNum++;
            }
            argumentsArray[0] = new Variable(varName, varType);
            int i = 1;
            for (ANotFirstArgument nfarg : nfargs) {
                varName = nfarg.getId().toString().trim();
                varType = null;
                if (nfarg.getValue().size() > 0) {
                    varType = getValueType((PValue) (nfarg.getValue().getFirst()));
                    defaultArgsNum++;
                }
                argumentsArray[i] = new Variable(varName, varType);
                i++;
            }
        }
        int numOfAllParams = 0;
        if (argumentsArray != null) numOfAllParams = argumentsArray.length;
        Function f = new Function(name, line, defaultArgsNum, numOfAllParams, node.getStatement(), argumentsArray);
        putToFunctions(f);
    }



}
