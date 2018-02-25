/*
 * Clase que genera el arbol de Hufmman
 */
package huffman.hashmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Huffman {

    private File archivoLeido;
    private String cadenaLeida;
    private HashMap<String, Integer> freqTable;
    private ArrayList<String> keys;

    public Huffman() {
        archivoLeido = null;
        cadenaLeida = "";
        freqTable = new HashMap<>();
        keys = new ArrayList<>();
    }

    public void createTree() {
        int lFreq, rFreq,i;
        while(freqTable.size() > 1){
            lFreq = freqTable.get(keys.get(0));
            rFreq = freqTable.get(keys.get(1));
            freqTable.put("", (lFreq +  rFreq) );
            freqTable.remove(keys.get(0));
            freqTable.remove(keys.get(1));
            keys.remove(0);
            keys.remove(0);
            freqTable = sort();
        }
        
        displayTable();
    }

    public void setFile(File f) throws IOException {
        this.archivoLeido = f;
        openFile();
    }

    public void pepareTable() {
        for (int i = 0; i < cadenaLeida.length(); i++) {
            freqTable.put(String.valueOf(cadenaLeida.charAt(i)), getFrequencyOf(String.valueOf(cadenaLeida.charAt(i))));
        }

        //Ordenar la tabla
        freqTable = sort();
    }

    public void displayTable() {
        Set set = freqTable.entrySet();
        Iterator iterator = set.iterator();
        System.out.println("\n\n\t=== Tabla de frecuencias ===\n\n");
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("\t   [ " + mentry.getKey() + " ] ---> [ " + mentry.getValue() + " ]");
            keys.add((String)mentry.getKey());
        }
    }

    private HashMap sort() {
        List list = new LinkedList(freqTable.entrySet());
        HashMap sorted = new LinkedHashMap();
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object t, Object t1) {
                return ((Comparable) ((Map.Entry) (t)).getValue()).compareTo(((Map.Entry) (t1)).getValue());
            }

        });

        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sorted.put(entry.getKey(), entry.getValue());
        }

        return sorted;
    }

    private Integer getFrequencyOf(String s) {
        int counter = 0;
        for (int i = 0; i < cadenaLeida.length(); i++) {
            if (s.equals(String.valueOf(cadenaLeida.charAt(i)))) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isTheFileNull() {
        return archivoLeido == null;
    }

    private void openFile() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivoLeido));
        String readed;

        if (getLineasDeTexto() > 1) {
            while ((readed = reader.readLine()) != null) {
                cadenaLeida += readed + "\n";
            }
        } else {
            while ((readed = reader.readLine()) != null) {
                cadenaLeida += readed;
            }
        }
        System.out.println("\n\n*** Se ha cargado el archivo de texto ***\n\n");
    }

    private int getLineasDeTexto() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivoLeido));
        int i = 0;
        String readed = "";
        while ((readed = reader.readLine()) != null) {
            i++;
        }
        reader.close();
        return i;
    }
}
