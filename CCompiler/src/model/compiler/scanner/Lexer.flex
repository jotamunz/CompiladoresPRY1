package model.compiler.scanner;
import static model.compiler.scanner.TokenNames.*;
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
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%%

/* KEYWORDS */
"auto" {return symbol(KEY_AUTO, yytext());}
"break" {return symbol(KEY_BREAK, yytext());}
"case" {return symbol(KEY_CASE, yytext());}
"char" {return symbol(KEY_CHAR, yytext());}
"const" {return symbol(KEY_CONST, yytext());}
"continue" {return symbol(KEY_CONT, yytext());}
"default" {return symbol(KEY_DEF, yytext());}
"do" {return symbol(KEY_DO, yytext());}
"double" {return symbol(KEY_DOUBLE, yytext());}
"else" {return symbol(KEY_ELSE, yytext());}
"enum" {return symbol(KEY_ENUM, yytext());}
"extern" {return symbol(KEY_EXT, yytext());}
"float" {return symbol(KEY_FLOAT, yytext());}
"for" {return symbol(KEY_FOR, yytext());}
"goto" {return symbol(KEY_GOTO, yytext());}
"if" {return symbol(KEY_IF, yytext());}
"int" {return symbol(KEY_INT, yytext());}
"long" {return symbol(KEY_LONG, yytext());}
"register" {return symbol(KEY_REG, yytext());}
"return" {return symbol(KEY_RET, yytext());}
"short" {return symbol(KEY_SHORT, yytext());}
"signed" {return symbol(KEY_SIG, yytext());}
"sizeof" {return symbol(KEY_SIZEOF, yytext());}
"static" {return symbol(KEY_STATIC, yytext());}
"struct" {return symbol(KEY_STRUCT, yytext());}
"switch" {return symbol(KEY_SWITCH, yytext());}
"typedef" {return symbol(KEY_TYPEDEF, yytext());}
"union" {return symbol(KEY_UNION, yytext());}
"unsigned" {return symbol(KEY_UNSIG, yytext());}
"void" {return symbol(KEY_VOID, yytext());}
"volatile" {return symbol(KEY_VOL, yytext());}
"while" {return symbol(KEY_WHILE, yytext());}
"read" {return symbol(KEY_READ, yytext());}
"write" {return symbol(KEY_WRITE, yytext());}

/* SPACES */
{Space}+ {/*Ignore*/}

/* COMMENTS */
"//"[^\r\n]* |
"/*"~"*/" {/*Ignore*/}

/* IDENTIFIERS */
{Letter}({Letter}|{Digit}|"_")* {return symbol(ID, yytext());}

/* FLOATS */
{Digit}+ "." {Digit}+ {Exponent}? |
"." {Digit}+ {Exponent}? |
{Digit}+ "." {Exponent}? |
{Digit}+ {Exponent} {return symbol(FLOAT, yytext());}

/* INTEGERS */
0[xX][{Digit}A-Fa-f]+ {return symbol(INT, yytext());}

/* ERRORS */
{Digit}+({Letter}|"_")({Letter}|{Digit}|"_")* |
("_")+({Letter}|{Digit}|"_")* {return symbol(ERROR, yytext());}

/* INTEGERS */
0[0-7]+ |                                                               
[1-9]{Digit}* |                           
0 {return symbol(INT, yytext());}

/* CHARS */
\'{CChar}\' {return symbol(CHAR, yytext());}
\"{SChar}*\" {return symbol(STRING, yytext());}

/* OPERATORS */
"," {return symbol(OP_COMA, yytext());}
";" {return symbol(OP_SEMICOL, yytext());}
"?" {return symbol(OP_QUEST, yytext());}
"=" {return symbol(OP_ASSIGN, yytext());}
"(" {return symbol(OP_PAR_OPEN, yytext());}
")" {return symbol(OP_PAR_CLOSE, yytext());}
"[" {return symbol(OP_BRACK_OPEN, yytext());}
"]" {return symbol(OP_BRACK_CLOSE, yytext());}
"{" {return symbol(OP_BRACE_OPEN, yytext());}
"}" {return symbol(OP_BRACE_CLOSE, yytext());}
":" {return symbol(OP_COLON, yytext());}
"." {return symbol(OP_DOT, yytext());}
"->" {return symbol(OP_ARROW, yytext());}
"++" {return symbol(OP_INC, yytext());}
"--" {return symbol(OP_DEC, yytext());}
"+" {return symbol(OP_ADD, yytext());}
"-" {return symbol(OP_SUB, yytext());}
"*" {return symbol(OP_MULT, yytext());}
"/" {return symbol(OP_DIV, yytext());}
"%" {return symbol(OP_MOD, yytext());}
"+=" {return symbol(OP_ADD_ASS, yytext());}
"-=" {return symbol(OP_SUB_ASS, yytext());}
"*=" {return symbol(OP_MULT_ASS, yytext());}
"/=" {return symbol(OP_DIV_ASS, yytext());}
"%=" {return symbol(OP_MOD_ASS, yytext());}
"==" {return symbol(OP_EQUAL, yytext());}
">=" {return symbol(OP_GRE_EQ, yytext());}
">" {return symbol(OP_GREATER, yytext());}
"<" {return symbol(OP_LESS, yytext());}
"<=" {return symbol(OP_LESS_EQ, yytext());}
"!=" {return symbol(OP_NOT_EQ, yytext());}
"||" {return symbol(OP_OR, yytext());}
"&&" {return symbol(OP_AND, yytext());}
"!" {return symbol(OP_NOT, yytext());}
"&" {return symbol(OP_BITAND, yytext());}
"|" {return symbol(OP_BITOR, yytext());}
"&=" {return symbol(OP_BITAND_ASS, yytext());}
"|=" {return symbol(OP_BITOR_ASS, yytext());}
">>" {return symbol(OP_RSHIFT, yytext());}
"<<" {return symbol(OP_LSHIFT, yytext());}
"^" {return symbol(OP_BITOREXC, yytext());}
"^=" {return symbol(OP_BITOREXC_ASS, yytext());}
"~" {return symbol(OP_BITCOMPL, yytext());}
"<<=" {return symbol(OP_RSHIFT_ASS, yytext());}
">>=" {return symbol(OP_LSHIFT_ASS, yytext());}

/* ERRORS */
. {return symbol(ERROR, yytext());}