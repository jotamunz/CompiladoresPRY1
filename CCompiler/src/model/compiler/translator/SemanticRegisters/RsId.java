/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.compiler.translator.SemanticRegisters;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RsId extends Register {
    public String name;

    public RsId(String name, int row, int col) {
        super(row, col);
        this.name = name;
    }

}
