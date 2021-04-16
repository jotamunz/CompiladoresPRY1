
package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scanner {
    
    private HashMap<String, Token> uniqueTokens = new HashMap<>();
    
    public void scan(String path){
        try {
            Lexer lex = new Lexer(new FileReader(path));
            Token newToken;
            // Read tokens
            while ((newToken = lex.nextToken()) != null){
                String id = newToken.getType().toString() + newToken.getValue().toString();
                // If first appearance add to hash
                if (!uniqueTokens.containsKey(id)){
                    uniqueTokens.put(id, newToken);
                }
                // Else add line number to existing token in hash
                else {
                    uniqueTokens.get(id).getLines().add(newToken.getLines().get(0));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printUniqueTokens(){
        System.out.println("VALUE\t\tTYPES\t\tLINES");
        for (Token token : uniqueTokens.values()) {
            System.out.println(token.toString());
        }
    }
}
    

