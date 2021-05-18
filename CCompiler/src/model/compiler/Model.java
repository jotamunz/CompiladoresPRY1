
package model.compiler;

import java.util.ArrayList;
import model.compiler.scanner.Token;
import model.compiler.scanner.Scanner;

public class Model {
    private String fileName;
    private String filePath;
    private Scanner scanner;

    public Model() {
        this.fileName = "";
        this.filePath = "";
        this.scanner = new Scanner();
    }
    
    public ArrayList<Token> scanFile () {
        scanner.scan(filePath);
        scanner.printTokens();
        return scanner.getTokens();
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
