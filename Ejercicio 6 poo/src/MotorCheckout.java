public class MotorCheckout {

    public String cobrar(Transaccional servicio, double monto) {
        return servicio.cobrar(monto);
    }

    public String reintegrarSiCompatible(
            IntegracionAPI api,
            String idOperacion,
            double monto) {

        if (api instanceof Transaccional) {
            return reintegrar(
                (Transaccional) api,
                idOperacion,
                monto
            );
        }

        if (api != null) {
            auditar(
                api,
                "Reintegro ignorado: capacidad no disponible"
            );
        }

        return "Ignorado: el servicio no admite reintegros. "
            + "El proceso puede continuar.";
    }

    public String reintegrar(
            Transaccional servicio,
            String idOperacion,
            double monto) {

        return servicio.reintegrar(idOperacion, monto);
    }

    public String generarDocumento(
            Documentable servicio,
            String datos) {

        return servicio.generarDocumento(datos);
    }

    public void auditar(Auditable servicio, String evento) {
        servicio.registrarEvento(evento);
    }
}