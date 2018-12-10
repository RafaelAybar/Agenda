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
		// Agenda.listaContactos;
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
		int indice = -1;
		Contacto[] lista = new Contacto[20];
		lista = Agenda.getContactos();
		for (int i = 0; i < lista.length && lista[i] == null; i++) {
			if (indiceNoSuperatamano(i)) {
				indice = i;
			} else {
				throw new OperationNotSupportedException("No hay espacio para ese contacto");
			}
		}
		return indice;
	}

	public static void anadir(Contacto contacto) throws OperationNotSupportedException {
		int indice = buscarPrimerIndiceComprobandoExistencia(contacto);
		Agenda.listaContactos[indice] = contacto;

	}

	private static int buscarIndiceCliente(String nombre) {
		int indiceCliente = -1;
		Contacto[] lista = new Contacto[20];
		lista = getContactos();

		for (int i = 0; i < lista.length && lista[i] == null; i++) {

			if (lista[i] != null && lista[i].getNombre().equals(nombre)) {
				indiceCliente = i;
			}
		}


		return indiceCliente;
	}

	public static Contacto buscar(String nombre) {
		Contacto contactoEncontrado;
		if (buscarIndiceCliente(nombre) == -1) {
			throw new IllegalArgumentException("El contacto introducido no existe");
		} else {
			contactoEncontrado = Agenda.listaContactos[buscarIndiceCliente(nombre)];
		}
		System.out.print(contactoEncontrado);
		return contactoEncontrado;
	}

	private static void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < Agenda.listaContactos.length - 1 && Agenda.listaContactos[i] != null; i++) {
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
