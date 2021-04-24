
package model.compiler;

import model.compiler.scanner.Token;
import model.compiler.scanner.Scanner;
import java.util.HashMap;

public class Model {
    private String fileName;
    private String filePath;
    private Scanner scanner;

    public Model() {
        this.fileName = "";
        this.filePath = "";
        this.scanner = new Scanner();
    }
    
    public HashMap<String, Token> scanFile () {
        scanner.scan(filePath);
        scanner.printUniqueTokens();
        return scanner.getUniqueTokens();
    }

    //Gets and Sets
    
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
