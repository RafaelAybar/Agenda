package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;

public class Agenda {
	// No se ha especificado el número máximo de contactos, por lo que lo
	// estableceré en 20
	private static int MAX_CONTACTOS = 20;
	private int numContactos;
	private static Contacto[] listaContactos = new Contacto[MAX_CONTACTOS];

	// Creamos el constructor
	public Agenda() {
	}

	public static Contacto[] getContactos() {

		return listaContactos;
	}

	public int getNumContactos() {
		// Entiendo que no cuentan como contactos los valores nulos, por lo que contamos
		// cada valor que no sea nulo
		int contador = 0;
		for (Contacto unContacto : listaContactos) {
			if (unContacto != null) {
				contador += 1;
			}
		}
		numContactos = contador;
		return numContactos;
	}

	private static boolean indiceNoSuperatamano(int indice) {
		boolean noSupera;
		if (indice > MAX_CONTACTOS) {
			noSupera = false;
		} else {
			noSupera = true;
		}
		return noSupera;

	}

	private static int buscarPrimerIndiceComprobandoExistencia(Contacto contacto)
			throws OperationNotSupportedException {
		int indice = 0;

		for (int i = 0; i < listaContactos.length; i++) {
			if (listaContactos[i] != null && indiceNoSuperatamano(i) == false || listaContactos[i].equals(contacto)) {
				throw new OperationNotSupportedException("Ese nombre ya existe, o no hay espacio para ese contacto");
			} else {
				indice = i;
				break;
			}
		}
		return indice;
	}

	public static void anadir(Contacto contacto) throws OperationNotSupportedException {
		int indice = buscarPrimerIndiceComprobandoExistencia(contacto);
		/*
		 * Copiamos el array original en el nuevo Contacto[] listaContactos2 = new
		 * Contacto[MAX_CONTACTOS]; for (int i = 0; i < listaContactos2.length; i++) {
		 * listaContactos2[i] = listaContactos[i]; } listaContactos2[indice] = contacto;
		 */
		listaContactos[indice] = contacto;

	}

	private static int buscarIndiceCliente(String nombre) {
		int indiceCliente = -1;
		int contador = 0;
		while (contador <= listaContactos.length) {
			if (listaContactos[contador].getNombre().equals(nombre)) {
				indiceCliente = contador;
				break;
			}
			contador++;
		}
		return indiceCliente;

	}

	public static Contacto buscar(String nombre) {
		Contacto contactoEncontrado = null;
		if (buscarIndiceCliente(nombre) == -1) {
			System.out.println("El contacto introducido no existe");
		} else {
			contactoEncontrado = listaContactos[buscarIndiceCliente(nombre)];
		}
		return contactoEncontrado;
	}

	private static void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < listaContactos.length - 1 && listaContactos[i] != null; i++) {
			listaContactos[i] = listaContactos[i + 1];
		}
	}

	public static void borrar(String nombre) throws OperationNotSupportedException {
		// Primero comprobamos que el usuario a borrar existe
		Contacto nombreABorrar = buscar(nombre);
		if (nombreABorrar == null) {
			throw new OperationNotSupportedException("El usuario que desea borrar no existe");
		} else {
			desplazarUnaPosicionHaciaIzquierda(buscarIndiceCliente(nombre));
		}
	}
}
