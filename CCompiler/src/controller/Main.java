
package controller;

import model.compiler.Model;
import view.View;

public class Main {
    
    public static void main(String[] args) {
        Controller controller = new Controller(new Model(), new View());
        controller.start(); 
    }
}
