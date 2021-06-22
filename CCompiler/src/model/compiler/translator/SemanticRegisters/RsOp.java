/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compiler.translator.SemanticRegisters;

/**
 *
 * @author joaqu
 */
public class RsOp extends Register{
    public String operator;

    public RsOp(String operator, int row, int col) {
        super(row, col);
        this.operator = operator;
    }        
}
