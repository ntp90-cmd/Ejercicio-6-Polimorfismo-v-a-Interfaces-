public class Main {
    public static void main(String[] args) {
        CatalogoIntegraciones catalogo =
            new CatalogoIntegraciones();

        MotorCheckout motor = new MotorCheckout();
        VistaConsola vista = new VistaConsola();

        ControladorIntegraciones controlador =
            new ControladorIntegraciones(
                catalogo,
                motor,
                vista
            );

        controlador.iniciar();
    }
}