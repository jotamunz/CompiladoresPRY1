
package model.compiler.translator;

import java.util.ArrayList;
import java.util.HashMap;
import model.compiler.translator.SemanticRegisters.RsId;
import model.compiler.translator.SemanticRegisters.RsType;
import model.compiler.translator.Symbols.IdentifierData;
import model.compiler.translator.Symbols.VariableData;

public class Translator {
    private ArrayList<SemanticError> semanticErrors = new ArrayList<>();
    private HashMap<String, IdentifierData> symbolTable = new HashMap<>();
    private SemanticStack stack = new SemanticStack();
        
    public ArrayList<SemanticError> getSemanticErrors() {
        return semanticErrors;
    }
    
    //Variable declaration

    public void rememberType(String type,int line, int col){
        RsType rs = new RsType(type,line,col);
        stack.push(rs);    
    }
    
    public void rememberId(String id, int line, int col){
        RsId rs = new RsId(id,line,col);
        stack.push(rs);
    }
    
    
    // int x,y,x; da el error en la primera x porque en la pila está primero la segunda.
    public void insertTS(){
        RsType rsType = (RsType)stack.findNearest(RsType.class);
        while (!stack.peek().getClass().equals(RsType.class)) {            
            RsId rsId = (RsId) stack.pop();
            if(! symbolTable.containsKey(rsId.name)){
               VariableData variableData = new VariableData(rsType.type, rsId.name);
               symbolTable.put(rsId.name, variableData);
            } else if(!symbolTable.get(rsId.name).hasError()){
               symbolTable.get(rsId.name).addError();
               String errorMessage =  "variable already declared";  //deberiamos definir los errores
               SemanticError error = new SemanticError(rsId.line, rsId.col, rsId.name, errorMessage);     
               semanticErrors.add(0, error); //insertar al inicio para que queden en orden
            } 
        }
        stack.pop(); 
    }



    
}
