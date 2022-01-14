import java.util.Arrays;

public class Variable {

    private String name;
    private String type;
    private int initLine;
    private String[] elementTypes;

    public Variable(String name) {
        this.name = name;
    }

    public Variable(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Variable(String name, String type, int initLine) {
        this.name = name;
        this.type = type;
        this.initLine = initLine;
    }

    public Variable(String name, String type, int initLine, String[] elementTypes) {
        this.name = name;
        this.type = type;
        this.initLine = initLine;
        this.elementTypes = elementTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInitLine() {
        return initLine;
    }

    public void setInitLine(int initLine) {
        this.initLine = initLine;
    }

    public String[] getElementTypes() {
        return elementTypes;
    }

    public void setElementTypes(String[] elementTypes) {
        this.elementTypes = elementTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;

        Variable variable = (Variable) o;

        if (initLine != variable.initLine) return false;
        if (!name.equals(variable.name)) return false;
        if (!type.equals(variable.type)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(elementTypes, variable.elementTypes);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + initLine;
        result = 31 * result + Arrays.hashCode(elementTypes);
        return result;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", initLine=" + initLine +
                ", elementTypes=" + Arrays.toString(elementTypes) +
                '}';
    }
}
