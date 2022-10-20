package lexical;

public class Lexical {
  private int arrayPosition;
  private String[] arr;

  private void addToArray(String str) {
    arr[arrayPosition] = str;
    arrayPosition++;
  }
  private String getTokens(String str) {
    int strLen = str.length();
    switch (strLen) {
      case 1:
        switch (str) {
          case ";":
            return "semicolon";
          case "=":
            return "possible assignment";
          case "(":
            return "open parenthesis";
          case ")":
            return "close parenthesis";
          case "{":
            return "open braces";
          case "}":
            return "close braces";
          case "/":
            return "division operator";
          case "*":
            return "multiplication operator";
          case "-":
            return "subtraction operator";
          case "+":
            return "addition operator";
          case "<":
            return "possible comparison (<)";
          case ">":
            return "possible comparison (>)";
          default:
            if(str.matches("[0-9]+")){
              return "possible literal integer ("+str+")";
            }
            if(str.matches("[a-z]+")){
              return "possible identifier ("+str+")";
            }
            return "ERROR INVALID TOKEN";
        }
      case 2:
        switch (str) {
          case "if":
            return "keyword if";
          case "<=":
            return "comparison (<=)";
          case ">=":
            return "comparison (>=)";
          case "==":
            return "comparison (==)";
          case "!=":
            return "comparison (!=)";
          default:
          if(str.matches("[0-9]+")){
            return "possible literal integer ("+str+")";
          }
          if(str.matches("[a-z]+")){
            return "possible identifier ("+str+")";
          }
            return "ERROR INVALID TOKEN";
        }
      case 5:
      switch (str) {
        case "while":
          return "keyword while";
        case "print":
          return "keyword print";
        default:
          if(str.matches("[0-9]+")){
            return "possible literal integer ("+str+")";
          }
          if(str.matches("[a-z]+")){
            return "possible identifier ("+str+")";
          }
          return "ERROR INVALID TOKEN";
      }
      default:
        if(str.matches("[0-9]+")){
          return "possible literal integer ("+str+")";
        }
        if(str.matches("[a-z]+")){
          return "possible identifier ("+str+")";
        }
        return "ERROR INVALID TOKEN";
    }
  }
  public String[] parseForTokens(String str) {
    arrayPosition = 0;
    arr = new String[256];
    String out = "";
    String tempString;
    String tempString2;
    int i = 0;
    int j = 1;
    while (j < str.length() + 1) {
      tempString = getTokens(str.substring(i, j));
      if (tempString.substring(0,8).equals("possible")){
        tempString2 = checkToken(str.substring(i));
        i += Integer.valueOf(tempString2.substring(tempString2.length()-1));
        j=i;
        if(j!=str.length()){
          out += tempString2.substring(0,tempString2.length()-1) + "\n";
          addToArray(tempString2.substring(0,tempString2.length()-1));
        }else{
          out += tempString2.substring(0,tempString2.length()-1);
          addToArray(tempString2.substring(0,tempString2.length()-1));
        }
      }
      else if (tempString != "ERROR INVALID TOKEN") {
        i = j;
        if(j!=str.length()){
          out += tempString + "\n";
          addToArray(tempString);
        }else{
          out += tempString;
          addToArray(tempString);
        }
      }
      j++;
    }
    if (out.equals("")) {
      return arr;
    }
    System.out.println(out);
    return arr;
  }
  private String checkToken(String str){
    String tempString;
    int i = 1;
    while(i<str.length()+1 && getTokens(str.substring(i-1, i)).substring(0,8).equals("possible")){
      i++;
    }
    if(i!=1){
      i--;
      tempString = getTokens(str.substring(0, i));
    }
    else{
      tempString = getTokens(str.substring(0, i));
    }
    if(tempString.substring(0,8).equals("possible")){
      return tempString.substring(9)+i;
    }
    return tempString+i;
  }
}
