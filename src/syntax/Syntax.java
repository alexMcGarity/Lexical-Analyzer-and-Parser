package syntax;

public class Syntax {
  String[] tokens;
  private int currentToken;

  private String getNextToken() {
    currentToken++;
    return tokens[currentToken];

  }

  private String getPrevToken() {
    currentToken--;
    return tokens[currentToken];

  }

  private String CheckNextToken() {
    return tokens[currentToken + 1];

  }

  public void analyzeSyntax(String[] importedTokens) {
    /*
     * pgm -> stmt
     * stmt -> ifstmt | whilestmt | printstmt | blockstmt | expr ;
     * blockstmt -> { smts }
     * stmts -> stmts stmt | stmt
     * ifstmt -> if ( expr ) stmt ;
     * whilestmt -> while ( expr ) stmt ;
     * printstmt -> print ident ;
     * expr -> id = A | A
     * A -> A == E | A != E | A < E | A > E | A <= E | A >= E | E
     * E -> E + T | E – T | T
     * T -> T * F | T / F | F
     * F -> ( expr ) | idlit
     * idlit -> ident | literal
     */
    tokens = importedTokens;
    currentToken = -1;
    if (pgm()) {
      System.out.println("Valid Syntax");
    } else {
      System.out.println("Invalid Syntax");
    }
  }

  /*
   * Checks statement follows language rules: pgm -> {stmts}
   */
  private boolean pgm() {
    String tkn = getNextToken();
    if (!tkn.equals("open braces")) {
      System.out.println("Error at program: " + tkn +  "at position " + currentToken);
      return false;
    }
    if (!stmts()) {
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("close braces")) {
      System.out.println("Error at if program: " + tkn + " at position " + currentToken);
      return false;
    }
    return true;
  }

  /*
   * Checks statement follows language rules: stmts -> one or more stmt
   */
  private boolean stmts() {
    while (!CheckNextToken().equals("close braces")) {
      if (!stmt()) {
        return false;
      }
    }
    return true;
  }

  /*
   * Checks statement follows language rules: stmt -> ifstmt | whilestmt |
   * printstmt | blockstmt | expr ;
   */
  private boolean stmt() {
    String tkn = getNextToken();
    switch (tkn) {
      case "keyword if":
        return ifstmt();
      case "keyword while":
        return whilestmt();
      case "keyword print":
        return printstmt();
      case "open braces":
        return blockstmt();
      default:
        tkn = getPrevToken();
        if (!expr()) {
          return false;
        }
        if (!getNextToken().equals("semicolon")) {
          System.out.println("Error at statement: " + tkn + " at position " + currentToken);
          return false;
        }
        return true;
    }
  }

  /*
   * Checks statement follows language rules: ifstmt -> if ( expr ) stmt;
   */
  private boolean ifstmt() {
    String tkn = getNextToken();
    if (!tkn.equals("open parenthesis")) {
      System.out.println("Error at if statement: " + tkn + " at position " + currentToken);
      return false;
    }

    if (!(expr())) {
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("close parenthesis")) {
      System.out.println("Error at if statement: " + tkn + " at position " + currentToken);
      return false;
    }
    if (!(stmt())) {
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("semicolon")) {
      System.out.println("Error at if statement: " + tkn + " at position " + currentToken);
      return false;
    }
    return true;
  }

  /*
   * Checks statement follows language rules: whilestmt -> while ( expr ) stmt;
   */
  private boolean whilestmt() {
    String tkn = getNextToken();
    if (!tkn.equals("open parenthesis")) {
      System.out.println("Error at while statement: " + tkn + " at position " + currentToken);
      return false;
    }
    if (!(expr())) {
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("close parenthesis")) {
      System.out.println("Error at while statement: " + tkn + " at position " + currentToken);
      return false;
    }
    if (!(stmt())) {
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("semicolon")) {
      System.out.println("Error at while statement: " + tkn + " at position " + currentToken);
      return false;
    }
    return true;
  }

  /*
   * Checks statement follows language rules: printstmt -> print ( ident ) ;
   */
  private boolean printstmt() {
    String tkn = getNextToken();
    if (!tkn.equals("open parenthesis")) {
      System.out.println("Error at while statement: " + tkn + " at position " + currentToken);
      return false;
    }
    if (!(ident())) {
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("close parenthesis")) {
      System.out.println("Error at while statement: " + tkn + " at position " + currentToken);
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("semicolon")) {
      System.out.println("Error at while statement: " + tkn + " at position " + currentToken);
      return false;
    }
    return true;
  }
  /*
   * Checks statement follows language rules: expr -> id = A | A
   * PLACEHOLDER METHOD! DOES NOT TYPE CHECK
   */
  private boolean ident() {
    String tkn = getNextToken();
    return true;
  }

  private boolean blockstmt() {
    String tkn;
    if (!stmts()) {
      return false;
    }
    tkn = getNextToken();
    if (!tkn.equals("close braces")) {
      System.out.println("Error at block statement: " + tkn + " at position " + currentToken);
      return false;
    }
    return true;
  }

  /*
   * Checks statement follows language rules: expr -> id = A | A
   */
  private boolean expr() {
    String tkn;
    while (!(CheckNextToken().equals("semicolon") || CheckNextToken().equals("close parenthesis")
        || CheckNextToken().equals("close braces"))) {
      //placeholder, loop through to end of expr
      tkn = getNextToken(); 
    }
    return true;
  }
}