package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;

public class Agenda {
	// No se ha especificado el número máximo de contactos, por lo que lo
	// estableceré en 20
	private int MAX_CONTACTOS = 20;
	private int numContactos;
	private Contacto[] contactos;

	public Agenda() {
		contactos = new Contacto[MAX_CONTACTOS];
	}

	public Contacto[] getContactos() {

		return contactos;
	}

	public int getNumContactos() {

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
		int indice = -1;

		for (int i = 0; i < contactos.length && contactos[i] == null; i++) {
			if (indiceNoSuperatamano(i)) {
				indice = i;
			} else {
				throw new OperationNotSupportedException("No hay espacio para ese contacto");
			}

		}
		return indice;
	}

	public void anadir(Contacto contacto) throws OperationNotSupportedException {
		for (Contacto contacto2 : contactos) {
			if (contacto2 != null && contacto2.getNombre().equals(contacto.getNombre())) {
				throw new IllegalArgumentException("Ya existe un contacto con ese nombre");
			}
		}
		int indice = buscarPrimerIndiceComprobandoExistencia(contacto);

		contactos[indice] = contacto;

		numContactos++;

	}

	private int buscarIndiceCliente(String nombre) {
		int indiceCliente = -1;

		for (int i = 0; i < contactos.length; i++) {

			if (contactos[i] != null && contactos[i].getNombre().equals(nombre)) {
				indiceCliente = i;
			}
		}

		return indiceCliente;
	}

	public Contacto buscar(String nombre) {
		Contacto contactoEncontrado;
		if (buscarIndiceCliente(nombre) == -1) {
			throw new IllegalArgumentException("El contacto introducido no existe");
		} else {
			contactoEncontrado = contactos[buscarIndiceCliente(nombre)];
		}

		return contactoEncontrado;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		if (indice == -1) {
			throw new IllegalArgumentException("No se ha podido desplazar a la izquierda");
		} else {
			for (int i = indice; i < contactos.length && contactos[i] != null; i++) {
				contactos[i] = contactos[i + 1];
			}
		}
	}

	public void borrar(String nombre) throws OperationNotSupportedException {
		// Primero comprobamos que el usuario a borrar existe
		Contacto nombreABorrar = buscar(nombre);
		if (nombreABorrar == null) {
			throw new OperationNotSupportedException("El usuario que desea borrar no existe");
		} else {
			contactos[buscarIndiceCliente(nombre)] = null;
			desplazarUnaPosicionHaciaIzquierda(buscarIndiceCliente(nombre));
			numContactos--;
		}
	}
}
