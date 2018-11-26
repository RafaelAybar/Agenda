package org.iesalandalus.programacion.agenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacto {
	private String ERR_TELEFONO;
	private String ERR_CORREO;
	private String nombre;
	private String telefono;
	private String correo;

	// Creamos los getters
	public String getNombre() {
		return nombre;

	}

	public String getTelefono() {
		return telefono;

	}

	public String getCorreo() {
		return correo;

	}

	public String getErrorCorreo() {
		return ERR_CORREO;

	}

	public String getErrorTelefono() {
		return ERR_TELEFONO;

	}

	// Creamos los setters
	public void setNombre(String nombre) {
		if (nombre == null || nombre.isEmpty() || nombre.equals(getNombre())) {
			throw new IllegalArgumentException("Debe introducir un nombre válido que no exista");
		} else {
			this.nombre = nombre;
		}

	}

	public void setTelefono(String telefono) {
		if (telefono.charAt(0) != '6' && telefono.charAt(0) != '9' || telefono.length() != 9) {
			throw new IllegalArgumentException("Debe introducir un número de teléfono válido");
		} else {
			this.telefono = telefono;
		}
	}

	public void setCorreo(String correo) {
		// Creamos un patrón a base de expresiones regulares, que sea indiferente a
		// mayúsculas y a minúsculas
		Pattern patron = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher correointorducido = patron.matcher(correo);

		// Comprobamos si no se cumple el patrón, es decir si devuelve false
		if (correointorducido.find() == false) {
			throw new IllegalArgumentException("Debe introducir un email válido");
		} else {
			this.correo = correo;
		}
	}
}
