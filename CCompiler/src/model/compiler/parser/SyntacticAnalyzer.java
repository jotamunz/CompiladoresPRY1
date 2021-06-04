
package model.compiler.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import model.compiler.scanner.Lexer;
import model.compiler.scanner.Token;

public class SyntacticAnalyzer {
    
    private ArrayList<Token> errorTokens = new ArrayList<>();
    
    public void parse(String path){
        try {
            parser par = new parser(new Lexer(new FileReader(path)));
            par.parse();
            errorTokens = par.errors;
            System.out.println("Finished Parse");
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (Exception ex) {
            System.out.println("Exception");
        }
    }
    
    public void printErrorTokens(){
        for (Token token : errorTokens) {
            System.err.println("Syntax error at line: " + token.getLineNum() + " at column: " + token.getColNum() + " on: " + token.getValue());
        }
    }
    
    public ArrayList<Token> getErrorTokens() {
        return errorTokens;
    }
}
 