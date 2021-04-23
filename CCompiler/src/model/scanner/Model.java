/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.scanner;

import java.util.HashMap;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Model {
    private String fileName;
    private String filePath;
    private Scanner scanner;

    public Model() {
        this.fileName = "";
        this.filePath = "";
        this.scanner = new Scanner();
    }
    
    public void scan () {
        scanner.scan(filePath);
        scanner.printUniqueTokens();
    }
    
    public HashMap<String, Token> getScanResult() {
        return scanner.getUniqueTokens();
    }

    
    //Gets and Sets
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }


    
    
    
}
