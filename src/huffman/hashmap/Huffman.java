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

    //combine
    //Tree map
    //Cadenas de 2 a 6
    private File archivoLeido;
    private String cadenaLeida;
    private HashMap<String, Integer> freqTable;
    private ArrayList<String> keys;
    private ArrayList<Nodo> nodos;
    private ArrayList<Nodo> referencias;
    private Arbol arbol;

    public Huffman() {
        archivoLeido = null;
        cadenaLeida = "";
        freqTable = new HashMap<>();//Tabla original
        keys = new ArrayList<>();//LLaves de la tabla original
        nodos = new ArrayList<>();//Nodos
        referencias = new ArrayList<>();//Copia de los nodos
        arbol = new Arbol();//Arbol a guardar
        
        
        
        
        

    }
    
        private void pruebaGrupos(){
            String s;
           for(int i = 0; i < cadenaLeida.length()-1; i++){
               s = cadenaLeida.substring(i, i+2);
               if(getCantidadDeCoincidencias(s) > 6){
                s = cadenaLeida.substring(i, i+3);
               }
               //System.out.println("La cadena " + s + " se repite " + getCantidadDeCoincidencias(s) + " veces.");
           }
        }
    
    private int getCantidadDeCoincidencias(String s){
        int times = 0;
        String cmp = cadenaLeida;
        while(cmp.indexOf(s) > -1){
           cmp = cmp.substring(cmp.indexOf(s)+s.length(),cmp.length());
           times++;
        }
        return times;
    }

    public void createTree() {

        int lFreq, rFreq;
        Nodo lNode, rNode;

        while (nodos.size() > 1) {
            lFreq = nodos.get(0).getFrecuencia();
            rFreq = nodos.get(1).getFrecuencia();
            lNode = nodos.get(0);
            rNode = nodos.get(1);

            nodos.add(new Nodo("", (lFreq + rFreq)));
            nodos.get(nodos.size() - 1).setIzquierdo(lNode);
            nodos.get(nodos.size() - 1).getIzquierdo().setLado(0);
            nodos.get(nodos.size() - 1).setDerecho(rNode);
            nodos.get(nodos.size() - 1).getDerecho().setLado(1);
            nodos.get(nodos.size() - 1).getIzquierdo().setPadre(nodos.get(nodos.size() - 1));
            nodos.get(nodos.size() - 1).getDerecho().setPadre(nodos.get(nodos.size() - 1));

            if (nodos.get(0).getValor() != null) {
                referencias.add(nodos.get(0));
                referencias.add(nodos.get(1));
            }
            nodos.remove(0);
            nodos.remove(0);
            nodos = reOrdenar(nodos);
        }
        nodos.get(0).setPadre(null);
        
        arbol.insertarNodoRaiz(nodos.get(0));
        pruebaGrupos();
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
            keys.add((String) mentry.getKey());
            nodos.add(new Nodo((String) mentry.getKey(), (int) mentry.getValue()));
        }
    }

    private HashMap sort() {
        List list = new LinkedList(freqTable.entrySet());
        HashMap sorted = new LinkedHashMap();
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object t, Object t1) {
                return ((Comparable) ((Map.Entry) t).getValue()).compareTo(((Map.Entry) t1).getValue());
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

    private ArrayList<Nodo> reOrdenar(ArrayList<Nodo> nodos) {
        ArrayList<Nodo> aux = nodos;
        //Burbuja
        Nodo temp = null;
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = 1; j < (nodos.size() - i); j++) {
                if (aux.get(j - 1).getFrecuencia() > aux.get(j).getFrecuencia()) {
                    temp = aux.get(j - 1);
                    aux.set((j - 1), aux.get(j));
                    aux.set(j, temp);
                }
            }
        }
        return aux;
    }

}
