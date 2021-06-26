
package model.compiler.translator.SemanticRegisters;

public class RsDO extends Register{
    public String type;
    public String value;

    public RsDO(String type, String value, int line, int col) {
        super(line, col);
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "RsDO{" + "type = " + type + ", value = " + value + '}';
    }
    
}
