import java.io.*;
import java.util.Hashtable;

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
      ast.apply(new SymbolTableFiller(symtable));
      System.out.println(symtable);
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

