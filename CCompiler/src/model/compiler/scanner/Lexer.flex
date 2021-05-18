package model.compiler.scanner;
import static model.compiler.scanner.Sym.*;
import static model.compiler.scanner.Token.*;

%%

%class Lexer
%line
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
"auto" {return token(KEYWORD, yytext());}
"break" {return token(KEYWORD, yytext());}
"case" {return token(KEYWORD, yytext());}
"char" {return token(KEYWORD, yytext());}
"const" {return token(KEYWORD, yytext());}
"continue" {return token(KEYWORD, yytext());}
"default" {return token(KEYWORD, yytext());}
"do" {return token(KEYWORD, yytext());}
"double" {return token(KEYWORD, yytext());}
"else" {return token(KEYWORD, yytext());}
"enum" {return token(KEYWORD, yytext());}
"extern" {return token(KEYWORD, yytext());}
"float" {return token(KEYWORD, yytext());}
"for" {return token(KEYWORD, yytext());}
"goto" {return token(KEYWORD, yytext());}
"if" {return token(KEYWORD, yytext());}
"int" {return token(KEYWORD, yytext());}
"long" {return token(KEYWORD, yytext());}
"register" {return token(KEYWORD, yytext());}
"return" {return token(KEYWORD, yytext());}
"short" {return token(KEYWORD, yytext());}
"signed" {return token(KEYWORD, yytext());}
"sizeof" {return token(KEYWORD, yytext());}
"static" {return token(KEYWORD, yytext());}
"struct" {return token(KEYWORD, yytext());}
"switch" {return token(KEYWORD, yytext());}
"typedef" {return token(KEYWORD, yytext());}
"union" {return token(KEYWORD, yytext());}
"unsigned" {return token(KEYWORD, yytext());}
"void" {return token(KEYWORD, yytext());}
"volatile" {return token(KEYWORD, yytext());}
"while" {return token(KEYWORD, yytext());}

/* SPACES */
{Space}+ {/*Ignore*/}

/* COMMENTS */
"//"[^\r\n]* |
"/*"~"*/" {/*Ignore*/}

/* IDENTIFIERS */
{Letter}({Letter}|{Digit}|"_")* {return token(IDENTIFIER, yytext());}

/* FLOATS */
{Digit}+ "." {Digit}+ {Exponent}? |
"." {Digit}+ {Exponent}? |
{Digit}+ "." {Exponent}? |
{Digit}+ {Exponent} {return token(FLOAT, yytext());}

/* INTEGERS */
0[xX][{Digit}A-Fa-f]+ {return token(INTEGER, yytext());}

/* ERRORS */
{Digit}+({Letter}|"_")({Letter}|{Digit}|"_")* |
("_")+({Letter}|{Digit}|"_")* {return token(ERROR, yytext());}

/* INTEGERS */
0[0-7]+ |                                                               
[1-9]{Digit}* |                           
0 {return token(INTEGER, yytext());}

/* CHARS */
\'{CChar}\' {return token(CHAR, yytext());}
\"{SChar}*\" {return token(STRING, yytext());}

/* OPERATORS */
"," {return token(OP_STRUCTURE, yytext());}
";" {return token(OP_STRUCTURE, yytext());}
"?" {return token(OP_STRUCTURE, yytext());}
"=" {return token(OP_STRUCTURE, yytext());}
"(" {return token(OP_STRUCTURE, yytext());}
")" {return token(OP_STRUCTURE, yytext());}
"[" {return token(OP_STRUCTURE, yytext());}
"]" {return token(OP_STRUCTURE, yytext());}
"{" {return token(OP_STRUCTURE, yytext());}
"}" {return token(OP_STRUCTURE, yytext());}
":" {return token(OP_STRUCTURE, yytext());}
"." {return token(OP_STRUCTURE, yytext());}
"->" {return token(OP_STRUCTURE, yytext());}

"++" {return token(OP_ARITHMETIC, yytext());}
"--" {return token(OP_ARITHMETIC, yytext());}
"+" {return token(OP_ARITHMETIC, yytext());}
"-" {return token(OP_ARITHMETIC, yytext());}
"*" {return token(OP_ARITHMETIC, yytext());}
"/" {return token(OP_ARITHMETIC, yytext());}
"%" {return token(OP_ARITHMETIC, yytext());}
"+=" {return token(OP_ARITHMETIC, yytext());}
"-=" {return token(OP_ARITHMETIC, yytext());}
"*=" {return token(OP_ARITHMETIC, yytext());}
"/=" {return token(OP_ARITHMETIC, yytext());}
"%=" {return token(OP_ARITHMETIC, yytext());}

"==" {return token(OP_LOGICAL, yytext());}
">=" {return token(OP_LOGICAL, yytext());}
">" {return token(OP_LOGICAL, yytext());}
"<" {return token(OP_LOGICAL, yytext());}
"<=" {return token(OP_LOGICAL, yytext());}
"!=" {return token(OP_LOGICAL, yytext());}
"||" {return token(OP_LOGICAL, yytext());}
"&&" {return token(OP_LOGICAL, yytext());}
"!" {return token(OP_LOGICAL, yytext());}
">>" {return token(OP_LOGICAL, yytext());}
"<<" {return token(OP_LOGICAL, yytext());}
"&" {return token(OP_LOGICAL, yytext());}
"^" {return token(OP_LOGICAL, yytext());}
"|" {return token(OP_LOGICAL, yytext());}
"~" {return token(OP_LOGICAL, yytext());}
"&=" {return token(OP_LOGICAL, yytext());}
"^=" {return token(OP_LOGICAL, yytext());}
"|=" {return token(OP_LOGICAL, yytext());}
"<<=" {return token(OP_LOGICAL, yytext());}
">>=" {return token(OP_LOGICAL, yytext());}

/* ERRORS */
. {return token(ERROR, yytext());}