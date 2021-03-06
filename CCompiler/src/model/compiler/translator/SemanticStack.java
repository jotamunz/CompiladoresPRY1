
package model.compiler.translator;

import java.util.ArrayList;
import model.compiler.translator.SemanticRegisters.Register;

public class SemanticStack {
    private ArrayList<Register> stack = new ArrayList<>();
    public int ifCounter = 0;
    public int whileCounter = 0;
    
    public Register pop(){
        if (!stack.isEmpty())
            return stack.remove(stack.size()-1);
        return null;
    }
    
    public Register peek(){
        if (!stack.isEmpty())
            return stack.get(stack.size()-1);
        return null;
    }
    
    public void push(Register rs){
        stack.add(rs);
    }
    
    public Register findNearest(Class rsClass){
        if (!stack.isEmpty()) {
            for (int i = stack.size()-1; i >= 0; i--) {
                if (stack.get(i).getClass().equals(rsClass)) { 
                    return stack.get(i);
                }
            }
        }
        return null;
    }
}
