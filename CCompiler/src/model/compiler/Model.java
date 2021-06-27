
package model.compiler;

import java.util.ArrayList;
import model.compiler.parser.SyntacticAnalyzer;
import model.compiler.scanner.Token;
import model.compiler.scanner.LexicalAnalyzer;

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
    
    public ArrayList<Token> parseAndTranslateFile() {
        parser.parseAndTranslate(filePath);
        //parser.printSyntaxErrors();
        parser.printSemanticErrors();
        parser.printSymbolTable();
        parser.printNasmCode();
        return parser.getSyntaxErrors();
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
