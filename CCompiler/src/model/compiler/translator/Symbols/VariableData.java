
package model.compiler.translator.Symbols;

public class VariableData implements IdentifierData {
    private String type;
    private String name;
    private boolean error;

    public VariableData(String type, String name) {
        this.type = type;
        this.name = name;
        this.error = false;
    }
    
    @Override
    public void addError(){
        this.error = true;
    }

    @Override
    public boolean hasError() {
        return error;
    }
    
    @Override
    public String toString(){
        return type ;
    }
    

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isError() {
        return error;
    }
    
    
}
