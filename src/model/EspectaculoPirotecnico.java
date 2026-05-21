package model;

// Clase para Espectáculos Pirotécnicos
public class EspectaculoPirotecnico extends Atraccion implements CalculableIngreso, RequiereMantenimiento, ClasificableRiesgo, AlertaCapacidad {

	private int duracionMinutos;
	private boolean usaMaterialPeligroso;

	public EspectaculoPirotecnico(String nombre, String zonaUbicacion, int capacidadMaxima,
								  int edadMinimaAnios, int visitantesPorDia, double precioEntrada,
								  int duracionMinutos, boolean usaMaterialPeligroso) {
		super(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, visitantesPorDia, precioEntrada);
		this.duracionMinutos = duracionMinutos;
		this.usaMaterialPeligroso = usaMaterialPeligroso;
	}

	@Override
	public double calcularIngresoDiario() {
		double ingreso = visitantesPorDia * precioEntrada;
		if (usaMaterialPeligroso) {
			ingreso *= 1.2; // recargo 20%
		}
		return ingreso;
	}

	@Override
	public boolean requiereMantenimiento() {
		return usaMaterialPeligroso || duracionMinutos > 60;
	}

	@Override
	public String getNivelRiesgo() {
		if (usaMaterialPeligroso) return "ALTO";
		if (duracionMinutos > 60) return "MEDIO";
		return "BAJO";
	}

	@Override
	public String generarAlertaCapacidad() {
		if (visitantesPorDia > capacidadMaxima) {
			int excedente = visitantesPorDia - capacidadMaxima;
			double porcentaje = (excedente * 100.0) / capacidadMaxima;
			return String.format("Exceso: %d visitantes (%.2f%%)", excedente, porcentaje);
		}
		return "";
	}

	public int getDuracionMinutos() { return duracionMinutos; }
	public boolean isUsaMaterialPeligroso() { return usaMaterialPeligroso; }

}
