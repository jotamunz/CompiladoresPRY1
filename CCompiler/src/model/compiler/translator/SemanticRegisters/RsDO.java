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
public class RsDO extends Register{
    public String type;
    public String value;

    public RsDO(String type, String value, int row, int col) {
        super(row, col);
        this.type = type;
        this.value = value;
    }    
}
