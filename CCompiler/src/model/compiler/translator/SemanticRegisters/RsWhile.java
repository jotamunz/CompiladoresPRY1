
package model.compiler.translator.SemanticRegisters;

public class RsWhile extends Register{
    public String whileLabel;
    public String exitLabel;

    public RsWhile(int whileCounter, int line, int col) {
        super(line, col);
        this.whileLabel = "else" + String.valueOf(whileCounter);
        this.exitLabel = "exitIf" + String.valueOf(whileCounter);
    }
}
