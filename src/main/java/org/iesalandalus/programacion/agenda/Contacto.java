package org.iesalandalus.programacion.agenda;

import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacto {
	// | representa un OR
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
		if (nombre == null || nombre.isEmpty() || nombre.equals(getNombre().toLowerCase())) {
			throw new IllegalArgumentException("Debe introducir un nombre válido que no exista");
		} else {
			this.nombre = nombre;
		}

	}

	public void setTelefono(String telefono) {
		Pattern patronTelefono = Pattern.compile(ER_TELEFONO);
		Matcher telefonoIntroducido = patronTelefono.matcher(telefono);
		if (telefonoIntroducido.matches() == false) {
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
		if (correoIntorducido.matches() == false) {
			throw new IllegalArgumentException("Debe introducir un email válido");
		} else {
			this.correo = correo;
		}
	}

	// Creamos el constuctor con los parámetros pertinentes
	public Contacto(String nombre, String telefono, String correo) {
		// Creamos las validaciones
		setNombre(nombre);
		setTelefono(telefono);
		setCorreo(correo);
	}

	// Creamos los métodos equals y hashCode
	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Contacto)) {
			return false;
		}
		Contacto other = (Contacto) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

	// Creamos el método getIniciales
	public String getIniciales(String nombre) {
		// Usaremos StringTokenizer
		StringTokenizer cadena = new StringTokenizer(nombre);
		String iniciales = "";
		while (cadena.hasMoreTokens()) {

			/* = cadena.nextToken().charAt(0); */
			String nombreCompletoToken = cadena.nextToken();
			iniciales += nombreCompletoToken.charAt(0);

		}
		return iniciales;

	}

	@Override
	public String toString() {
		return "" + getIniciales(nombre) + "[" + nombre + " ," + telefono + ", " + correo + "]";
	}

}
