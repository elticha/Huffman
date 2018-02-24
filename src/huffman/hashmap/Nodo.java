package huffman.hashmap;

import java.io.Serializable;

public class Nodo implements Serializable{
      private Nodo padre;
      private Nodo derecho;
      private Nodo izquierdo;
      private String valor;
      private int frecuencia;
      private int lado;

      public Nodo(String s, int i){
            derecho = null;
            izquierdo = null;
            padre = null;
            this.valor = s;
            this.frecuencia = i;
            this.lado = -1;//-1 si no tiene un nodo padre
      }

      public String getValor(){
            return valor;
      }
      public int getFrecuencia(){
            return frecuencia;
      }
      public String getLado(){
            return String.valueOf(lado);
      }
      public Nodo getPadre(){
            return padre;
      }
      public Nodo getDerecho(){
            return derecho;
      }
      public Nodo getIzquierdo(){
            return izquierdo;
      }
      public void setValor(String s){
            this.valor = s;
      }
      public void setFrecuencia(int f){
            this.frecuencia = f;
      }
      public void setLado(int i){
            this.lado = i;
      }
      public void setPadre(Nodo p){
            this.padre = p;
      }
      public void setDerecho(Nodo d){
            this.derecho = d;
      }
      public void setIzquierdo(Nodo i){
            this.izquierdo = i;
      }

      public String mostrar(){
            return valor + " --> " + frecuencia + ", padre = [" + padre + "], lado = " + lado ;
      }
      public String mostrarConPapa(){
            return valor + " --> " + frecuencia + ", padre = [" + padre + "], lado = " + lado ;     
      }
}