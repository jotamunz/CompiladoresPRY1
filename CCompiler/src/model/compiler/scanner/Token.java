    
package model.compiler.scanner;

import java.util.ArrayList;

public class Token {
    private TokenType type;
    private Object value;
    private ArrayList<Integer> lines = new ArrayList<>();
    
    public Token(TokenType type, Object value, int lineNumber){
        this.type = type;
        this.value = value;
        // Line numbers are switched to 1-based
        this.lines.add(lineNumber + 1);
    }
    
    public TokenType getType(){
        return this.type;
    }
    
    public Object getValue(){
        return this.value;
    }
    
    public ArrayList<Integer> getLines(){
        return this.lines;
    }
    
    @Override
    public String toString(){
        String res;  
        res = this.value.toString() + "\t\t";
        res += this.type.toString() + "\t\t";
        int counter = 1;
        for (int i = 0; i < lines.size(); i++){
            int line = lines.get(i);
            // If repeating line increase counter
            if (i != 0 && lines.get(i-1) == line){
                counter += 1;
            }
            else {
                // If accumulated counter add (counter) to result
                if (counter != 1){
                    res += "(" + String.valueOf(counter) + ")";
                    counter = 1;
                }
                res += " " + String.valueOf(line);
            }
        }
        // If last element was a repetition add (counter) to result
        if (counter != 1)
            res += "(" + String.valueOf(counter) + ")";
        return res;
    } 
}
