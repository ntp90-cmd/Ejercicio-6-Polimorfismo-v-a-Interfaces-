import java.util.Locale;
import java.util.UUID;

public class PasarelaPago
        extends IntegracionAPI implements Transaccional {

    private final String modalidad;

    public PasarelaPago(
            String id, String nombre, String endpoint,
            boolean activa, String estado, double latencia,
            String modalidad) {

        super(id, nombre, endpoint, activa, estado, latencia);
        this.modalidad = textoValido(modalidad, "Modalidad");
    }

    public String getModalidad() {
        return modalidad;
    }

    @Override
    public String consultarEstado() {
        return "Pasarela " + getNombre()
            + ": " + getEstadoConexion();
    }

    @Override
    public String cobrar(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            return "Rechazado: monto inválido.";
        }

        if (!isActiva()
                || !getEstadoConexion().equals("Conectada")) {
            return "Rechazado: pasarela no disponible.";
        }

        String operacion = UUID.randomUUID().toString();
        registrarEvento("Cobro simulado: " + operacion);

        return String.format(
            Locale.ROOT,
            "Simulación: cobro de Q%.2f. Operación: %s",
            monto,
            operacion
        );
    }

    @Override
    public String reintegrar(String idOperacion, double monto) {
        if (idOperacion == null || idOperacion.trim().isEmpty()) {
            return "Rechazado: falta la operación.";
        }

        if (!Double.isFinite(monto) || monto <= 0) {
            return "Rechazado: monto inválido.";
        }

        if (!isActiva()
                || !getEstadoConexion().equals("Conectada")) {
            return "Rechazado: pasarela no disponible.";
        }

        registrarEvento("Reintegro simulado: " + idOperacion);

        return String.format(
            Locale.ROOT,
            "Simulación: reintegro de Q%.2f para %s.",
            monto,
            idOperacion
        );
    }

    @Override
    public String toString() {
        return super.toString()
            + "\nModalidad: " + modalidad;
    }
}