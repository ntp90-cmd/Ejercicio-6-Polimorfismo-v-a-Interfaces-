import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class IntegracionAPI
        implements Auditable, Comparable<IntegracionAPI> {

    private final String id;
    private final String nombre;
    private String endpoint;
    private boolean activa;
    private String estadoConexion;
    private double latenciaPromedioMs;

    public IntegracionAPI(
            String id, String nombre, String endpoint,
            boolean activa, String estadoConexion,
            double latenciaPromedioMs) {

        this.id = textoValido(id, "Identificador");
        this.nombre = textoValido(nombre, "Nombre");
        setEndpoint(endpoint);
        setActiva(activa);
        setEstadoConexion(estadoConexion);
        setLatenciaPromedioMs(latenciaPromedioMs);
    }

    protected static String textoValido(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                campo + " no puede estar vacío."
            );
        }

        return texto.trim();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public boolean isActiva() {
        return activa;
    }

    public String getEstadoConexion() {
        return estadoConexion;
    }

    public double getLatenciaPromedioMs() {
        return latenciaPromedioMs;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = textoValido(endpoint, "Endpoint");
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void setEstadoConexion(String estado) {
        this.estadoConexion = textoValido(estado, "Estado");
    }

    public void setLatenciaPromedioMs(double latencia) {
        if (!Double.isFinite(latencia) || latencia < 0) {
            throw new IllegalArgumentException(
                "La latencia debe ser finita y no negativa."
            );
        }

        this.latenciaPromedioMs = latencia;
    }

    public abstract String consultarEstado();

    @Override
    public void registrarEvento(String evento) {
        Logger.getLogger("Auditoria").info(
            "[" + id + "] " + evento
        );
    }

    @Override
    public int compareTo(IntegracionAPI otra) {
        return Double.compare(
            latenciaPromedioMs,
            otra.latenciaPromedioMs
        );
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }

        if (!(otro instanceof IntegracionAPI)) {
            return false;
        }

        IntegracionAPI otraAPI = (IntegracionAPI) otro;
        return id.equals(otraAPI.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
            Locale.ROOT,
            "Identificador: %s | Nombre: %s%n"
                + "Endpoint: %s%n"
                + "Activa: %s | Conexión: %s | Latencia: %.2f ms",
            id,
            nombre,
            endpoint,
            activa ? "Sí" : "No",
            estadoConexion,
            latenciaPromedioMs
        );
    }
}
