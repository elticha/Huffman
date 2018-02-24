package huffman.hashmap;

import java.io.Serializable;

public class Arbol implements Serializable{
    private Nodo raiz;
	private int size;
	private String recorrido;

	public Arbol(){
		raiz = null;
		size = 0;
		this.recorrido = "";
	}

	public void insertarNodoRaiz(Nodo n){
		raiz = n;
	}

	public void insertar(String valor, int freq){
		try{

			Nodo n = new Nodo(valor,freq);
			n.setValor(valor);
			n.setFrecuencia(freq);

			if(raiz == null){
				raiz = n;
			}else{
				Nodo temporal = raiz;
				while(temporal != null){
					n.setPadre(temporal);
					if(freq > temporal.getFrecuencia()){
						temporal = temporal.getDerecho();
					}else{
						temporal = temporal.getIzquierdo();
					}
					/*
					if(valor.compareTo(temporal.getValor()) > 0){
						temporal = temporal.getDerecho();
					}else{
						temporal = temporal.getIzquierdo();
					}
					*/
				}
				if(freq < n.getPadre().getFrecuencia()){
					n.getPadre().setIzquierdo(n);
				}else{
					n.getPadre().setDerecho(n);
				}

				/*
				if(valor.compareTo(n.getPadre().getValor()) < 0 ){
					n.getPadre().setIzquierdo(n);
				}else{
					n.getPadre().setDerecho(n);
				}
				*/
			}
			size++;
		}catch(Exception e){
			System.out.println("Error" + e);
		}
	}
	public void inOrder ()
      {
          inOrder (raiz);
          System.out.println();
      }

	private void inOrder (Nodo x)
      {
          if (x != null)
          {    
              inOrder (x.getIzquierdo());
              System.out.print("[" + x.getValor() + " --> " + x.getFrecuencia() +  "] ");
              inOrder (x.getDerecho());
          }
      }

    public Nodo getArbol(){
    	return raiz;
    }

    private String containsNodeRecursive(Nodo current, String value){
    	if(current == null){
    		return "No hay nodos";
    	}

    	if(value.equals(current.getValor())){
    		System.out.println("Comparando " + value  + " con " + current.getValor());
    		return "0";
    	}

    	if(!current.getValor().equals("")){
    		if(value.compareTo(current.getValor()) > 0){//Si es mayor
    			System.out.println("IF 1 ---> " + "Comparando " + value  + " con " + current.getValor());
	    		recorrido += "0";
	    		containsNodeRecursive(current.getDerecho(), value);
	    	}else{
	    		recorrido += "1";
	    		containsNodeRecursive(current.getIzquierdo(), value);
	    	}
    	}else{
    		containsNodeRecursive(current.getIzquierdo(), value);
    	}
    	return recorrido;
    }

    public void resetRecorridoString(){
    	recorrido = "";
    }
    public String buscar(Nodo r, String value){
    	return containsNodeRecursive(r, value);
    }
}
