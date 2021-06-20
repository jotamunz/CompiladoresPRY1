/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.compiler.translator;

import java.io.Console;
import java.util.Collections;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class translatorMain {
    public static void main(String[] args) {
        Translator translator = new Translator();
        
        // int x,x,y,z,w;
        translator.rememberType("int", 0, 0);
        translator.rememberId("x", 1, 4);
        translator.rememberId("x", 1, 1);
        translator.rememberId("y", 1, 2);
        translator.rememberId("z", 2, 2);
        translator.rememberId("w", 2, 2);
        translator.insertTS();
        
        //int x,y,w;
        translator.rememberType("int", 0, 0);
        translator.rememberId("x", 1, 2);
        translator.rememberId("y", 2, 2);
        translator.rememberId("w", 3, 2);
        translator.insertTS();
        
        
        for (SemanticError semanticError : translator.getSemanticErrors()) {
            System.err.println(semanticError.toString());
        }
    }
}
