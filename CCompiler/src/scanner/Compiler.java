
package scanner;

import javax.swing.JFileChooser;

public class Compiler {
    
    public static void main(String[] args) {
        // Create File chooser
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        int returnVal = chooser.showOpenDialog(null);
        
        String filePath = "";
        if( returnVal == JFileChooser.APPROVE_OPTION ){
                filePath = chooser.getSelectedFile().getPath();
        }
        

        System.out.println("\nCompiling "+filePath+" ...\n");
        
        Scanner scanner = new Scanner();
        scanner.scan(filePath);
        scanner.printUniqueTokens();
    }
}
