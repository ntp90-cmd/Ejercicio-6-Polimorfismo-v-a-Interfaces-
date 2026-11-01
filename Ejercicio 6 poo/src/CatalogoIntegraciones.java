import java.util.ArrayList;
import java.util.Collections;

public class CatalogoIntegraciones {

    private final ArrayList<IntegracionAPI> integraciones =
        new ArrayList<>();

    public void cargarIniciales() {
        agregar(new PasarelaPago(
            "P01", "PagoTarjeta",
            "https://tarjeta.example/api",
            true, "Conectada", 120,
            "Tarjeta de crédito"
        ));

        agregar(new PasarelaPago(
            "P02", "PagoBanco",
            "https://banco.example/api",
            true, "Conectada", 230,
            "Transferencia bancaria"
        ));

        agregar(new PasarelaPago(
            "P03", "PagoWallet",
            "https://wallet.example/api",
            true, "Conectada", 85,
            "Billetera digital"
        ));

        agregar(new PasarelaPago(
            "P04", "PagoAlterno",
            "https://alterno.example/api",
            true, "Desconectada", 350,
            "Tarjeta de crédito"
        ));

        agregar(new ProveedorLogistico(
            "L01", "EnvioLocal",
            "https://local.example/api",
            true, "Conectada", 95,
            "Última milla"
        ));

        agregar(new ProveedorLogistico(
            "L02", "EnvioNacional",
            "https://nacional.example/api",
            true, "Conectada", 180,
            "Nacional"
        ));

        agregar(new ProveedorLogistico(
            "L03", "EnvioExterior",
            "https://exterior.example/api",
            false, "Desconectada", 420,
            "Internacional"
        ));

        agregar(new CertificadorFEL(
            "F01", "FacturaUno",
            "https://feluno.example/api",
            true, "Conectada", 110,
            "Cert-001"
        ));

        agregar(new CertificadorFEL(
            "F02", "FacturaDos",
            "https://feldos.example/api",
            true, "Conectada", 160,
            "Cert-002"
        ));

        agregar(new CertificadorFEL(
            "F03", "FacturaTres",
            "https://feltres.example/api",
            true, "Conectada", 75,
            "Cert-003"
        ));
    }

    public boolean agregar(IntegracionAPI api) {
        if (api == null || integraciones.contains(api)) {
            return false;
        }

        return integraciones.add(api);
    }

    public IntegracionAPI buscar(String id) {
        if (id == null) {
            return null;
        }

        for (IntegracionAPI api : integraciones) {
            if (api.getId().equals(id.trim())) {
                return api;
            }
        }

        return null;
    }

    public IntegracionAPI buscar(String nombre, String endpoint) {
        if (nombre == null || endpoint == null) {
            return null;
        }

        for (IntegracionAPI api : integraciones) {
            if (api.getNombre().equalsIgnoreCase(nombre.trim())
                    && api.getEndpoint().equals(endpoint.trim())) {
                return api;
            }
        }

        return null;
    }

    public void ordenarPorLatencia() {
        Collections.sort(integraciones);
    }

    public int cantidad() {
        return integraciones.size();
    }

    public IntegracionAPI obtener(int indice) {
        return integraciones.get(indice);
    }
}