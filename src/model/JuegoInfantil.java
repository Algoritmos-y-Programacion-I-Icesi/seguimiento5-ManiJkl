package model;

public class JuegoInfantil extends Atraccion implements CalculableIngreso, RequiereMantenimiento, AlertaCapacidad {

	private int edadMaximaPermitida;
	private boolean supervisionPermanente;

	public JuegoInfantil(String nombre, String zonaUbicacion, int capacidadMaxima,
						 int edadMinimaAnios, int visitantesPorDia, double precioEntrada,
						 int edadMaximaPermitida, boolean supervisionPermanente) {
		super(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, visitantesPorDia, precioEntrada);
		this.edadMaximaPermitida = edadMaximaPermitida;
		this.supervisionPermanente = supervisionPermanente;
	}

	@Override
	public double calcularIngresoDiario() {
		double ingreso = visitantesPorDia * precioEntrada;
		if (supervisionPermanente) {
			ingreso += 50000.0 * visitantesPorDia;
		}
		return ingreso;
	}

	@Override
	public boolean requiereMantenimiento() {
		return !supervisionPermanente || visitantesPorDia > capacidadMaxima;
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

	public int getEdadMaximaPermitida() { return edadMaximaPermitida; }
	public void setEdadMaximaPermitida(int edadMaximaPermitida) { this.edadMaximaPermitida = edadMaximaPermitida; }
	public boolean isSupervisionPermanente() { return supervisionPermanente; }
	public void setSupervisionPermanente(boolean supervisionPermanente) { this.supervisionPermanente = supervisionPermanente; }

}
