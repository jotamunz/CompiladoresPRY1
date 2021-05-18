
package model.compiler.scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Scanner {
    
    private ArrayList<Token> tokens = new ArrayList<>();
    
    public void scan(String path){
        try {
            tokens = new ArrayList<>();
            Lexer lex = new Lexer(new FileReader(path));
            Token newToken;
            // Read tokens
            while ((newToken = lex.nextToken()) != null){
                tokens.add(newToken);
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
    

