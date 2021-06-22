
package model.compiler.translator;

public class SemanticError {
    public int lineNumber;
    public int colNumber;
    public String tokenValue;
    public String message;

    public SemanticError(int lineNumber, int colNumber, String tokenValue, String message) {
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        this.tokenValue = tokenValue;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Semantic error " + message + " at line: " + lineNumber + " at column: " + colNumber + " on: " + tokenValue;
    }
    
    
}
