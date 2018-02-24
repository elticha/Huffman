/*
 * Creado por Luis Fernando Hernández Morales
 * Ingeniería en Desarrollo de Software
 */
package huffman.hashmap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Luis Fernando
 */
public class HuffmanHashMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner input = new Scanner(System.in);
        int option = 0;
        String fileName = null;
        Huffman arbolito = new Huffman();

        while (option < 1 || option > 6) {
            showMenu();
            option = input.nextInt();
            switch (option) {
                case 1: {
                    input.nextLine();
                    System.out.print("Ingrese el nombre del fichero de texto >> ");
                    fileName = input.nextLine();
                    if (!fileName.contains(".txt")) {
                        fileName += ".txt";
                    }
                    arbolito.setFile(new File(fileName));
                    option = 0;
                    break;
                }
                case 2:{
                    if(!arbolito.isTheFileNull()){
                        arbolito.pepareTable();
                        arbolito.displayTable();
                        arbolito.createTree();
                    }else{
                        System.out.println("Al parecer no has cargado un fichero, [ es la opcion 1 ]");
                        option = 0;
                    }
                    option = 0;
                    break;
                }
            }
        }

    }

    static void showMenu() throws InterruptedException {
        String supremeString = "\n\n\t\t   = = = M E N U = = =\n\n"
                + "1.- Abrir fichero de texto.\n"
                + "2.- Generar arbol de Huffman [Almacena arbol y texto resultante automaticamente].\n"
                + "3.- Leer un archivo de texto comprimido.\n"
                + "4.- Leer un arbol de Huffman.\n"
                + "5- Salir.\n\n" + "\n\nIngrese una opcion >> ";

        for (int i = 0; i < supremeString.length(); i++) {
            Thread.sleep(10);
            System.out.print(supremeString.charAt(i));
        }
    }
}
