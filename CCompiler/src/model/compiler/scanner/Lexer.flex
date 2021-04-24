package model.compiler.scanner;
import static model.compiler.scanner.TokenType.*;
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
EChar = \\[ntbrf\\\'\"]
CChar = [^\'\\\n\r] | {EChar}
SChar = [^\"\\\n\r] | {EChar}
Exponent = [eE][+-]?{Digit}+

/* KEYWORDS */
Keyword = 
"auto" |
"break" |
"case" |
"char" |
"const" |
"continue" |
"default" |
"do" |
"double" |
"else" |
"enum" |
"extern" |
"float" |
"for" |
"goto" |
"if" |
"int" |
"long" |
"register" |
"return" |
"short" |
"signed" |
"sizeof" |
"static" |
"struct" |
"switch" |
"typedef" |
"union" |
"unsigned" |
"void" |
"volatile" |
"while"

/* OPERATORS */
Op_Structure = 
"," |
";" |
"?" |
"=" |
"(" |
")" |
"[" |
"]" |
"{" |
"}" |
":" |
"." |
"->"
Op_Arithmetic = 
"++" |
"--" |
"+" | 
"-" | 
"*" |
"/" |
"%" |
"+=" |
"-=" |
"*=" |
"/=" |
"%="
Op_Logical = 
"==" |
">=" |
">" |
"<" |
"<=" |
"!=" |
"||" |
"&&" |
"!" |
">>" | 
"<<" |
"&" | 
"^" |
"|" |
"~" |
"&=" |
"^=" |
"|=" |
"<<=" |
">>="
Operator = {Op_Structure}|{Op_Arithmetic}|{Op_Logical}

%{
    private Token token(TokenType type, Object value){
        return new Token(type, value, yyline);
    }
%}

%%

/* KEYWORDS */
{Keyword} {return token(KEYWORD, yytext());}

/* SPACES */
{Space}+ {/*Ignore*/}

/* COMMENTS */
"//"[^\r\n]* |
"/*"~"*/" {/*Ignore*/}

/* IDENTIFIERS */
({Letter}|"_")({Letter}|{Digit}|"_")* {return token(IDENTIFIER, yytext());}

/* FLOATS */
{Digit}+ "." {Digit}+ {Exponent}? |
"." {Digit}+ {Exponent}? |
{Digit}+ "." {Exponent}? |
{Digit}+ {Exponent} {return token(FLOAT, yytext());}

/* INTEGERS */
0[xX][{Digit}A-Fa-f]+ {return token(INTEGER, yytext());}

/* ERROR (Number + Letter) */
{Digit}+({Letter}|"_")({Letter}|{Digit}|"_")* {return token(ERROR, yytext());}

/* INTEGERS */
0[0-7]+ |                                                               
[1-9]{Digit}* |                           
0 {return token(INTEGER, yytext());}

/* CHARS */
\'{CChar}\' {return token(CHAR, yytext());}
\"{SChar}*\" {return token(STRING, yytext());}

/* OPERATORS */
{Op_Structure} {return token(OP_STRUCTURE, yytext());}
{Op_Arithmetic} {return token(OP_ARITHMETIC, yytext());}
{Op_Logical} {return token(OP_LOGICAL, yytext());}

/* ERRORS */
. {return token(ERROR, yytext());}