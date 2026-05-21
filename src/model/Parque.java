package model;

import java.util.ArrayList;

/**
 * Clase controladora del sistema MagicWorld.
 * Administra la lista de atracciones y centraliza las operaciones
 * que la interfaz de usuario necesita realizar.
 */
public class Parque {

    private String nombre;
    private ArrayList<Atraccion> atracciones;

    /**
     * Constructor del Parque. Inicializa el ArrayList de atracciones.
     */
    public Parque(String nombre) {
        this.nombre = nombre;
        this.atracciones = new ArrayList<>();
    }

    /**
     * Retorna la lista completa de atracciones registradas.
     */
    public ArrayList<Atraccion> getAtracciones() {
        return atracciones;
    }

    /**
     * Agrega una atracción genérica al parque.
     * @param nombre nombre de la atracción (no nulo)
     * @param zonaUbicacion zona donde se ubica (no nulo)
     * @param capacidadMaxima capacidad máxima de visitantes (mayor a 0)
     * @param edadMinimaAnios edad mínima permitida (mayor o igual a 0)
     * @param precioEntrada precio de entrada (mayor a 0)
     * Precondición: todos los parámetros deben ser válidos
     * Postcondición: la atracción se añade a la lista de atracciones con visitantes inicializados en 0
     */
    public void agregarAtraccion(String nombre, String zonaUbicacion, int capacidadMaxima,
                                 int edadMinimaAnios, double precioEntrada) {

        // Crear una atracción base anónima si se requiere (no usada en la solución principal)
        Atraccion atraccion = new Atraccion(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, 0, precioEntrada) {
            @Override
            public double calcularIngresoDiario() {
                return getVisitantesPorDia() * getPrecioEntrada();
            }
        };
        atracciones.add(atraccion);
    }

    /**
     * Agrega un simulador virtual al parque.
     * @param nombre nombre de la atracción (no nulo)
     * @param zonaUbicacion zona donde se ubica (no nulo)
     * @param capacidadMaxima capacidad máxima de visitantes (mayor a 0)
     * @param edadMinimaAnios edad mínima permitida (mayor o igual a 0)
     * @param precioEntrada precio de entrada (mayor a 0)
     * @param numeroEstaciones número de estaciones (mayor o igual a 0)
     * @param requiereAnteojos indica si requiere anteojos especiales
     * Precondición: parámetros válidos, numeroEstaciones >= 0
     * Postcondición: SimuladorVirtual se agrega a la lista con visitantes en 0
     */
    public void agregarSimuladorVirtual(String nombre, String zonaUbicacion, int capacidadMaxima,
                                        int edadMinimaAnios, double precioEntrada, int numeroEstaciones, boolean requiereAnteojos) {
        SimuladorVirtual s = new SimuladorVirtual(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, 0, precioEntrada, numeroEstaciones, requiereAnteojos);
        atracciones.add(s);
    }
    /**
     * Agrega un juego infantil al parque.
     * @param nombre nombre de la atracción (no nulo)
     * @param zonaUbicacion zona donde se ubica (no nulo)
     * @param capacidadMaxima capacidad máxima de visitantes (mayor a 0)
     * @param edadMinimaAnios edad mínima permitida (mayor o igual a 0)
     * @param precioEntrada precio de entrada (mayor a 0)
     * @param edadMaximaPermitida edad máxima permitida (mayor o igual a edadMinimaAnios)
     * @param supervisionPermanente indica si cuenta con supervisión permanente
     * Precondición: parámetros válidos, edadMaximaPermitida >= edadMinimaAnios
     * Postcondición: JuegoInfantil se agrega a la lista con visitantes en 0
     */
    public void agregarJuegoInfantil(String nombre, String zonaUbicacion, int capacidadMaxima,
                                     int edadMinimaAnios, double precioEntrada, int edadMaximaPermitida, boolean supervisionPermanente) {
        JuegoInfantil j = new JuegoInfantil(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, 0, precioEntrada, edadMaximaPermitida, supervisionPermanente);
        atracciones.add(j);
    }

    /**
     * Agrega un espectáculo pirotécnico al parque.
     * @param nombre nombre de la atracción (no nulo)
     * @param zonaUbicacion zona donde se ubica (no nulo)
     * @param capacidadMaxima capacidad máxima de visitantes (mayor a 0)
     * @param edadMinimaAnios edad mínima permitida (mayor o igual a 0)
     * @param precioEntrada precio de entrada (mayor a 0)
     * @param duracionMinutos duración en minutos (mayor a 0)
     * @param usaMaterialPeligroso indica si usa material peligroso certificado
     * Precondición: parámetros válidos, duracionMinutos > 0
     * Postcondición: EspectaculoPirotecnico se agrega a la lista con visitantes en 0
     */
    public void agregarEspectaculoPirotecnico(String nombre, String zonaUbicacion, int capacidadMaxima,
                                              int edadMinimaAnios, double precioEntrada, int duracionMinutos, boolean usaMaterialPeligroso) {
        EspectaculoPirotecnico e = new EspectaculoPirotecnico(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, 0, precioEntrada, duracionMinutos, usaMaterialPeligroso);
        atracciones.add(e);
    }

     /**
     * Busca una atraccion por nombre y registra sus visitantes del dia.
     * @param nombreAtraccion nombre de la atraccion
     * @param visitantesPorDia cantidad de visitantes del dia
     */
    public void registrarVisitantes(String nombreAtraccion, int visitantesPorDia) {
        Atraccion atraccionEncontrada = buscarAtraccionPorNombre(nombreAtraccion);

        if (atraccionEncontrada == null) {
            System.out.println("No se encontro una atraccion con el nombre: " + nombreAtraccion);
        } else {
            atraccionEncontrada.setVisitantesPorDia(visitantesPorDia);
        }
    }

    /**
     * Busca una atraccion por su nombre.
     * 
     * @param nombreAtraccion nombre de la atraccion buscada
     * @return la atraccion encontrada o null si no existe
     */
    public Atraccion buscarAtraccionPorNombre(String nombreAtraccion) {
        for (Atraccion atraccion : atracciones) {
            if (atraccion.getNombre().equalsIgnoreCase(nombreAtraccion)) {
                return atraccion;
            }
        }

        return null;
    }

    // ---------------------------------------------------------------
    // CALCULOS Y REPORTES
    // ---------------------------------------------------------------

    /**
     * Calcula el ingreso total diario del parque.
     * @return suma de los ingresos diarios de todas las atracciones
     * Postcondición: retorna un valor numérico >= 0 (suma de ingresos)
     */
    public double calcularIngresoTotalDiario() {
        double total = 0.0;
        for (Atraccion a : atracciones) {
            total += a.calcularIngresoDiario();
        }
        return total;
    }

    /**
     * Muestra los ingresos diarios de cada atracción y el total.
     * Postcondición: se imprime en consola el detalle de ingresos por atracción y el total
     */
    public void mostrarIngresosDiarios() {
        System.out.println("\nIngresos diarios por atracción:");
        for (Atraccion a : atracciones) {
            System.out.println(a.toString());
        }
        System.out.println("\nIngreso total diario del parque: $" + String.format("%,.2f", calcularIngresoTotalDiario()));
    }

    /**
     * Genera un reporte completo de operaciones de todas las atracciones.
     * Muestra información de cada atracción, mantenimiento requerido, nivel de riesgo y alertas.
     * Precondición: la lista de atracciones está inicializada
     * Postcondición: se imprime en consola el reporte con toda la información operacional
     */
    public void generarReporteOperaciones() {
        System.out.println("Reporte de operaciones: ");
        for (Atraccion a : atracciones) {
            System.out.println(a.toString());
            if (a instanceof RequiereMantenimiento requiereMantenimiento) {
                boolean necesita = requiereMantenimiento.requiereMantenimiento();
                System.out.println("Requiere mantenimiento: " + (necesita ? "SI" : "NO"));
            }
            if (a instanceof ClasificableRiesgo clasificableRiesgo) {
                System.out.println("Nivel de riesgo: " + clasificableRiesgo.getNivelRiesgo());
            }
            if (a instanceof AlertaCapacidad alertaCapacidad) {
                String alerta = alertaCapacidad.generarAlertaCapacidad();
                if (alerta != null && !alerta.isEmpty()) {
                    System.out.println("ALERTA: " + alerta);
                }
            }
            System.out.println();
        }
    }

    /**
     * Muestra todas las atracciones que tienen clasificación de riesgo (ALTO, MEDIO, BAJO)
     * No incluye atracciones sin clasificación de riesgo
     */
    public void mostrarAtraccionesClasifRiesgo(){
        System.out.println("Atracciones con clasificación de riesgo:");
        for (Atraccion a : atracciones) {
            if (a instanceof ClasificableRiesgo clasificableRiesgo) {
                System.out.println(a.getNombre() + " Tiene un " + clasificableRiesgo.getNivelRiesgo());
            }
        }
    }
    /**
     * Genera un reporte de alertas por sobreocupación de capacidad.
     * Muestra solo atracciones donde los visitantes superan la capacidad máxima.
     */
    public void generarReporteAlertasCapacidad(){
        System.out.println("Alertas por sobreocupación:");
        for (Atraccion a : atracciones) {
            if (a instanceof AlertaCapacidad alertaCapacidad) {
                String alerta = alertaCapacidad.generarAlertaCapacidad();
                if (alerta != null && !alerta.isEmpty()) {
                    System.out.println(a.getNombre() + " Tiene un " + alerta);
                }
            }
        }
    }

    /**
     * Alias para mostrarAtraccionesClasifRiesgo().
     * Genera la clasificación de riesgo de todas las atracciones.
     */
    public void generarClasificacionRiesgo() {
        mostrarAtraccionesClasifRiesgo();
    }
}