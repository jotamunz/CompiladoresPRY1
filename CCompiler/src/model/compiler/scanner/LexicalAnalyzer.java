
package model.compiler.scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import static model.compiler.parser.sym.EOF;
import static model.compiler.parser.sym.LEX_ERROR;

public class LexicalAnalyzer {
    
    private ArrayList<Token> tokens = new ArrayList<>();
    
    public void scan(String path){
        try {
            tokens = new ArrayList<>();
            Lexer lex = new Lexer(new FileReader(path));
            Symbol nextSym;
            // Read tokens
            while ((nextSym = lex.next_token()).sym != EOF){
                tokens.add(new Token(nextSym.sym, nextSym.right, nextSym.left, nextSym.value));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (IOException ex) {
            System.out.println("IO Exception");
        }
    }
    
    public void printTokens(){
        System.out.println("VALUE\t\tTYPES\t\tLINES");
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }
    
    public void printErrorTokens(){
        for (Token token : tokens) {
            if (token.getId() == LEX_ERROR){
                System.err.println("Lexical error at line: " + token.getLineNum() + " at column: " + token.getColNum() + " on: " + token.getValue());
            }
        }
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
    
    
}
    

