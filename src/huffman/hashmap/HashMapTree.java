package huffman.hashmap;

import java.io.Serializable;
import java.util.HashMap;

public class HashMapTree implements Serializable {

    private HashMap<String, Integer> padre;
    private HashMap<String, Integer> izquierdo;
    private HashMap<String, Integer> derecho;

    public HashMapTree(String l, Integer freq) {
        padre = new HashMap<>();
        padre.put(l,freq);
        izquierdo = null;
        derecho = null;
    }

    public HashMap getIzq() {
        return izquierdo;
    }

    public HashMap getDer() {
        return derecho;
    }

    public HashMap getPadre() {
        return padre;
    }

    public void setPadre(HashMap p) {
        this.padre = p;
    }

    public void setDerecho(HashMap d) {
        this.derecho = d;
    }

    public void setIzquierdo(HashMap i) {
        this.izquierdo = i;
    }
}
