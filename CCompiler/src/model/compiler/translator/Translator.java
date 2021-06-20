
package model.compiler.translator;

import java.util.ArrayList;
import java.util.HashMap;
import model.compiler.translator.Symbols.IdentifierData;

public class Translator {
    private ArrayList<SemanticError> semanticErrors = new ArrayList<>();
    private HashMap<String, IdentifierData> symbolTable = new HashMap<>();
    private SemanticStack stack = new SemanticStack();
    
    public void rememberType(){
    }
}
