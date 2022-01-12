import java.io.*;
import java.util.Hashtable;
import java.util.List;

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
      Hashtable symtable =  new Hashtable();
      Hashtable<String, List<Function>> functions =  new Hashtable();
//      ast.apply(new SymbolTableFiller(symtable));
      ast.apply(new Visitor1(functions));
      System.out.println(functions);
      /* Gia ton deutero visitor grapste thn entolh
       * ast.apply(new mysecondvisitor(symtable));
       */

//      System.out.println(ast);
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}

