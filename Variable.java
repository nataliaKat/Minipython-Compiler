import java.util.Arrays;

public class Variable {

    private String name;
    private String type;
    private int initLine;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;

        Variable variable = (Variable) o;

        if (initLine != variable.initLine) return false;
        if (name != null ? !name.equals(variable.name) : variable.name != null) return false;
        return type != null ? type.equals(variable.type) : variable.type == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + initLine;
        return result;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", initLine=" + initLine +
                '}';
    }
}
