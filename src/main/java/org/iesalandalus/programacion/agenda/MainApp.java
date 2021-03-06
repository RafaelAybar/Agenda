package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	private static String ERROR = "ERROR";
	private static String EXITO = "Operación realizada satisfactoriamente";
	private static Contacto contacto;

	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("Programa para gestionar una agenda de contactos");
		mostrarMenu();
		int numeroPrincipal = Entrada.entero();
		Agenda agenda = new Agenda();
		do {
			ejecutarOpcion(numeroPrincipal, agenda);
			System.out.println("Selecciona qué quieres hacer ahora");
			numeroPrincipal = Entrada.entero();
		} while (numeroPrincipal != 5);
	}

	public static void mostrarMenu() {
		System.out.println("Selecciona qué quieres hacer");
		System.out.println("1 añadir un contacto,  2 buscar un contacto, 3 borrar un contacto");
		System.out.println("4 listar todos los contactos, 5 salir");
	}

	public static int elegirOpcion(int numeroPrincipal) {
		if (numeroPrincipal < 1 || numeroPrincipal > 6) {
			System.out.println("La opción introducida es incorrecta");
		}
		return numeroPrincipal;
	}

	public static void anadircontacto(Agenda agenda) throws OperationNotSupportedException {

		System.out.println("Introduce el nombre del contacto");
		String nombre = Entrada.cadena();

		System.out.println("Introduce el email del contacto");
		String correo = Entrada.cadena();

		System.out.println("Introduce el teléfono del contacto (móvil o fijo)");
		String telefono = Entrada.cadena();


		try{
			contacto = new Contacto(nombre, telefono, correo);
			agenda.anadir(contacto);
		}
		catch (IllegalArgumentException e){
			System.out.println("Los datos introducidos no son correctos");
		}

	}

	public static void borrarContacto(Agenda agenda) throws OperationNotSupportedException {
		System.out.print("Introduce el nombre del contacto a borrar");
		String nombreABorrar = Entrada.cadena();

		if (agenda.buscar(nombreABorrar) == null) {
			System.out.println(ERROR + " El contacto introducido no existe");
		} else {
			agenda.borrar(nombreABorrar);

			if (agenda.buscar(nombreABorrar) == null) {
				System.out.println(EXITO);
			} else {
				System.out.println(ERROR + " El contacto no se ha podido borrar correctamente");
			}
		}
	}

	public static void buscarContacto(String nombre, Agenda agenda) {

		Contacto contactoBuscado = agenda.buscar(nombre);
		if (contactoBuscado == null) {
			System.out.println(ERROR + " El contacto del nombre introducido no existe");
		} else {
			System.out.println("Contacto: " + contactoBuscado);
		}
	}

	public static void listaragenda(Agenda agenda) {
		Contacto[] listado = new Contacto[20];
		listado = agenda.getContactos();
		for (Contacto element : listado) {
			if (element != null) {
				System.out.println("Contacto" + " " + element);
			}
		}
	}
	// Es mejor pasar la agenda por parámetro

	public static void ejecutarOpcion(int numeroPrincipal, Agenda agenda) throws OperationNotSupportedException {
		switch (numeroPrincipal) {
		case 1:
			try {
				anadircontacto(agenda);
			} catch (IllegalArgumentException e){
				System.out.println("El contacto introducido ya existe");
			}

			break;

		case 2:
			System.out.println("Introduce el nombre del contacto a buscar");
			String nombre = Entrada.cadena();
			try {
				buscarContacto(nombre, agenda);
			}catch (IllegalArgumentException e){
				System.out.println("El contacto buscado no existe");
			}

			break;

		case 3:
			borrarContacto(agenda);
			break;
		case 4:
			listaragenda(agenda);
			break;
		case 5:
			System.out.println("Adiós");
			break;
		default:
			System.out.println("Debes introducir una opción válida");
			break;
		}
	}

}
