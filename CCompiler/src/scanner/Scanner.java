
package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scanner {
    
    public static void main(String[] args) {
        try {
            Lexer lex = new Lexer(new FileReader("prueba.txt"));
            Token token;
            while ((token = lex.nextToken()) != null)
                System.out.println(token.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

