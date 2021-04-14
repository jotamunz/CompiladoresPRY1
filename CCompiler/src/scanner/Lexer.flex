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
{L}({L}|{D}|"_")* {return token(IDENTIFIER, yytext());}
("+"|"-")?{D}+ {return token(INTEGER, yytext());}
"=" |
"+" |
"-" |
"*" |
"/" {return token(OPERATOR, yytext());}
 . {return token(ERROR, yytext());}
