package scanner;
import static scanner.TokenType.*;
import static scanner.Token.*;

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

%{
    private Token token(TokenType type, Object value){
        return new Token(type, value, yyline);
    }
%}

%%

/* KEYWORDS */
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
"while" {return token(KEYWORD, yytext());}

/* SPACES */
{Space}+ {/*Ignore*/}

/* COMMENTS */
"//"[^\r\n]* |
"/*"~"*/" {/*Ignore*/}

/* IDENTIFIERS */
{Letter}({Letter}|{Digit}|"_")* {return token(IDENTIFIER, yytext());}

/* INTEGERS */
0[0-7]+ |                                       
0[xX][{Digit}A-Fa-f]+ |                         
[1-9]{Digit}* |                           
0 {return token(INTEGER, yytext());}

/* FLOATS */
{Digit}+ "." {Digit}+ {Exponent}? |
"." {Digit}+ {Exponent}? |
{Digit}+ "." {Exponent}? |
{Digit}+ {Exponent} {return token(FLOAT, yytext());}

/* CHARS */
\'{CChar}\' {return token(CHAR, yytext());}
\"{SChar}*\" {return token(STRING, yytext());}

/* OPERATORS */
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
"->" {return token(OP_STRUCTURE, yytext());}
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
"%=" {return token(OP_ARITHMETIC, yytext());}
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
">>=" {return token(OP_LOGICAL, yytext());}

/* ERRORS */
. {return token(ERROR, yytext());}