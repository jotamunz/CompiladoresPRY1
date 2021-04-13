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
    private Token token(TokenType type){
        return new Token(type);
    }

    private Token token(TokenType type, Object value){
        return new Token(type, value);
    }
%}

%%

"int" |
"if" |
"else" {return token(Keyword, yytext());}
{S}+ {/*Ignore*/}
"//".* {/*Ignore*/}
"=" |
"+" |
"-" |
"*" |
"/" {return token(Operator, yytext());}
{L}({L}|{D}|"_")* {return token(Identifier, yytext());}
("+"|"-")?{D}+ {return token(Integer, yytext());}
 . {return token(Error, yytext());}
