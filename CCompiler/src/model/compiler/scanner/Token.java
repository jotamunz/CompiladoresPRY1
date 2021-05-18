    
package model.compiler.scanner;

import java_cup.runtime.*;

public class Token extends Symbol{
    private Sym name;
    private int lineNum;
    private int colNum;
    
    public Token(Sym name, Object value, int lineNumber, int colNumber){
        super(name.hashCode(), value);
        this.name = name;
        // Line and column numbers are switched to 1-based
        this.lineNum = lineNumber + 1;
        this.colNum = colNumber + 1;
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
    
    public int getColNum(){
        return this.colNum;
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
