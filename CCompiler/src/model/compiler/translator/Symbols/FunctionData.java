
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
        String result = type + " " + name + "(";
        for (int i = 0; i < parameterAmount; i++){
            if (i == parameterAmount - 1)
                result += parameterData.get(i).getType();
            else
                result += parameterData.get(i).getType() + ", ";
        }
        result += ")";
        return result + " ERROR: " + error;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getParameterAmount() {
        return parameterAmount;
    }

    public ArrayList<VariableData> getParameterData() {
        return parameterData;
    }

    public boolean isError() {
        return error;
    }
    
    
    
}
