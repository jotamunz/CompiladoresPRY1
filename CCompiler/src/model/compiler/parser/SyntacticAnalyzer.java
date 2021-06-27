
package model.compiler.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import model.compiler.scanner.Lexer;
import model.compiler.scanner.Token;
import model.compiler.translator.SemanticError;
import model.compiler.translator.Symbols.IdentifierData;
import model.compiler.translator.Translator;

public class SyntacticAnalyzer {
    
    private ArrayList<Token> errorTokens = new ArrayList<>();
    private Translator translator = new Translator();
    
    public void parseAndTranslate(String path){
        try {
            parser par = new parser(new Lexer(new FileReader(path)));
            par.parse();
            translator = par.tl;
            errorTokens = par.errors;
            System.out.println("Finished Parse");
            this.translator.endNasmCode();
            writeFile((path.substring(0,path.lastIndexOf('.')))+".asm",this.translator.getNasmConverter().getNasmCode());
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (Exception ex) {
            System.out.println("Exception");
        }
    }
    
    public void printSyntaxErrors(){
        for (Token token : errorTokens) {
            System.err.println("Syntax error at line: " + token.getLineNum() + " at column: " + token.getColNum() + " on: " + token.getValue());
        }
    }
    
    public ArrayList<Token> getSyntaxErrors() {
        return errorTokens;
    }
    
    public void printSemanticErrors(){
        for (SemanticError error : translator.getSemanticErrors()){
            System.err.println(error.toString());
        }
    }
    
    public void printNasmCode(){
        this.translator.printNasmCode();
    }
    
    public ArrayList<SemanticError> getSemanticErrors() {
        return translator.getSemanticErrors();
    }
    
    public void printSymbolTable(){
        System.out.println("SYMBOL TABLE");
        for (String key: translator.getSymbolTable().keySet()){  
                System.out.println(translator.getSymbolTable().get(key).toString());
        } 
    }
    
    public HashMap<String, IdentifierData> getSymbolTable() {
        return translator.getSymbolTable();
    }
    
    
    public void writeFile(String absolutePath, String file){
        try(FileWriter fileWriter = new FileWriter(absolutePath)) {
            String fileContent = file;
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            // Cxception handling
        }
    }
}
 