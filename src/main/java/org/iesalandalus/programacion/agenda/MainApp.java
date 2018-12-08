package org.iesalandalus.programacion.agenda;

public class MainApp {
	private String ERROR = "ERROR";
	private String EXITO = "Operación realizada satisfactoriamente";

	public void mostrarMenu() {
		System.out.println("Selecciona qué quieres hacer");
		System.out.println("1 añadir un contacto,  2 buscar un contacto, 3 borrar un contacto");
		System.out.println("4 listar todos los contactos no nulos, 6 salir");
	}

	public static void main(String[] args) {
		System.out.println("Programa para gestionar una agenda de contactos");

	}

}
