
package model.compiler.translator.SemanticRegisters;

public class RsId extends Register {
    public String name;
    public boolean isFunc;

    public RsId(String name,  boolean isFunc, int line, int col) {
        super(line, col);
        this.name = name;
        this.isFunc = isFunc;
    }

}
