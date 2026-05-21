package model;

public class SimuladorVirtual extends Atraccion implements CalculableIngreso, RequiereMantenimiento, ClasificableRiesgo, AlertaCapacidad {

    private int estaciones;
    private boolean anteojos;

    public SimuladorVirtual(String nombre, String zonaUbicacion, int capacidadMaxima,
                            int edadMinimaAnios, int visitantesPorDia, double precioEntrada,
                            int estaciones, boolean anteojos) {
        super(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, visitantesPorDia, precioEntrada);
        this.estaciones = estaciones;
        this.anteojos = anteojos;
    }

    @Override
    public double calcularIngresoDiario() {
        double ingreso = visitantesPorDia * precioEntrada;
        if (!anteojos) {
            ingreso *= 0.9;
        }
        return ingreso;
    }

    @Override
    public boolean requiereMantenimiento() {
        return estaciones > 20 || visitantesPorDia > capacidadMaxima;
    }

    @Override
    public String getNivelRiesgo() {
        if (anteojos && estaciones > 20) return "ALTO";
        if (anteojos || estaciones > 20) return "MEDIO";
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

    public int getEstaciones() { return estaciones; }
    public void setEstaciones(int estaciones) { this.estaciones = estaciones; }
    public boolean isAnteojos() { return anteojos; }
    public void setAnteojos(boolean anteojos) { this.anteojos = anteojos; }

}