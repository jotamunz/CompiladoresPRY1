
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
    private NasmConverter nasmConverter = new NasmConverter();
    
    public void printNasmCode(){
        this.nasmConverter.print();
    }

    public NasmConverter getNasmConverter() {
        return nasmConverter;
    }
   
    public ArrayList<SemanticError> getSemanticErrors() {
        return semanticErrors;
    }
    
    public HashMap<String, IdentifierData> getSymbolTable() {
        return symbolTable;
    }
    
    public void initCode(){
        this.nasmConverter.initCode();
    }

    public void rememberType(Object type, int line, int col){
        RsType rs = new RsType(String.valueOf(type), line, col);
        stack.push(rs);    
    }
    
    public void rememberIdVar(Object id, int line, int col){
        RsId rs = new RsId(String.valueOf(id), false, line, col);
        this.nasmConverter.declareVariable(String.valueOf(id));        
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
        if (isNewId(rsId)){
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
            int value = constFoldBinary(rsDO2.value, rsOp.operator, rsDO1.value);
            rsDOresult = new RsDO("constant", String.valueOf(value), rsDO2.line, rsDO2.col);
        } else{
            this.nasmConverter.doExpressionBinary(rsDO2, rsDO1, rsOp.operator);
            // generar código para la operación
            rsDOresult = new RsDO("asmRegister", "EAX", rsDO2.line, rsDO2.col); // Lo último siempre quedara en el registro EAX
        }
        stack.push(rsDOresult);
    }
    
    public void evalUnary(boolean isPostfix) {
        RsDO rsDO;
        RsOp rsOp;
        RsDO rsDOresult;
        if (isPostfix){
            rsOp = (RsOp) stack.pop();
            rsDO = (RsDO) stack.pop();
        } else {
            rsDO = (RsDO) stack.pop();
            rsOp = (RsOp) stack.pop();
        }
        if ("constant".equals(rsDO.type)){
            int value = constFoldUnary(rsDO.value, rsOp.operator, isPostfix);
            rsDOresult = new RsDO("constant", String.valueOf(value), rsDO.line, rsDO.col);
        } else {
            this.nasmConverter.doExpressionUnary(rsDO, rsOp.operator, isPostfix);
            rsDOresult = new RsDO("asmRegister", "EAX", rsDO.line, rsDO.col);
        }
        stack.push(rsDOresult);
    }
    
    private int constFoldBinary(String value1, String op, String value2) { // al tomar el valor del string, cual tamano usar Ej. Integer.valueOf()
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
    
    private int constFoldUnary(String value, String op, boolean isPostfix){
        int operand = Integer.valueOf(value);
        switch (op){
            case "++":
                if (isPostfix)
                    return operand++;
                else
                    return ++operand;
            case "--":
                if (isPostfix)
                    return operand--;
                else
                    return --operand;
            case "!":
                if (operand == 0)
                    return 1;
            case "+":
                return +operand;
            case "-":
                return -operand;
        }
        return 0;
    }
     
    public void rememberFunc(Object id, int line, int col){
        RsDO rs = new RsDO("function", String.valueOf(id), line, col);
        if(!symbolTable.containsKey(rs.value)){
            FunctionData functionData = new FunctionData("Unknown", rs.value, new ArrayList<>()); // Si no está declarada no sabemos el tipo
            functionData.addError();
            symbolTable.put(rs.value, functionData);
            String errorMessage = "function undeclared";  //deberiamos definir los errores
            SemanticError error = new SemanticError(rs.line, rs.col, rs.value, errorMessage);     
            semanticErrors.add(error);        
        }
        stack.push(rs);
    }
    
    public void validateParams(){ // no aguanta asignaciones, otras funciones o operaciones con variables en los parametros
        ArrayList<RsDO> params = new ArrayList<>();
        while(!"function".equals(((RsDO) stack.peek()).type)){
            RsDO param = (RsDO) stack.pop();
            params.add(0, param);
        }
        RsDO rsFunc = (RsDO) stack.peek();
        FunctionData functionData = (FunctionData) symbolTable.get(rsFunc.value);
        if (!functionData.hasError()){
            if (functionData.parameterAmount != params.size()){
                String errorMessage = "incorrect parameter amount; expected: " + functionData.parameterAmount;  //deberiamos definir los errores
                SemanticError error = new SemanticError(rsFunc.line, rsFunc.col, rsFunc.value, errorMessage);     
                semanticErrors.add(error);   
            } else {
                for (int i = 0; i < functionData.parameterAmount; i++){
                    RsDO rsParam = params.get(i);
                    if ("constant".equals(rsParam.type)) {
                        // TO DO
                    } else {
                        VariableData paramData = (VariableData) symbolTable.get(rsParam.value);
                        if (!paramData.hasError() && functionData.parameterData.get(i).getType().equals(paramData.getType())) {
                            String errorMessage = "incorrect parameter type; expected: " + functionData.parameterData.get(i).getType();  //deberiamos definir los errores
                            SemanticError error = new SemanticError(rsParam.line, rsParam.col, rsParam.value, errorMessage);     
                            semanticErrors.add(error);   
                        }
                    }
                }
            }
        }
    }
    
    public void assign(){
        RsDO rsDO1 = (RsDO) stack.pop();
        RsOp rsOp = (RsOp) stack.pop();
        RsDO rsDO2 = (RsDO) stack.pop();
        // generar código para la asignacion
        this.nasmConverter.assign(rsDO2, rsDO1);
    }
    
    public void validateBreakContinue(Object value, int line, int col){
        if (stack.findNearest(RsWhile.class) == null){
            String errorMessage = "no loop found";  //deberiamos definir los errores
            SemanticError error = new SemanticError(line+1, col+1, String.valueOf(value), errorMessage);     
            semanticErrors.add(error);   
        }
    }
    
    public void startIf(int line, int col){
        RsIf rs = new RsIf(stack.ifCounter, line, col);
        stack.ifCounter++;
        stack.push(rs);
    }
    
    public void testIf(){
        RsDO rsDO = (RsDO) stack.pop();
        RsIf rsIf = (RsIf) stack.findNearest(RsIf.class);
        this.nasmConverter.testIf(rsDO, rsIf.elseLabel);
    }
    
    public void startElse(){
        RsIf rsIf = (RsIf) stack.findNearest(RsIf.class);
        this.nasmConverter.startElse(rsIf.elseLabel, rsIf.exitLabel);
    }
    
    public void endIf(){
        RsIf rsIf = (RsIf) stack.findNearest(RsIf.class);
        this.nasmConverter.endIf(rsIf.exitLabel);
        
        while (!stack.peek().getClass().equals(RsIf.class)){
            stack.pop();
        }
        stack.pop();
    }
    
    public void startWhile(int line, int col){
        RsWhile rs = new RsWhile(stack.whileCounter, line, col);
        stack.whileCounter++;
        this.nasmConverter.startWhile(rs.whileLabel);
        stack.push(rs);
    }
    
    public void testWhile(){
        RsDO rsDO = (RsDO) stack.pop();
        RsWhile rs = (RsWhile) stack.findNearest(RsWhile.class);
        this.nasmConverter.testWhile(rsDO, rs.exitLabel);
    }

    public void endWhile(){
        RsWhile rs = (RsWhile) stack.findNearest(RsWhile.class);
        this.nasmConverter.endWhile(rs.whileLabel, rs.exitLabel);
        while (!stack.peek().getClass().equals(RsWhile.class)){
            stack.pop();
        }
        stack.pop();
    }
    
    public void endNasmCode(){
        this.nasmConverter.endOfCode();
    }
}
