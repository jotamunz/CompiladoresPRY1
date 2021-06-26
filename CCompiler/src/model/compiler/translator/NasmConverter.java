/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compiler.translator;

import model.compiler.translator.SemanticRegisters.RsDO;

/**
 *
 * @author joaqu
 */
public class NasmConverter {
    private String nasmCode;
    private boolean isInCodeSection;

    public NasmConverter() {
        this.isInCodeSection = false;
        this.nasmCode = 
                "%include \"io.mac\" \n\n" + 
                ".DATA \n" + 
                "out_msg\t db \"Results: \",0 \n\n" + 
                ".UDATA \n";
    }
    
    public void declareVariable(String var){
        nasmCode += var + "\t resd \t 1 \n";
    }
    
    public void initCode(){
        nasmCode += 
                "\n.CODE \n" +
                "\t.STARTUP \n";
    }
    
    public void doExpressionBinary(RsDO rsDO1, RsDO rsDO2, String op){
        String operand1 = rsDO1.value;
        String operand2 = rsDO2.value;
        if(!this.isInCodeSection){
            this.isInCodeSection = true;
            this.initCode();
        }
        if("address".equals(rsDO1.type) && "address".equals(rsDO2.type)){
            // sd
        }
        else{
            if("address".equals(rsDO1.type)){
                switch (op){
                    case "+":
                        this.nasmCode += "\tmov EAX, [" + operand1 + "] \n";
                        this.nasmCode += "\tadd EAX, " + operand2 + "\n";
                        break;
                    case "-":
                        this.nasmCode += "\tmov EAX, [" + operand1 + "] \n";
                        this.nasmCode += "\tsub EAX, " + operand2 + "\n\n";
                        break;
                    case "*":
                        this.nasmCode += "\tmov EAX, [" + operand1 + "] \n";
                        this.nasmCode += "\tmov ECX, " + operand2 + "\n";
                        this.nasmCode += "\tmul ECX \n\n";
                        break;
                    case "/":
                        this.nasmCode += "\tmov EAX, [" + operand1 + "] \n";
                        this.nasmCode += "\tmov EDX, 0\n";
                        this.nasmCode += "\tmov ECX, " + operand2 + "\n";
                        this.nasmCode += "\tdiv ECX \n\n";
                        break;
                    case "%":
                        this.nasmCode += "\tmov EAX, [" + operand1 + "] \n";
                        this.nasmCode += "\tmov EDX, 0\n";
                        this.nasmCode += "\tmov ECX, " + operand2 + "\n";
                        this.nasmCode += "\tdiv ECX \n";
                        this.nasmCode += "\tmov EAX,EDX \n\n";
                        break;
                }        
            }
            else{
                switch (op){
                    case "+":
                        this.nasmCode += "\tmov EAX, [" + operand2 + "] \n";
                        this.nasmCode += "\tadd EAX, " + operand1 + "\n\n";
                        break;
                    case "-":
                        this.nasmCode += "\tmov EBX, [" + operand2 + "] \n";
                        this.nasmCode += "\tmov EAX, " + operand1 + "\n";
                        this.nasmCode += "\tsub EAX, EBX\n\n";
                        break;
                    case "*":
                        this.nasmCode += "\tmov EAX, [" + operand2 + "] \n";
                        this.nasmCode += "\tmov ECX, " + operand1 + "\n";
                        this.nasmCode += "\tmul ECX \n\n";
                        break;
                    case "/":
                        this.nasmCode += "\tmov EAX, " + operand1 + "\n";
                        this.nasmCode += "\tmov EDX, 0\n";
                        this.nasmCode += "\tmov ECX, [" + operand2 + "]\n";
                        this.nasmCode += "\tdiv ECX \n\n";
                        break;
                    case "%":
                        this.nasmCode += "\tmov EAX, " + operand1 + "\n";
                        this.nasmCode += "\tmov EDX, 0\n";
                        this.nasmCode += "\tmov ECX, [" + operand2 + "]\n";
                        this.nasmCode += "\tdiv ECX \n";
                        this.nasmCode += "\tmov EAX,EDX \n\n";
                        break;
                } 
            }
        }
    }
    
    public void assign(RsDO var, RsDO value){
        
        switch(value.type){
            case "asmRegister":
                this.nasmCode += "\tmov dword[" + var.value + "], EAX \n\n";
                break;
            case "constant":
                this.nasmCode += "\tmov dword[" + var.value + "]," +  value.value + "\n\n";
                break;
            case "address":
                this.nasmCode += "\tmov EAX, [" + value.type + "] \n";
                this.nasmCode += "\tmov dword[" + var.value + "], EAX \n\n";
        }
        this.nasmCode += "\tsub EAX,EAX";
    }
    
    public void print(){
        System.err.println("TRADUCCIÓN");
        System.out.println(this.nasmCode);
    }
    
}
