
package scanner;

public enum TokenType {
    
    KEYWORD,
    
    OPERATOR_ARITHMETIC,
    OPERATOR_LOGICAL,
    OPERATOR_STRUCTURE,
    
    IDENTIFIER,
    
    // LITERALS
    INTEGER,
    FLOAT,
    STRING,
    CHAR,

    ERROR
}
