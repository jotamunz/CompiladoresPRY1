/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.scanner.Model;
import model.scanner.Scanner;
import model.scanner.Token;
import view.View;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Controller implements ActionListener {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        
        //Add action Listeners
        this.view.btn_scan.addActionListener(this);
        this.view.btn_select.addActionListener(this);

    }
    
    public void start(){
        view.setTitle("C Compiler Scanner");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == view.btn_select) {
            selectOption();
        }
        else if (e.getSource() == view.btn_scan) {
            scanOption();
        }
    }
    
    //Implement the Actions
    
    private void selectOption(){
        // Create File chooser
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        int returnVal = chooser.showOpenDialog(null);
        
        String filePath = "";
        String fileName = "";
     
        if (returnVal == JFileChooser.APPROVE_OPTION){ 
            filePath = chooser.getSelectedFile().getPath();
            fileName = chooser.getSelectedFile().getName();

            model.setFilePath(filePath);
            model.setFileName(fileName);

            view.txtf_fileName.setText(model.getFileName());
            view.btn_scan.setEnabled(true);
        }
    }
    
    public void scanOption(){
        model.scan();
        HashMap<String, Token> scanResult = model.getScanResult();
        DefaultTableModel table = (DefaultTableModel)view.table_scannerResult.getModel();
        table.setRowCount(0);
        for (Token token : scanResult.values()) {
            String lines = token.getLines().toString().replace("[", "").replace("]", "");
            String row[] = {token.getValue().toString(),token.getType().name(),lines};
            table.addRow(row);
        }
 
    }

}
