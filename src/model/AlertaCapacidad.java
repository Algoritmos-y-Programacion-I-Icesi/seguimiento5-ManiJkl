package model;

// Interfaz sugerida para reportar alertas por sobreocupación.
public interface AlertaCapacidad {
	/**
	 * Retorna un mensaje de alerta si la atracción excede su capacidad, o cadena vacía si no hay alerta.
	 */
	String generarAlertaCapacidad();
}
