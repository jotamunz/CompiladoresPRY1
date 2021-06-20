
package model.compiler.translator.SemanticRegisters;

public class RsType extends Register{
    public String type;

    public RsType(String type, int row, int col) {
        super(row, col);
        this.type = type;
    }


    
}
