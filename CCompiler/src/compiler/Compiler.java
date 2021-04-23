
package compiler;

import controller.Controller;
import javax.swing.JFileChooser;
import model.scanner.Model;
import model.scanner.Scanner;
import view.View;

public class Compiler {
    
    public static void main(String[] args) {
        Controller controller = new Controller(new Model(), new View());
        controller.start();
        
    }
}
