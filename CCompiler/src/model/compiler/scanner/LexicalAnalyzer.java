
package model.compiler.scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import static model.compiler.parser.sym.EOF;

public class LexicalAnalyzer {
    
    private ArrayList<Token> tokens = new ArrayList<>();
    
    public void scan(String path){
        try {
            tokens = new ArrayList<>();
            Lexer lex = new Lexer(new FileReader(path));
            Symbol nextSym;
            // Read tokens
            while ((nextSym = lex.next_token()).sym != EOF){
                tokens.add(new Token(nextSym.sym, nextSym.left, nextSym.right, nextSym.value));
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

    public ArrayList<Token> getTokens() {
        return tokens;
    }
    
    
}
    

