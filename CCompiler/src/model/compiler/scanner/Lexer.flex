package model.compiler.scanner;
import static model.compiler.scanner.Sym.*;
import static model.compiler.scanner.Token.*;

%%

%class Lexer
%line
%column
%function nextToken
%type Token
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
    private Token token(Sym type, Object value){
        return new Token(type, value, yyline);
    }
%}

%%

/* KEYWORDS */
"auto" {return token(KEY_AUTO, yytext());}
"break" {return token(KEY_BREAK, yytext());}
"case" {return token(KEY_CASE, yytext());}
"char" {return token(KEY_CHAR, yytext());}
"const" {return token(KEY_CONST, yytext());}
"continue" {return token(KEY_CONT, yytext());}
"default" {return token(KEY_DEF, yytext());}
"do" {return token(KEY_DO, yytext());}
"double" {return token(KEY_DOUBLE, yytext());}
"else" {return token(KEY_ELSE, yytext());}
"enum" {return token(KEY_ENUM, yytext());}
"extern" {return token(KEY_EXT, yytext());}
"float" {return token(KEY_FLOAT, yytext());}
"for" {return token(KEY_FOR, yytext());}
"goto" {return token(KEY_GOTO, yytext());}
"if" {return token(KEY_IF, yytext());}
"int" {return token(KEY_INT, yytext());}
"long" {return token(KEY_LONG, yytext());}
"register" {return token(KEY_REG, yytext());}
"return" {return token(KEY_RET, yytext());}
"short" {return token(KEY_SHORT, yytext());}
"signed" {return token(KEY_SIG, yytext());}
"sizeof" {return token(KEY_SIZEOF, yytext());}
"static" {return token(KEY_STATIC, yytext());}
"struct" {return token(KEY_STRUCT, yytext());}
"switch" {return token(KEY_SWITCH, yytext());}
"typedef" {return token(KEY_TYPEDEF, yytext());}
"union" {return token(KEY_UNION, yytext());}
"unsigned" {return token(KEY_UNSIG, yytext());}
"void" {return token(KEY_VOID, yytext());}
"volatile" {return token(KEY_VOL, yytext());}
"while" {return token(KEY_WHILE, yytext());}

/* SPACES */
{Space}+ {/*Ignore*/}

/* COMMENTS */
"//"[^\r\n]* |
"/*"~"*/" {/*Ignore*/}

/* IDENTIFIERS */
{Letter}({Letter}|{Digit}|"_")* {return token(ID, yytext());}

/* FLOATS */
{Digit}+ "." {Digit}+ {Exponent}? |
"." {Digit}+ {Exponent}? |
{Digit}+ "." {Exponent}? |
{Digit}+ {Exponent} {return token(FLOAT, yytext());}

/* INTEGERS */
0[xX][{Digit}A-Fa-f]+ {return token(INT, yytext());}

/* ERRORS */
{Digit}+({Letter}|"_")({Letter}|{Digit}|"_")* |
("_")+({Letter}|{Digit}|"_")* {return token(ERROR, yytext());}

/* INTEGERS */
0[0-7]+ |                                                               
[1-9]{Digit}* |                           
0 {return token(INT, yytext());}

/* CHARS */
\'{CChar}\' {return token(CHAR, yytext());}
\"{SChar}*\" {return token(STRING, yytext());}

/* OPERATORS */
"," {return token(OP_COMA, yytext());}
";" {return token(OP_SEMICOL, yytext());}
"?" {return token(OP_QUEST, yytext());}
"=" {return token(OP_ASSIGN, yytext());}
"(" {return token(OP_PAR_OPEN, yytext());}
")" {return token(OP_PAR_CLOSE, yytext());}
"[" {return token(OP_BRACK_OPEN, yytext());}
"]" {return token(OP_BRACK_CLOSE, yytext());}
"{" {return token(OP_BRACE_OPEN, yytext());}
"}" {return token(OP_BRACE_CLOSE, yytext());}
":" {return token(OP_COLON, yytext());}
"." {return token(OP_DOT, yytext());}
"->" {return token(OP_ARROW, yytext());}
"++" {return token(OP_INC, yytext());}
"--" {return token(OP_DEC, yytext());}
"+" {return token(OP_ADD, yytext());}
"-" {return token(OP_SUB, yytext());}
"*" {return token(OP_MULT, yytext());}
"/" {return token(OP_DIV, yytext());}
"%" {return token(OP_MOD, yytext());}
"+=" {return token(OP_ADD_ASS, yytext());}
"-=" {return token(OP_SUB_ASS, yytext());}
"*=" {return token(OP_MULT_ASS, yytext());}
"/=" {return token(OP_DIV_ASS, yytext());}
"%=" {return token(OP_MOD_ASS, yytext());}
"==" {return token(OP_EQUAL, yytext());}
">=" {return token(OP_GRE_EQ, yytext());}
">" {return token(OP_GREATER, yytext());}
"<" {return token(OP_LESS, yytext());}
"<=" {return token(OP_LESS_EQ, yytext());}
"!=" {return token(OP_NOT_EQ, yytext());}
"||" {return token(OP_OR, yytext());}
"&&" {return token(OP_AND, yytext());}
"!" {return token(OP_NOT, yytext());}
"&" {return token(OP_BITAND, yytext());}
"|" {return token(OP_BITOR, yytext());}
"&=" {return token(OP_BITAND_ASS, yytext());}
"|=" {return token(OP_BITOR_ASS, yytext());}
">>" {return token(OP_RSHIFT, yytext());}
"<<" {return token(OP_LSHIFT, yytext());}
"^" {return token(OP_BITOREXC, yytext());}
"^=" {return token(OP_BITOREXC_ASS, yytext());}
"~" {return token(OP_BITCOMPL, yytext());}
"<<=" {return token(OP_RSHIFT_ASS, yytext());}
">>=" {return token(OP_LSHIFT_ASS, yytext());}

/* ERRORS */
. {return token(ERROR, yytext());}