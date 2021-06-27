
package model.compiler.translator;

public class SemanticError {
    private int lineNumber;
    private int colNumber;
    private String tokenValue;
    private String message;

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

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public String getMessage() {
        return message;
    }
    
    
    
    
}
