package org.iesalandalus.programacion.agenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacto {
	// \d busca dígitos, | representa un OR
	private String ER_TELEFONO = "^[9]+[0-9]{8}|[6]+[0-9]{8}";
	private String ER_CORREO = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.+[A-Z]+[A-Z]{2,6}$";
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

	// Creamos los setters
	public void setNombre(String nombre) {
		if (nombre == null || nombre.isEmpty() || nombre.equals(getNombre().toLowerCase())
				|| nombre.equals(getNombre().toUpperCase())) {
			throw new IllegalArgumentException("Debe introducir un nombre válido que no exista");
		} else {
			this.nombre = nombre;
		}

	}

	public void setTelefono(String telefono) {
		Pattern patronTelefono = Pattern.compile(ER_TELEFONO);
		Matcher telefonoIntroducido = patronTelefono.matcher(telefono);
		if (telefonoIntroducido.find() == false) {
			throw new IllegalArgumentException("Debe introducir un número de teléfono válido");
		} else {
			this.telefono = telefono;
		}
	}

	public void setCorreo(String correo) {
		// Creamos un patrón a base de expresiones regulares, que sea indiferente a
		// mayúsculas y a minúsculas
		Pattern patron = Pattern.compile(ER_CORREO, Pattern.CASE_INSENSITIVE);
		Matcher correoIntorducido = patron.matcher(correo);

		// Comprobamos si no se cumple el patrón, es decir si devuelve false
		if (correoIntorducido.find() == false) {
			throw new IllegalArgumentException("Debe introducir un email válido");
		} else {
			this.correo = correo;
		}
	}

	// Creamos el constuctor con los parámetros pertinentes
	public Contacto(String nombre, String telefono, String correo) {
		// Creamos las validaciones
		if (nombre == null || nombre.isEmpty() || nombre.equals(getNombre())) {
			throw new IllegalArgumentException("Debe introducir un nombre válido que no exista");
		}
		if (telefono.charAt(0) != '6' && telefono.charAt(0) != '9' || telefono.length() != 9) {
			throw new IllegalArgumentException("Debe introducir un número de teléfono válido");
		}

		Pattern patron = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher correointorducido = patron.matcher(correo);

		if (correointorducido.find() == false) {
			throw new IllegalArgumentException("Debe introducir un email válido");
		}
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + " [" + telefono + " correo" + correo + "]";
	}

	// Generamos los métodos toString y hashCcode

}
