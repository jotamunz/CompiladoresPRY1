
package model.compiler.translator.SemanticRegisters;

public class RsId extends Register {
    public String name;

    public RsId(String name, int line, int col) {
        super(line, col);
        this.name = name;
    }

}
