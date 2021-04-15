
package scanner;

public class Token {
    TokenType type;
    Object value;
    int lineNumber;
    
    public Token(TokenType type, Object value, int lineNumber){
        this.type = type;
        this.value = value;
        this.lineNumber = lineNumber + 1; // +1 because first line in jflex is 0 
    }
    
    @Override
    public String toString(){
        String res;
        res = "Type: " + this.type.toString() + "\t\t";
        res += "Value: " + this.value.toString() + "\t\t";
        res += "lineNumber: " + this.lineNumber;
        return res;
    }
}
