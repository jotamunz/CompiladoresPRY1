
package model.compiler.translator.SemanticRegisters;

public class RsType extends Register{
    public String type;

    public RsType(String type, int line, int col) {
        super(line, col);
        this.type = type;
    }


    
}
