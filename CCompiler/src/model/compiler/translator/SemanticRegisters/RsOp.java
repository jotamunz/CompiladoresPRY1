
package model.compiler.translator.SemanticRegisters;

public class RsOp extends Register{
    public String operator;

    public RsOp(String operator, int line, int col) {
        super(line, col);
        this.operator = operator;
    }        
}
