import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import lexical.Lexical;
import syntax.Syntax;
public class Runner {
  public static void main(String[] args) {
    try {
      File myObj = new File("code.txt");
      Scanner myReader = new Scanner(myObj);
      String[] tokens = new String[256]; // Array of tokens
      int tokenCount = 0;
      /*
      1 : if
      2 : while
      3 : print
      4 : indentifier
      5 : integer
      6 : semicolon
      7 : equals
      8 : left parenthesis
      9 : right parenthesis
      10 : left brace
      11 : right brace
      12 : division
      13 : multiplication
      14 : subtraction
      15 : addition
      16 : less than
      17 : greater than
      18 : less than or equals
      19 : greater than or equals
      20 : equals
      21 : not equals
      */
      Lexical myLexical = new Lexical();
      while (myReader.hasNext()) {
        String data = myReader.next();
        String[] tempArray = myLexical.parseForTokens(data);
        int index = 0;
        while(tempArray[index]!=null) {
          tokens[tokenCount] = tempArray[index];
          ++tokenCount;
          ++index;
        }
      }
      System.out.println("Tokens: ");
      for(int x=0;x<tokenCount;x++) {
        System.out.println(tokens[x]);
      }
      Syntax mySyntax = new Syntax();
      mySyntax.analyzeSyntax(tokens);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}