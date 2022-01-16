import java.io.*;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import minipython.lexer.Lexer;
import minipython.parser.Parser;
import minipython.node.Start;


public class ParserTest2
{
  public static void main(String[] args)
  {
    try
    {
      Parser parser = 
        new Parser(
        new Lexer(
        new PushbackReader(
        new FileReader(args[0].toString()), 1024)));

      Start ast = parser.parse();

	  ast.apply(new ASTPrinter());
      Hashtable<String, Variable> variables = new Hashtable();
      Hashtable<String, List<Function>> functions =  new Hashtable(); /* List of functions for overloading */
//      ast.apply(new SymbolTableFiller(symtable));
      System.out.println("Entering First Visitor:");
      ast.apply(new Visitor1(functions));
      System.out.println("Functions");
      System.out.println(functions);
      System.out.println("Variables");
      for (Map.Entry<String, Variable> entry: variables.entrySet()) {
        String key = entry.getKey();
        Variable value = entry.getValue();

        System.out.println("Key: " + key + " Value: " + value);
      }
      System.out.println("Entering Second Visitor:");
      ast.apply(new Visitor2(variables, functions));
      System.out.println("Functions");
      System.out.println(functions);
      System.out.println("Variables");
      for (Map.Entry<String, Variable> entry: variables.entrySet()) {
        String key = entry.getKey();
        Variable value = entry.getValue();

        System.out.println("Key: " + key + " Value: " + value);
      }
      System.out.println("Entering Third Visitor:");
      ast.apply(new Visitor3(variables, functions));
      System.out.println("Functions");
      System.out.println(functions);
      System.out.println("Variables");
      for (Map.Entry<String, Variable> entry: variables.entrySet()) {
        String key = entry.getKey();
        Variable value = entry.getValue();

        System.out.println("Key: " + key + " Value: " + value);
      }
//      System.out.println(ast);
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}

