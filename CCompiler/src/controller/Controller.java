
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.compiler.Model;
import static model.compiler.parser.sym.LEX_ERROR;
import model.compiler.scanner.Token;
import model.compiler.translator.SemanticError;
import model.compiler.translator.Symbols.FunctionData;
import model.compiler.translator.Symbols.IdentifierData;
import model.compiler.translator.Symbols.VariableData;
import view.View;

public class Controller implements ActionListener {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    
    public void start(){
        //Action listeners
        this.view.btn_compile.addActionListener(this);
        this.view.btn_select.addActionListener(this);
        
        view.setTitle("C Compiler");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == view.btn_select) {
            selectFile();
        }
        else if (e.getSource() == view.btn_compile) {
            scanFile();
        }
    }

    //Actions
    
    private void selectFile(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        int returnVal = chooser.showOpenDialog(null);
     
        if (returnVal == JFileChooser.APPROVE_OPTION){ 
            String filePath = chooser.getSelectedFile().getPath();
            String fileName = chooser.getSelectedFile().getName();

            model.setFilePath(filePath);
            model.setFileName(fileName);

            view.txtf_fileName.setText(model.getFileName());
            view.btn_compile.setEnabled(true);
            view.txtArea_input.setText(readFile(filePath));
        }
        
    }
    
    public void scanFile(){
        ArrayList<Token> result = model.scanFile();
        DefaultTableModel tableScanner= (DefaultTableModel)view.table_scannerErrors.getModel();
        tableScanner.setRowCount(0);
        DefaultTableModel tableParser = (DefaultTableModel)view.table_parserErrors.getModel();
        tableParser.setRowCount(0);
        DefaultTableModel tableSemantic = (DefaultTableModel)view.table_semanticErrors.getModel();
        tableSemantic.setRowCount(0);
        DefaultTableModel symbolVariablesTable = (DefaultTableModel)view.table_symbolVariablesTable.getModel();
        symbolVariablesTable.setRowCount(0);
        DefaultTableModel symbolFunctionsTable = (DefaultTableModel)view.table_symbolFunctionsTable.getModel();
        symbolFunctionsTable.setRowCount(0);

        
        for (Token token : result) {
            String row[] = {token.getValue().toString(),String.valueOf(token.getLineNum()),String.valueOf(token.getColNum())};
            if (token.getId() == LEX_ERROR){
                tableScanner.addRow(row);
            }
        }
        model.parseAndTranslateFile();
        result = model.getSyntaxErrors();
        for (Token token : result){
            if (token.getValue() != null){
                String row[] = {token.getValue().toString(),String.valueOf(token.getLineNum()),String.valueOf(token.getColNum())};
                tableParser.addRow(row);
            }
        }
        
        model.getSemanticErrors().forEach((error) -> {
            String row[] = {error.getTokenValue().toString(),String.valueOf(error.getLineNumber()),String.valueOf(error.getColNumber()),error.getMessage()};
            tableSemantic.addRow(row); 
        });

        
        HashMap<String,IdentifierData> symbolTableValue = model.getSymbolTable();
        symbolTableValue.keySet().forEach((symbol)->{
          IdentifierData value = symbolTableValue.get(symbol);
            if (value.getClass().equals(VariableData.class)) {
                VariableData variable = (VariableData) value;
                String row[] = {variable.getType(),variable.getName(),String.valueOf(variable.isError())};
                symbolVariablesTable.addRow(row);  
            }
            else{
                FunctionData function = (FunctionData) value;
                String row[] = {function.getType(),function.getName(),function.getParameterData().toString(),String.valueOf(function.isError())};
                symbolFunctionsTable.addRow(row);   
            }
        });
        
        System.out.println(model.getNasmCode());
        view.txtArea_output.setText(model.getNasmCode());
       

    }
      
    public String readFile(String path){
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            byte[] buffer = new byte[10];
            StringBuilder sb = new StringBuilder();
            try {
                while (fis.read(buffer) != -1) {
                    sb.append(new String(buffer));
                    buffer = new byte[10];
                }
                fis.close();

                String content = sb.toString();
                return content;
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
