public class Descuento {
    private String tipo;
    private int porcentaje;

    public Descuento(String tipo, int porcentaje) {
        this.tipo = tipo;
        this.porcentaje = porcentaje;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }
}