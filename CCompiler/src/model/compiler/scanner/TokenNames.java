
package model.compiler.scanner;

public interface TokenNames {
    public static final int KEY_AUTO = 0;
    public static final int KEY_BREAK = 1;
    public static final int KEY_CASE = 2;   
    public static final int KEY_CHAR = 3; 
    public static final int KEY_CONST = 4;             
    public static final int KEY_CONT = 5;         
    public static final int KEY_DEF = 6;         
    public static final int KEY_DO = 7;         
    public static final int KEY_DOUBLE = 8;         
    public static final int KEY_ELSE = 9;         
    public static final int KEY_ENUM = 10;         
    public static final int KEY_EXT = 11;         
    public static final int KEY_FLOAT = 12;         
    public static final int KEY_FOR = 13;         
    public static final int KEY_GOTO = 14;         
    public static final int KEY_IF = 15;         
    public static final int KEY_INT = 16;         
    public static final int KEY_LONG = 17;         
    public static final int KEY_REG = 18;         
    public static final int KEY_RET = 19;         
    public static final int KEY_SHORT = 20;         
    public static final int KEY_SIG = 21;         
    public static final int KEY_SIZEOF = 22;         
    public static final int KEY_STATIC = 23;         
    public static final int KEY_STRUCT = 24;         
    public static final int KEY_SWITCH = 25;         
    public static final int KEY_TYPEDEF = 26;         
    public static final int KEY_UNION = 27;         
    public static final int KEY_UNSIG = 28;         
    public static final int KEY_VOID = 29;         
    public static final int KEY_VOL = 30;         
    public static final int KEY_WHILE = 31;         
    public static final int KEY_READ = 32;         
    public static final int KEY_WRITE = 33;         
    public static final int ID = 34;         
    public static final int FLOAT = 35;             
    public static final int INT = 36;             
    public static final int CHAR = 37;             
    public static final int STRING = 38;             
    public static final int OP_COMA = 39;             
    public static final int OP_SEMICOL = 40;             
    public static final int OP_QUEST = 41;             
    public static final int OP_ASSIGN = 42;             
    public static final int OP_PAR_OPEN = 43;             
    public static final int OP_PAR_CLOSE = 44;             
    public static final int OP_BRACK_OPEN = 45;             
    public static final int OP_BRACK_CLOSE = 46;             
    public static final int OP_BRACE_OPEN = 47;            
    public static final int OP_BRACE_CLOSE = 48;             
    public static final int OP_COLON = 49;             
    public static final int OP_DOT = 50;             
    public static final int OP_ARROW = 51;             
    public static final int OP_INC = 52;             
    public static final int OP_DEC = 53;             
    public static final int OP_ADD = 54;             
    public static final int OP_SUB = 55;             
    public static final int OP_MULT = 56;             
    public static final int OP_DIV = 57;             
    public static final int OP_MOD = 58;             
    public static final int OP_ADD_ASS = 59;             
    public static final int OP_SUB_ASS = 60;             
    public static final int OP_MULT_ASS = 61;             
    public static final int OP_DIV_ASS = 62;             
    public static final int OP_MOD_ASS = 63;             
    public static final int OP_EQUAL = 64;             
    public static final int OP_GRE_EQ = 65;             
    public static final int OP_GREATER = 66;             
    public static final int OP_LESS = 67;             
    public static final int OP_LESS_EQ = 68;             
    public static final int OP_NOT_EQ = 69;             
    public static final int OP_OR = 70;             
    public static final int OP_AND = 71;             
    public static final int OP_NOT = 72;             
    public static final int OP_BITAND = 73;             
    public static final int OP_BITOR = 74;             
    public static final int OP_BITAND_ASS = 75;             
    public static final int OP_BITOR_ASS = 76;             
    public static final int OP_RSHIFT = 77;     
    public static final int OP_LSHIFT = 78;             
    public static final int OP_BITOREXC = 79;             
    public static final int OP_BITOREXC_ASS = 80;             
    public static final int OP_BITCOMPL = 81;             
    public static final int OP_RSHIFT_ASS = 82;             
    public static final int OP_LSHIFT_ASS = 83;             
    public static final int ERROR = 84;    
    
    public static final String[] symNames = new String[] {
        "KEY_AUTO",
        "KEY_BREAK",
        "KEY_CASE",
        "KEY_CHAR",
        "KEY_CONST",
        "KEY_CONT",
        "KEY_DEF",
        "KEY_DO",
        "KEY_DOUBLE",
        "KEY_ELSE",
        "KEY_ENUM",
        "KEY_EXT",
        "KEY_FLOAT",
        "KEY_FOR",
        "KEY_GOTO",
        "KEY_IF",
        "KEY_INT",
        "KEY_LONG",
        "KEY_REG",
        "KEY_RET",
        "KEY_SHORT",
        "KEY_SIG",
        "KEY_SIZEOF",
        "KEY_STATIC",
        "KEY_STRUCT",
        "KEY_SWITCH",
        "KEY_TYPEDEF",
        "KEY_UNION",
        "KEY_UNSIG",
        "KEY_VOID",
        "KEY_VOL",
        "KEY_WHILE",
        "KEY_READ",
        "KEY_WRITE",
        "ID",
        "FLOAT",
        "INT",
        "CHAR",
        "STRING",
        "OP_COMA",
        "OP_SEMICOL",
        "OP_QUEST",
        "OP_ASSIGN",
        "OP_PAR_OPEN",
        "OP_PAR_CLOSE",
        "OP_BRACK_OPEN",
        "OP_BRACK_CLOSE",
        "OP_BRACE_OPEN",
        "OP_BRACE_CLOSE",
        "OP_COLON",
        "OP_DOT",
        "OP_ARROW",
        "OP_INC",
        "OP_DEC",
        "OP_ADD",
        "OP_SUB",
        "OP_MULT",
        "OP_DIV",
        "OP_MOD",
        "OP_ADD_ASS",
        "OP_SUB_ASS",
        "OP_MULT_ASS",
        "OP_DIV_ASS",
        "OP_MOD_ASS",
        "OP_EQUAL",
        "OP_GRE_EQ",
        "OP_GREATER",
        "OP_LESS",
        "OP_LESS_EQ",
        "OP_NOT_EQ",
        "OP_OR",
        "OP_AND",
        "OP_NOT",
        "OP_BITAND",
        "OP_BITOR",
        "OP_BITAND_ASS",
        "OP_BITOR_ASS",
        "OP_RSHIFT",
        "OP_LSHIFT",
        "OP_BITOREXC",
        "OP_BITOREXC_ASS",
        "OP_BITCOMPL",
        "OP_RSHIFT_ASS",
        "OP_LSHIFT_ASS",
        "ERROR"
    };
}