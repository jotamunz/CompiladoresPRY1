    
package model.compiler.scanner;

import java_cup.runtime.*;

public class Token extends Symbol{
    private Sym name;
    private int lineNum;
    
    public Token(Sym name, Object value, int lineNumber){
        super(name.hashCode(), value);
        this.name = name;
        // Line numbers are switched to 1-based
        this.lineNum = lineNumber + 1;
    }
    
    public Sym getName(){
        return this.name;
    }
    
    public Object getValue(){
        return this.value;
    }
    
    public int getLineNum(){
        return this.lineNum;
    }
    
    @Override
    public String toString(){
        String res;  
        res = this.value.toString() + "\t\t";
        res += this.name.toString() + "\t\t";
        res += String.valueOf(this.lineNum);
        return res;
    } 
}
