
package model.compiler.translator.Symbols;

import java.util.ArrayList;

public class FunctionData implements IdentifierData {
    public String type;
    public String name;
    public int parameterAmount;
    public ArrayList<VariableData> parameterData;
    private boolean error;

    public FunctionData(String type, String name, ArrayList<VariableData> parameterData) {
        this.type = type;
        this.name = name;
        this.parameterData = parameterData;
        this.parameterAmount = parameterData.size();
        this.error = false;
    }
    
    public FunctionData(String type, String name) {
        this.type = type;
        this.name = name;
        this.parameterAmount = 0;
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
}
