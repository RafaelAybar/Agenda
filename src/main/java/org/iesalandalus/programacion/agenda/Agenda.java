package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;

public class Agenda {
	// No se ha especificado el número máximo de contactos, por lo que lo
	// estableceré en 20
	private int MAX_CONTACTOS = 20;
	private int numContactos;
	private Contacto[] listaContactos = new Contacto[MAX_CONTACTOS];

	// Creamos el constructor
	public Agenda() {

	}

	public Contacto[] getContactos() {

		return listaContactos;
	}

	public int getNumContactos() {
		// Entiendo que no cuentan como contactos los valores nulos, por lo que contamos
		// cada valor que no sea nulo
		int contador = 0;
		for (Contacto listaContacto : listaContactos) {
			if (listaContacto != null) {
				contador += 1;
			}
		}
		numContactos = contador;
		return numContactos;
	}

	private boolean indiceNoSuperatamano(int indice) {
		boolean noSupera;
		if (indice > MAX_CONTACTOS) {
			noSupera = false;
		} else {
			noSupera = true;
		}
		return noSupera;

	}

	private int buscarPrimerIndiceComprobandoExistencia(Contacto contacto) throws OperationNotSupportedException {
		// Buscamos el primer valor del array que no sea nulo
		// para ello recorremos el array buscando el primer índice nulo
		int indice = 0;

		for (int i = 0; i < listaContactos.length; i++) {
			if (listaContactos[i] != null || listaContactos[i].equals(contacto) && indiceNoSuperatamano(i) == false) {
				throw new OperationNotSupportedException("Ese nombre ya existe, o no hay espacio para ese contacto");
			} else {
				indice = i;
			}
		}
		return indice;

	}

	public Contacto[] anadir(Contacto contacto) throws OperationNotSupportedException {
		int indice = buscarPrimerIndiceComprobandoExistencia(contacto);

		Contacto[] listaContactos = new Contacto[MAX_CONTACTOS];
		listaContactos[indice] = contacto;
		return listaContactos;

	}

	private int buscarIndiceCliente(String nombre) {
		int indiceCliente = 0;

		for (Contacto listaContacto : listaContactos) {
			if (listaContactos.getNombre().equals(nombre)) {

			}
		}
		return indiceCliente;

	}

	public Contacto buscar(String nombreAbuscar) {
		Contacto contactoEncontrado = null;
		return contactoEncontrado;

	}
}
