
package model.compiler.translator.SemanticRegisters;

public abstract class Register {
    public int line;
    public int col;

    public Register(int line, int col) {
        this.line = line;
        this.col = col;
    }
    
    
}
