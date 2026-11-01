public class CertificadorFEL
        extends IntegracionAPI implements Documentable {

    private final String identificadorCertificador;

    public CertificadorFEL(
            String id, String nombre, String endpoint,
            boolean activa, String estado, double latencia,
            String identificadorCertificador) {

        super(id, nombre, endpoint, activa, estado, latencia);
        this.identificadorCertificador = textoValido(
            identificadorCertificador, "Certificador"
        );
    }

    public String getIdentificadorCertificador() {
        return identificadorCertificador;
    }

    @Override
    public String consultarEstado() {
        return "Certificador " + getNombre()
            + ": " + getEstadoConexion();
    }

    @Override
    public String generarDocumento(String datos) {
        if (datos == null || datos.trim().isEmpty()) {
            return "Rechazado: faltan los datos.";
        }

        if (!isActiva()
                || !getEstadoConexion().equals("Conectada")) {
            return "Rechazado: certificador no disponible.";
        }

        registrarEvento("Documento simulado generado");

        return "<factura simulada=\"true\">"
            + "<certificador>"
            + escaparXml(identificadorCertificador)
            + "</certificador>"
            + "<datos>" + escaparXml(datos) + "</datos>"
            + "</factura>";
    }

    private String escaparXml(String texto) {
        return texto.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;");
    }

    @Override
    public String toString() {
        return super.toString()
            + "\nCertificador: " + identificadorCertificador;
    }
}