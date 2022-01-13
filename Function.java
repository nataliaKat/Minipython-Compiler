import minipython.node.PExpression;
import minipython.node.PStatement;

import java.util.Arrays;

public class Function {

    private String name;
    private String returnType; /* string, none, number,
                                undefined αν δεν έχει συγκεκριμένο τύπο επιστροφής πχ. x+y (εξαρτάται μόνο από μεταβλητές-ορίσματα)
                                void αν δεν έχει return */
    private PStatement statement;
    private int numOfDefaultParameters;
    private int numOfAllParameters;
    private Variable[] parameters;


    public Function(String name, int numOfDefaultParameters, int numOfAllParameters, Variable[] parameters) {
        this.name = name;
        this.numOfDefaultParameters = numOfDefaultParameters;
        this.numOfAllParameters = numOfAllParameters;
        this.parameters = parameters;
    }

    public Function(String name, int numOfDefaultParameters, int numOfAllParameters, PStatement statement, Variable[] parameters) {
        this.name = name;
        this.statement = statement;
        this.numOfDefaultParameters = numOfDefaultParameters;
        this.numOfAllParameters = numOfAllParameters;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public PStatement getStatement() {
        return statement;
    }

    public void setStatement(PStatement statement) {
        this.statement = statement;
    }

    public int getNumOfDefaultParameters() {
        return numOfDefaultParameters;
    }

    public void setNumOfDefaultParameters(int numOfDefaultParameters) {
        this.numOfDefaultParameters = numOfDefaultParameters;
    }

    public int getNumOfAllParameters() {
        return numOfAllParameters;
    }

    public void setNumOfAllParameters(int numOfAllParameters) {
        this.numOfAllParameters = numOfAllParameters;
    }

    public Variable[] getParameters() {
        return parameters;
    }

    public void setParameters(Variable[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", returnType='" + returnType + '\'' +
                ", statement=" + statement +
                ", numOfDefaultParameters=" + numOfDefaultParameters +
                ", numOfAllParameters=" + numOfAllParameters +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
