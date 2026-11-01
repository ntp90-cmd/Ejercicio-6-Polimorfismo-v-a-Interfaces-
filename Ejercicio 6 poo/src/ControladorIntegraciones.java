import java.util.Locale;
import java.util.NoSuchElementException;

public class ControladorIntegraciones {

    private final CatalogoIntegraciones catalogo;
    private final MotorCheckout motor;
    private final VistaConsola vista;

    public ControladorIntegraciones(
            CatalogoIntegraciones catalogo,
            MotorCheckout motor,
            VistaConsola vista) {

        this.catalogo = catalogo;
        this.motor = motor;
        this.vista = vista;
    }

    public void iniciar() {
        catalogo.cargarIniciales();

        vista.mostrarMensaje(
            "Hola, bienvenido al gestor de integraciones."
        );

        vista.mostrarMensaje(
            "Todas las operaciones son simuladas."
        );

        vista.mostrarMensaje(
            "Integraciones cargadas: " + catalogo.cantidad()
        );

        boolean continuar = true;

        try {
            while (continuar) {
                vista.mostrarMenu();

                switch (vista.leerOpcion()) {
                    case 1:
                        listarActivas();
                        break;
                    case 2:
                        buscarPorId();
                        break;
                    case 3:
                        ordenarPorLatencia();
                        break;
                    case 4:
                        simularCobro();
                        break;
                    case 5:
                        simularReintegro();
                        break;
                    case 6:
                        generarDocumento();
                        break;
                    case 7:
                        consultarEstado();
                        break;
                    case 8:
                        buscarPorConfiguracion();
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        vista.mostrarMensaje(
                            "Opción fuera del menú."
                        );
                }
            }
        } catch (NoSuchElementException e) {
            vista.mostrarMensaje("Entrada finalizada.");
        }

        vista.mostrarMensaje("Programa finalizado.");
    }

    public void listarActivas() {
        int cantidad = 0;

        for (int i = 0; i < catalogo.cantidad(); i++) {
            IntegracionAPI api = catalogo.obtener(i);

            if (api.isActiva()) {
                vista.mostrarIntegracion(api);
                cantidad++;
            }
        }

        vista.mostrarMensaje(
            "Integraciones activas: " + cantidad
        );
    }

    public void buscarPorId() {
        IntegracionAPI api = seleccionarAPI();

        if (api != null) {
            vista.mostrarIntegracion(api);
        }
    }

    public void ordenarPorLatencia() {
        catalogo.ordenarPorLatencia();

        vista.mostrarMensaje(
            "Catálogo ordenado de menor a mayor latencia:"
        );

        for (int i = 0; i < catalogo.cantidad(); i++) {
            vista.mostrarIntegracion(
                catalogo.obtener(i)
            );
        }
    }

    private IntegracionAPI seleccionarAPI() {
        String id = vista.leerTexto(
            "Identificador (P01-P04, L01-L03, F01-F03): "
        ).toUpperCase(Locale.ROOT);

        IntegracionAPI api = catalogo.buscar(id);

        if (api == null) {
            vista.mostrarMensaje(
                "No existe una integración con ese identificador."
            );
        }

        return api;
    }

    private double leerMonto() {
        while (true) {
            try {
                String texto = vista.leerTexto(
                    "Monto en Q (ejemplo 150.50): "
                );

                double monto = Double.parseDouble(
                    texto.replace(',', '.')
                );

                if (Double.isFinite(monto) && monto > 0) {
                    return monto;
                }

                vista.mostrarMensaje(
                    "El monto debe ser finito y mayor que cero."
                );
            } catch (NumberFormatException e) {
                vista.mostrarMensaje(
                    "Ingresa un monto numérico válido."
                );
            }
        }
    }

    private void simularCobro() {
        IntegracionAPI api = seleccionarAPI();

        if (api == null) {
            return;
        }

        if (api instanceof Transaccional) {
            vista.mostrarMensaje(
                motor.cobrar(
                    (Transaccional) api,
                    leerMonto()
                )
            );
        } else {
            vista.mostrarMensaje(
                "Ignorado: el servicio no admite cobros."
            );
        }
    }

    private void simularReintegro() {
        IntegracionAPI api = seleccionarAPI();

        if (api == null) {
            return;
        }

        String operacion = vista.leerTexto(
            "Identificador de la operación: "
        );

        vista.mostrarMensaje(
            motor.reintegrarSiCompatible(
                api,
                operacion,
                leerMonto()
            )
        );
    }

    private void generarDocumento() {
        IntegracionAPI api = seleccionarAPI();

        if (api == null) {
            return;
        }

        if (api instanceof Documentable) {
            String datos = vista.leerTexto(
                "Datos del documento: "
            );

            vista.mostrarMensaje(
                motor.generarDocumento(
                    (Documentable) api,
                    datos
                )
            );
        } else {
            vista.mostrarMensaje(
                "Ignorado: el servicio no genera documentos."
            );
        }
    }

    private void consultarEstado() {
        IntegracionAPI api = seleccionarAPI();

        if (api != null) {
            vista.mostrarMensaje(
                api.consultarEstado()
            );
        }
    }

    private void buscarPorConfiguracion() {
        String nombre = vista.leerTexto("Nombre: ");
        String endpoint = vista.leerTexto("Endpoint: ");

        IntegracionAPI api = catalogo.buscar(
            nombre,
            endpoint
        );

        if (api == null) {
            vista.mostrarMensaje(
                "No se encontró esa configuración."
            );
        } else {
            vista.mostrarIntegracion(api);
        }
    }
}