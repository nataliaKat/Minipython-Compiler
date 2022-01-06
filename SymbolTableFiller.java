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
        String varName = node.getId().toString();
        String type;
        PExpression expression = node.getExpression();
        if (expression instanceof APlusExpression) {
//            System.out.println("Hi");
        }
    }

    @Override
    public void inAStringValue(AStringValue node) {
        System.out.println("hello from string" + node.getStringLiteral());
        System.out.println("test12");
        Node parent = node;
        do {
            System.out.println("test1");
            parent = parent.parent();
            System.out.println("test");
        } while (!parent.getClass().equals(AAssignEqStatement.class));
        System.out.print("Identifier is: ");
        String name = ((AAssignEqStatement) parent).getId().toString().trim();
        System.out.println(name);
        if (!symtable.containsKey(name))
            symtable.put(name, "string");
    }

    @Override
    public void inANumberValue(ANumberValue node) {
        System.out.println("hello from num" + node.getNumber());
        Node parent = node;
        do {
            System.out.println("test1");
            parent = parent.parent();
            System.out.println("test");
        } while (parent != null  && !parent.getClass().equals(AAssignEqStatement.class));
        if (parent != null) {
            System.out.print("Identifier is: ");
            String name = ((AAssignEqStatement) parent).getId().toString().trim();
            System.out.println(name);
            if (!symtable.containsKey(name))
                symtable.put(name, "number");
        }
    }


    /**
     * Checks if a value is string (Not finished)
     * @param node
     */
    @Override
    public void inAPlusExpression(APlusExpression node) {

    }
}
