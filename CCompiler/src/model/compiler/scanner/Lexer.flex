package model.compiler.scanner;
import model.compiler.parser.sym;
import java_cup.runtime.*;

%%

%cup
%public
%class Lexer
%type java_cup.runtime.Symbol
%line
%column
Letter = [a-zA-Z]
Digit = [0-9]
EOL = \r|\n|\r\n
Space = {EOL}|[ \t\f]
OctalEChar = \\[0-7] | \\[0-7][0-7] | \\[0-3][0-7][0-7]
EChar = \\[ntbrf\\\'\"] | {OctalEChar}
CChar = [^\'\\\n\r] | {EChar}
SChar = [^\"\\\n\r] | {EChar}
Exponent = [eE][+-]?{Digit}+

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yycolumn, yyline, value);
    }
%}

%%

/* KEYWORDS */
"auto" {return symbol(sym.KEY_AUTO, yytext());}
"break" {return symbol(sym.KEY_BREAK, yytext());}
"case" {return symbol(sym.KEY_CASE, yytext());}
"char" {return symbol(sym.KEY_CHAR, yytext());}
"const" {return symbol(sym.KEY_CONST, yytext());}
"continue" {return symbol(sym.KEY_CONT, yytext());}
"default" {return symbol(sym.KEY_DEF, yytext());}
"do" {return symbol(sym.KEY_DO, yytext());}
"double" {return symbol(sym.KEY_DOUBLE, yytext());}
"else" {return symbol(sym.KEY_ELSE, yytext());}
"enum" {return symbol(sym.KEY_ENUM, yytext());}
"extern" {return symbol(sym.KEY_EXT, yytext());}
"float" {return symbol(sym.KEY_FLOAT, yytext());}
"for" {return symbol(sym.KEY_FOR, yytext());}
"goto" {return symbol(sym.KEY_GOTO, yytext());}
"if" {return symbol(sym.KEY_IF, yytext());}
"int" {return symbol(sym.KEY_INT, yytext());}
"long" {return symbol(sym.KEY_LONG, yytext());}
"register" {return symbol(sym.KEY_REG, yytext());}
"return" {return symbol(sym.KEY_RET, yytext());}
"short" {return symbol(sym.KEY_SHORT, yytext());}
"signed" {return symbol(sym.KEY_SIG, yytext());}
"sizeof" {return symbol(sym.KEY_SIZEOF, yytext());}
"static" {return symbol(sym.KEY_STATIC, yytext());}
"struct" {return symbol(sym.KEY_STRUCT, yytext());}
"switch" {return symbol(sym.KEY_SWITCH, yytext());}
"typedef" {return symbol(sym.KEY_TYPEDEF, yytext());}
"union" {return symbol(sym.KEY_UNION, yytext());}
"unsigned" {return symbol(sym.KEY_UNSIG, yytext());}
"void" {return symbol(sym.KEY_VOID, yytext());}
"volatile" {return symbol(sym.KEY_VOL, yytext());}
"while" {return symbol(sym.KEY_WHILE, yytext());}
"read" {return symbol(sym.KEY_READ, yytext());}
"write" {return symbol(sym.KEY_WRITE, yytext());}

/* SPACES */
{Space}+ {/*Ignore*/}

/* COMMENTS */
"//"[^\r\n]* |
"/*"~"*/" {/*Ignore*/}

/* IDENTIFIERS */
{Letter}({Letter}|{Digit}|"_")* {return symbol(sym.ID, yytext());}

/* FLOATS */
{Digit}+ "." {Digit}+ {Exponent}? |
"." {Digit}+ {Exponent}? |
{Digit}+ "." {Exponent}? |
{Digit}+ {Exponent} {return symbol(sym.FLOAT, yytext());}

/* INTEGERS */
0[xX][{Digit}A-Fa-f]+ {return symbol(sym.INT, yytext());}

/* ERRORS */
{Digit}+({Letter}|"_")({Letter}|{Digit}|"_")* |
("_")+({Letter}|{Digit}|"_")* {return symbol(sym.LEX_ERROR, yytext());}

/* INTEGERS */
0[0-7]+ |                                                               
[1-9]{Digit}* |                           
0 {return symbol(sym.INT, yytext());}

/* CHARS */
\'{CChar}\' {return symbol(sym.CHAR, yytext());}
\"{SChar}*\" {return symbol(sym.STRING, yytext());}

/* OPERATORS */
"," {return symbol(sym.OP_COMA, yytext());}
";" {return symbol(sym.OP_SEMICOL, yytext());}
"?" {return symbol(sym.OP_QUEST, yytext());}
"=" {return symbol(sym.OP_ASSIGN, yytext());}
"(" {return symbol(sym.OP_PAR_OPEN, yytext());}
")" {return symbol(sym.OP_PAR_CLOSE, yytext());}
"[" {return symbol(sym.OP_BRACK_OPEN, yytext());}
"]" {return symbol(sym.OP_BRACK_CLOSE, yytext());}
"{" {return symbol(sym.OP_BRACE_OPEN, yytext());}
"}" {return symbol(sym.OP_BRACE_CLOSE, yytext());}
":" {return symbol(sym.OP_COLON, yytext());}
"." {return symbol(sym.OP_DOT, yytext());}
"->" {return symbol(sym.OP_ARROW, yytext());}
"++" {return symbol(sym.OP_INC, yytext());}
"--" {return symbol(sym.OP_DEC, yytext());}
"+" {return symbol(sym.OP_ADD, yytext());}
"-" {return symbol(sym.OP_SUB, yytext());}
"*" {return symbol(sym.OP_MULT, yytext());}
"/" {return symbol(sym.OP_DIV, yytext());}
"%" {return symbol(sym.OP_MOD, yytext());}
"+=" {return symbol(sym.OP_ADD_ASS, yytext());}
"-=" {return symbol(sym.OP_SUB_ASS, yytext());}
"*=" {return symbol(sym.OP_MULT_ASS, yytext());}
"/=" {return symbol(sym.OP_DIV_ASS, yytext());}
"%=" {return symbol(sym.OP_MOD_ASS, yytext());}
"==" {return symbol(sym.OP_EQUAL, yytext());}
">=" {return symbol(sym.OP_GRE_EQ, yytext());}
">" {return symbol(sym.OP_GREATER, yytext());}
"<" {return symbol(sym.OP_LESS, yytext());}
"<=" {return symbol(sym.OP_LESS_EQ, yytext());}
"!=" {return symbol(sym.OP_NOT_EQ, yytext());}
"||" {return symbol(sym.OP_OR, yytext());}
"&&" {return symbol(sym.OP_AND, yytext());}
"!" {return symbol(sym.OP_NOT, yytext());}
"&" {return symbol(sym.OP_BITAND, yytext());}
"|" {return symbol(sym.OP_BITOR, yytext());}
"&=" {return symbol(sym.OP_BITAND_ASS, yytext());}
"|=" {return symbol(sym.OP_BITOR_ASS, yytext());}
">>" {return symbol(sym.OP_RSHIFT, yytext());}
"<<" {return symbol(sym.OP_LSHIFT, yytext());}
"^" {return symbol(sym.OP_BITOREXC, yytext());}
"^=" {return symbol(sym.OP_BITOREXC_ASS, yytext());}
"~" {return symbol(sym.OP_BITCOMPL, yytext());}
"<<=" {return symbol(sym.OP_RSHIFT_ASS, yytext());}
">>=" {return symbol(sym.OP_LSHIFT_ASS, yytext());}

/* ERRORS */
. {return symbol(sym.LEX_ERROR, yytext());}