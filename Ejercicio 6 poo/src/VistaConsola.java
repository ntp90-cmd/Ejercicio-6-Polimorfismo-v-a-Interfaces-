import java.util.Scanner;


public class VistaConsola {

    private final Scanner entrada = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\nGestor de integraciones");
        System.out.println("1. Listar integraciones activas");
        System.out.println("2. Buscar por identificador");
        System.out.println("3. Ordenar catálogo por latencia");
        System.out.println("4. Simular cobro");
        System.out.println("5. Simular reintegro");
        System.out.println("6. Generar documento de ejemplo");
        System.out.println("7. Consultar estado de una integración");
        System.out.println("8. Buscar por nombre y endpoint");
        System.out.println("0. Salir");
    }

    public int leerOpcion() {
        while (true) {
            try {
                return Integer.parseInt(
                    leerTexto("Opción: ")
                );
            } catch (NumberFormatException e) {
                mostrarMensaje(
                    "Escribe un número entero del menú."
                );
            }
        }
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);

        if (!entrada.hasNextLine()) {
            throw new java.util.NoSuchElementException(
                "Fin de entrada"
            );
        }

        return entrada.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarIntegracion(IntegracionAPI api) {
        System.out.println("--------------------------------");
        System.out.println(api);
    }
}