import java.util.Hashtable;
import java.util.List;

public class Utils {
    public static Function getFunction(Hashtable<String, List<Function>>  functions, String name, int numOfArgs) {
        if (functions.containsKey(name)) {
            List<Function> funcs = functions.get(name);
            for (Function f : funcs) {
                if (f.getNumOfAllParameters() == numOfArgs) return f;
                if (f.getNumOfAllParameters() - f.getNumOfDefaultParameters() == numOfArgs) return f;
            }
        }
        return null;
    }
}
