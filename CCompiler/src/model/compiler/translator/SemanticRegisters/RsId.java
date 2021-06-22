
package model.compiler.translator.SemanticRegisters;

public class RsId extends Register {
    public String name;

    public RsId(String name, int row, int col) {
        super(row, col);
        this.name = name;
    }

}
