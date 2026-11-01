public interface Transaccional {
    String cobrar(double monto);
    String reintegrar(String idOperacion, double monto);
}