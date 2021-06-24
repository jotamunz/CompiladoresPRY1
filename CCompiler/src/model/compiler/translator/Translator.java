
package model.compiler.translator;

import java.util.ArrayList;
import java.util.HashMap;
import model.compiler.translator.SemanticRegisters.*;
import model.compiler.translator.Symbols.FunctionData;
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

    public void rememberType(Object type, int line, int col){
        RsType rs = new RsType(String.valueOf(type), line, col);
        stack.push(rs);    
    }
    
    public void rememberIdVar(Object id, int line, int col){
        RsId rs = new RsId(String.valueOf(id), false, line, col);
        stack.push(rs);
    }
    
    public void insertTableVar(){
        RsType rsType = (RsType) stack.findNearest(RsType.class);
        while (!stack.peek().getClass().equals(RsType.class)) {            
            RsId rsId = (RsId) stack.pop();
            if (isNewId(rsId)){
                symbolTable.put(rsId.name, new VariableData(rsType.type, rsId.name));
            }
        }
        stack.pop(); 
    }
    
    private boolean isNewId(RsId rsId){
        if (!symbolTable.containsKey(rsId.name)){
            return true;
        } else if (!symbolTable.get(rsId.name).hasError()){
            symbolTable.get(rsId.name).addError();
            String errorMessage = "identifier name already declared";  //deberiamos definir los errores
            SemanticError error = new SemanticError(rsId.line, rsId.col, rsId.name, errorMessage);     
            semanticErrors.add(error); 
        } 
        return false;
    }
    
    public void rememberIdFunc(Object id, int line, int col){
        RsId rs = new RsId(String.valueOf(id), true, line, col);
        stack.push(rs);
    }
    
    public void insertTableFunc(){
        ArrayList<VariableData> parameters = new ArrayList<>();
        while(!((RsId) stack.peek()).isFunc){
            RsId rsId = (RsId) stack.pop();
            RsType rsType = (RsType) stack.pop();
            VariableData varData = new VariableData(rsType.type, rsId.name);
            if (isNewId(rsId)){
                symbolTable.put(rsId.name, varData);
            }
            parameters.add(0, varData);
        }
        RsId rsId = (RsId) stack.pop();
        RsType rsType = (RsType) stack.pop();
        if (isNewId(rsId)){ // discriminar por cantidad de parametros
            FunctionData funcData = new FunctionData(rsType.type, rsId.name, parameters);
            symbolTable.put(rsId.name, funcData);
        }
    }

    public void rememberConst(Object value, int line, int col){
        RsDO rs = new RsDO("constant", String.valueOf(value), line, col);
        stack.push(rs);
    }
    
    public void rememberOp(Object operator, int line, int col){
        RsOp rs = new RsOp(String.valueOf(operator), line, col);
        stack.push(rs);
    }    
    
    public void rememberVar(Object id, int line, int col){
        RsDO rs = new RsDO("address", String.valueOf(id), line, col);
        if(!symbolTable.containsKey(rs.value)){
            VariableData variableData = new VariableData("Unknown", rs.value); // Si no está declarada no sabemos el tipo
            variableData.addError(); 
            symbolTable.put(rs.value, variableData);
            String errorMessage = "variable undeclared";  //deberiamos definir los errores
            SemanticError error = new SemanticError(rs.line, rs.col, rs.value, errorMessage);     
            semanticErrors.add(error);        
        }
        stack.push(rs);
    }
    
    public void evalBinary(){
        RsDO rsDO1 = (RsDO) stack.pop();
        RsOp rsOp = (RsOp) stack.pop();
        RsDO rsDO2 = (RsDO) stack.pop();
        RsDO rsDOresult;
        if("constant".equals(rsDO1.type) && "constant".equals(rsDO2.type)){
            int value = constantFold(rsDO2.value, rsOp.operator, rsDO1.value);
            rsDOresult = new RsDO("constant", String.valueOf(value), rsDO2.line, rsDO2.col);
        }
        else{
            // generar código para la operación
            rsDOresult = new RsDO("address", "register", rsDO2.line, rsDO2.col);
        }
        System.out.println(rsDOresult);
        stack.push(rsDOresult);
    }
    
    public void evalUnary() {
        // mismo que el de arriba pero para ++, --
    }
    
    private int constantFold(String value1, String op, String value2) { // al tomar el valor del string, cual tamano usar Ej. Integer.valueOf()
        int operand1 = Integer.valueOf(value1);
        int operand2 = Integer.valueOf(value2);
        switch (op){
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "%":
                return operand1 % operand2;
            case "==":
                if (operand1 == operand2)
                    return 1;
                break;
            case ">=":
                if (operand1 >= operand2)
                    return 1;
                break;
            case ">":
                if (operand1 > operand2)
                    return 1;
                break;
            case "<=":
                if (operand1 <= operand2)
                    return 1;
                break;
            case "<":
                if (operand1 < operand2)
                    return 1;
                break;
            case "!=":
                if (operand1 != operand2)
                    return 1;
                break;
            case "&&":
                if (operand1 != 0 && operand2 != 0)
                    return 1;
                break;
            case "||":
                if (operand1 != 0 || operand2 != 0)
                    return 1;
                break;
        }
        return 0;
    }
    
    public void assign(){
        RsDO rsDO1 = (RsDO) stack.pop();
        RsOp rsOp = (RsOp) stack.pop();
        RsDO rsDO2 = (RsDO) stack.pop();
        // generar código para la asignacion
    }
    
    public void rememberFunc(Object id, int line, int col){
        // validar que existe en la tabla de simbolos
        // Corroborar cantidad y tipo de parámetros
    }
}
