
package model.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import model.compiler.parser.SyntacticAnalyzer;
import model.compiler.scanner.Token;
import model.compiler.scanner.LexicalAnalyzer;
import model.compiler.translator.SemanticError;
import model.compiler.translator.Symbols.IdentifierData;

public class Model {
    private String fileName;
    private String filePath;
    private LexicalAnalyzer scanner;
    private SyntacticAnalyzer parser;

    public Model() {
        this.fileName = "";
        this.filePath = "";
        this.scanner = new LexicalAnalyzer();
        this.parser = new SyntacticAnalyzer();
    }
    
    public ArrayList<Token> scanFile() {
        scanner.scan(filePath);
        //scanner.printErrorTokens();
        return scanner.getTokens();
    }
    
    public void parseAndTranslateFile() {
        parser.parseAndTranslate(filePath);
        //parser.printSyntaxErrors();
//        parser.printSemanticErrors();
//        parser.printSymbolTable();
       // parser.printNasmCode();
   
    }
    
    public String getNasmCode(){
        return parser.getNasmCode();
    }
    
    public ArrayList<Token> getSyntaxErrors(){
         return parser.getSyntaxErrors();
    }
    
    public ArrayList<SemanticError> getSemanticErrors(){
         return parser.getSemanticErrors();
    }
        
    public HashMap<String, IdentifierData> getSymbolTable(){
         return parser.getSymbolTable();
    }
    
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
}
