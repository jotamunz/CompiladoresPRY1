
package model.compiler.translator;

import java.util.ArrayList;
import java.util.HashMap;
import model.compiler.translator.SemanticRegisters.*;
import model.compiler.translator.Symbols.IdentifierData;
import model.compiler.translator.Symbols.VariableData;

public class Translator {
    private ArrayList<SemanticError> semanticErrors = new ArrayList<>();
    private HashMap<String, IdentifierData> symbolTable = new HashMap<>();
    private SemanticStack stack = new SemanticStack();
        
    public ArrayList<SemanticError> getSemanticErrors() {
        return semanticErrors;
    }
    
    public HashMap<String, IdentifierData> getSymbolTable() {
        return symbolTable;
    }
    
    //Variable declaration

    public void rememberType(Object type, int line, int col){
        RsType rs = new RsType(String.valueOf(type), line, col);
        stack.push(rs);    
    }
    
    public void rememberId(Object id, int line, int col){
        RsId rs = new RsId(String.valueOf(id), line, col);
        stack.push(rs);
    }
    
    // int x,y,x; da el error en la primera x porque en la pila está primero la segunda.
    public void insertSymbolTable(){
        RsType rsType = (RsType) stack.findNearest(RsType.class);
        while (!stack.peek().getClass().equals(RsType.class)) {            
            RsId rsId = (RsId) stack.pop();
            if (!symbolTable.containsKey(rsId.name)){
               VariableData variableData = new VariableData(rsType.type, rsId.name);
               symbolTable.put(rsId.name, variableData);
            } else if (!symbolTable.get(rsId.name).hasError()){
               symbolTable.get(rsId.name).addError();
               String errorMessage =  "variable already declared";  //deberiamos definir los errores
               SemanticError error = new SemanticError(rsId.line, rsId.col, rsId.name, errorMessage);     
               semanticErrors.add(error); //insertar al inicio para que queden en orden
            } 
        }
        stack.pop(); 
    }

    public void rememberConst(String value, int line, int column){
        RsDO rs = new RsDO("Const", value, line, column); // No se si el tipo es "Const"
        stack.push(rs);
    }
    
    public void rememberOperator(String operator, int line, int column){
        RsOp rs = new RsOp(operator, line, column);
        stack.push(rs);
    }    
    
    public void rememberVariable(String value, int line, int column){
        RsDO rs = new RsDO("Address", value, line, column); // No se si el tipo es "Address"
        if(!symbolTable.containsKey(rs.value)){
            VariableData variableData = new VariableData("Unknown", rs.value); // Si no está declarada no sabemos el tipo
            variableData.addError(); 
            symbolTable.put(rs.value, variableData);
            
            // Agregar el error
            String errorMessage =  "Variable undeclared";  //deberiamos definir los errores
            SemanticError error = new SemanticError(rs.line, rs.col, rs.value, errorMessage);     
            semanticErrors.add(error); //insertar al inicio para que queden en orden            
        }
        stack.push(rs);
    }
    
    public void evalBinary(){
        RsDO rsDO1 = (RsDO) stack.pop();
        RsOp rsOperator = (RsOp) stack.pop();
        RsDO rsDO2 = (RsDO) stack.pop();
        if(rsDO1.type == "Const" && rsDO2.type == "Const"){
            // calcular el resultado dependiendo de operador
            // crear RsDO tipo Const con el resultado calculado
        }
        else{
            // generar código para la operación
            // crear RsDO tipo Address
        }
        // hacer push al RsDO creado en el if o else
    }
}
