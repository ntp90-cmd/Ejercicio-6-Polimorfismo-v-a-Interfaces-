public class ProveedorLogistico
        extends IntegracionAPI implements Documentable {

    private final String tipoServicio;

    public ProveedorLogistico(
            String id, String nombre, String endpoint,
            boolean activa, String estado, double latencia,
            String tipoServicio) {

        super(id, nombre, endpoint, activa, estado, latencia);
        this.tipoServicio = textoValido(
            tipoServicio, "Tipo de servicio"
        );
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    @Override
    public String consultarEstado() {
        return "Logística " + getNombre()
            + ": " + getEstadoConexion();
    }

    @Override
    public String generarDocumento(String datos) {
        if (datos == null || datos.trim().isEmpty()) {
            return "Rechazado: faltan los datos.";
        }

        if (!isActiva()
                || !getEstadoConexion().equals("Conectada")) {
            return "Rechazado: proveedor no disponible.";
        }

        registrarEvento("Guía simulada generada");

        return "Guía de envío simulada"
            + "\nProveedor: " + getNombre()
            + "\nServicio: " + tipoServicio
            + "\nDatos: " + datos;
    }

    @Override
    public String toString() {
        return super.toString()
            + "\nTipo de servicio: " + tipoServicio;
    }
}