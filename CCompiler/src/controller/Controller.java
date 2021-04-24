
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.compiler.Model;
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
        this.view.btn_scan.addActionListener(this);
        this.view.btn_select.addActionListener(this);
        view.setTitle("C Compiler Scanner");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == view.btn_select) {
            selectFile();
        }
        else if (e.getSource() == view.btn_scan) {
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
            view.btn_scan.setEnabled(true);
        }
    }
    
    public void scanFile(){
        HashMap<String, Token> scanResult = model.scanFile();
        DefaultTableModel table = (DefaultTableModel)view.table_scannerResult.getModel();
        table.setRowCount(0);
        for (Token token : scanResult.values()) {
            String row[] = {token.getValue().toString(),token.getType().name(),token.linesToString()};
            table.addRow(row);
        } 
    }

}
