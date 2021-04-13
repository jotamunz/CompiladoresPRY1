
package scanner;

public class Token {
    TokenType type;
    Object value;
    
    public Token(TokenType type){
        this.type = type;
    }
    
    public Token(TokenType type, Object value){
        this.type = type;
        this.value = value;
    }
    
    @Override
    public String toString(){
        String res;
        res = "Type: " + this.type.toString() + "\t\t";
        res += "Value: " + this.value.toString();
        return res;
    }
}
