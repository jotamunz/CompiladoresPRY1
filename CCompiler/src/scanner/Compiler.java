
package scanner;

public class Compiler {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        scanner.scan("prueba.txt");
        scanner.printUniqueTokens();
    }
}
