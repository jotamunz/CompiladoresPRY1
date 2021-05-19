    
package model.compiler.scanner;

import static model.compiler.parser.sym.terminalNames;

public class Token {
    private int id;
    private String name;
    private Object value;
    private int lineNum;
    private int colNum;
    
    public Token(int id, int lineNumber, int colNumber, Object value){
        this.id = id;
        this.name = terminalNames[id];
        this.value = value;
        // Line and column numbers are switched to 1-based
        this.lineNum = lineNumber + 1;
        this.colNum = colNumber + 1;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
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
        res += this.name + "\t\t";
        res += String.valueOf(this.lineNum);
        return res;
    } 
}
