package scanner;
import static scanner.TokenType.*;
import static scanner.Token.*;

%%

%class Lexer
%function nextToken
%type Token
L = [a-zA-Z]
D = [0-9]
S = [ ,\t,\r,\n]

%{
    private Token token(TokenType type, Object value){
        return new Token(type, value);
    }
%}

%%

"int" |
"if" |
"else" {return token(KEYWORD, yytext());}
{S}+ {/*Ignore*/}
"//".* {/*Ignore*/}
"=" |
"+" |
"-" |
"*" |
"/" {return token(OPERATOR, yytext());}
{L}({L}|{D}|"_")* {return token(IDENTIFIER, yytext());}
0[0-7]+ |
0[xX][{D}A-Fa-f]+ |
[1-9]{D}* |
0 {return token(INTEGER, yytext());}
 . {return token(ERROR, yytext());}
