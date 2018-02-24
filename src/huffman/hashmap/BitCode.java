package huffman.hashmap;

import java.io.Serializable;

public class BitCode implements Serializable{
	private String[] bitCode;
	private String[] caracter;
	private Nodo root;
	private String cadenaResultante;
	private String cadenaOriginalBinario;
	private String cadenaUsuario;

	public BitCode(int size){
		this.bitCode = new String[size];
		this.caracter = new String[size];
		this.root = null;
		this.cadenaResultante = null;
		this.cadenaOriginalBinario = null;
		this.cadenaUsuario = null;
	}

	public String [] getBitCodeTable(){
		return bitCode;
	}
	public String[] getTablaCaracteres(){
		return caracter;
	}
	public String getCadenaResultante(){
		return cadenaResultante;
	}

	public void llenarTabla(String tablaCaracteres[], String code[]){
		for(int i = 0; i < bitCode.length; i++){
			caracter[i] = tablaCaracteres[i];
			bitCode[i] = code[i];
		}
	}

	public void mostrarTabla(){
		System.out.println("\n\n\n\t\t\t===== Mostrando tabla =====\n\n");
		for(int i = 0; i < bitCode.length; i++){
			System.out.println("\t\t[" + caracter[i] + "] --> [" + bitCode[i] + "] ");
		}
	}	

	public void setCadenaResultante(String s){
		cadenaResultante = s;
	}
	public void setCadenaOriginalBinario(String s){
		cadenaOriginalBinario = s;
	}
	public void setCadenaUsuario(String s){
		cadenaUsuario = s;
	}

	//GETTERS
	public String getCadenaOriginalBinario(){
		return cadenaOriginalBinario;
	}

	public String getCadenaUsuario(){
		return cadenaUsuario;
	}
}