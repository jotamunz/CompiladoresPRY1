
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.compiler.Model;
import static model.compiler.parser.sym.LEX_ERROR;
import model.compiler.scanner.Token;
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
        }
    }
    
    public void scanFile(){
        ArrayList<Token> result = model.scanFile();
        DefaultTableModel tableScanner= (DefaultTableModel)view.table_scannerErrors.getModel();
        tableScanner.setRowCount(0);
        DefaultTableModel tableParser = (DefaultTableModel)view.table_parserErrors.getModel();
        tableParser.setRowCount(0);
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
        
        
        
        
    }
}
