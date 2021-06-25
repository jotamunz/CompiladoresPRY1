
package model.compiler.translator.SemanticRegisters;

public class RsIf extends Register{
    public String elseLabel;
    public String exitLabel;

    public RsIf(int ifCounter, int line, int col) {
        super(line, col);
        this.elseLabel = "else" + String.valueOf(ifCounter);
        this.exitLabel = "exitIf" + String.valueOf(ifCounter);
    }
    
    
}
