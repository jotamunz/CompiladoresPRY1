
package model.compiler.translator.SemanticRegisters;

public class RsWhile extends Register{
    public String whileLabel;
    public String exitLabel;

    public RsWhile(int whileCounter, int line, int col) {
        super(line, col);
        this.whileLabel = "while" + String.valueOf(whileCounter);
        this.exitLabel = "exitWhile" + String.valueOf(whileCounter);
    }
}
