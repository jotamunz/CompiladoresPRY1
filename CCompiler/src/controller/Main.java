
package controller;

import model.compiler.Model;
import model.compiler.parser.SyntacticAnalyzer;
import view.View;

public class Main {
    
    public static void main(String[] args) {
        //Controller controller = new Controller(new Model(), new View());
        //controller.start(); 
        SyntacticAnalyzer s = new SyntacticAnalyzer();
        s.parse("prueba.txt");
    }
}
