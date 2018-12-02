package org.iesalandalus.programacion.agenda;

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
		if (indice > 20) {
			noSupera = false;
		} else {
			noSupera = true;
		}
		return noSupera;

	}

	private Contacto buscarPrimerIndiceComprobandoExistencia(int indice) {
		return null;

	}

	public void anadir(Contacto contacto) {
	}
}
