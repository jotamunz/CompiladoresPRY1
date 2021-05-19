
package model.compiler.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.compiler.scanner.Lexer;

public class SyntacticAnalyzer {
    
    public void parse(String path){
        try {
            parser par = new parser(new Lexer(new FileReader(path)));
            par.parse();
            System.out.println("Success");
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (Exception ex) {
            Logger.getLogger(SyntacticAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
 