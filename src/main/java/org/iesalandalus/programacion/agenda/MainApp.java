package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	private static String ERROR = "ERROR";
	private static String EXITO = "Operación realizada satisfactoriamente";
	private static Contacto contacto;

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

	public static void anadirContacto() throws OperationNotSupportedException {
		System.out.println("Introduce el nombre del contacto");
		String nombre = Entrada.cadena();

		System.out.println("Introduce el email del contacto");
		String correo = Entrada.cadena();

		System.out.println("Introduce el teléfono del contacto (móvil o fijo)");
		String telefono = Entrada.cadena();

		contacto = new Contacto(nombre, telefono, correo);
		Agenda.anadir(contacto);

		/*
		 * if (Agenda.buscar(contacto.getNombre()).equals(nombre)) {
		 * System.out.println(EXITO); } else { System.out.println(ERROR +
		 * " El contacto no se ha podido añadir"); }
		 */
	}

	public static void borrarContacto() throws OperationNotSupportedException {
		System.out.print("Introduce el nombre del contacto a borrar");
		String nombreABorrar = Entrada.cadena();

		if (Agenda.buscar(nombreABorrar) == null) {
			System.out.println(ERROR + " El contacto introducido no existe");
		} else {
			Agenda.borrar(nombreABorrar);

			if (Agenda.buscar(nombreABorrar) == null) {
				System.out.println(EXITO);
			} else {
				System.out.println(ERROR + " El contacto no se ha podido borrar correctamente");
			}
		}
	}

	public static void buscarContacto(String nombre) {

		Contacto contactoBuscado = Agenda.buscar(nombre);
		if (contactoBuscado == null) {
			System.out.println(ERROR + " El contacto del nombbre introducido no existe");
		} else {
			System.out.println("Contacto: " + contactoBuscado);
		}
	}

	public static void listarAgenda() {
		Contacto[] listado = new Contacto[20];
		listado = Agenda.getContactos();
		for (Contacto element : listado) {
			if (element != null) {
				System.out.println("Contacto" + " " + element);
			}
		}
	}

	public static void ejecutarOpcion(int numeroPrincipal) throws OperationNotSupportedException {
		switch (numeroPrincipal) {
		case 1:
			anadirContacto();
			break;

		case 2:
			System.out.println("Introduce el nombre del contacto a buscar");
			String nombre = Entrada.cadena();
			buscarContacto(nombre);
			break;

		case 3:
			borrarContacto();
			break;
		case 4:
			listarAgenda();
			break;
		case 5:
			System.out.println("Adiós");
			break;
		default:
			System.out.println("Debes introducir una opción válida");
			break;
		}
	}

	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("Programa para gestionar una agenda de contactos");
		mostrarMenu();
		int numeroPrincipal = Entrada.entero();
		do {
			ejecutarOpcion(numeroPrincipal);
			System.out.println("Selecciona qué quieres hacer ahora");
			numeroPrincipal = Entrada.entero();
		} while (numeroPrincipal != 5);
	}

}
