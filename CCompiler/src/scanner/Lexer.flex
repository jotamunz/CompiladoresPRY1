package scanner;
import static scanner.TokenType.*;
import static scanner.Token.*;

%%

%class Lexer
%function nextToken
%type Token
L = [a-zA-Z]
D = [0-9]
T = \r|\n|\r\n
S = {T}|[ \t\f]

%{
    private Token token(TokenType type, Object value){
        return new Token(type, value);
    }
%}

%%

/* KEYWORDS */
"int" |
"if" |
"else" {return token(KEYWORD, yytext());}

/* SPACES */
{S}+ {/*Ignore*/}

/* COMMENTS */
"//"[^\r\n]* |
"/*"~"*/" {/*Ignore*/}

/* OPERATORS */
"=" |
"+" |
"-" |
"*" |
"/" {return token(OPERATOR, yytext());}

/* IDENTIFIERS */
{L}({L}|{D}|"_")* {return token(IDENTIFIER, yytext());}

/* INTEGERS */
0[0-7]+ |
0[xX][{D}A-Fa-f]+ |
[1-9]{D}* |
0 {return token(INTEGER, yytext());}

/* ERRORS */
 . {return token(ERROR, yytext());}
