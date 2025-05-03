public class ventaDeEntradas {
    String asiento, tipoPublico;
    int pagaTotal, idReserva, idCliente, idVenta;

    public ventaDeEntradas(String asiento, String tipoPublico, int pagaTotal, int idReserva, int idVenta, int idCliente) {
        this.asiento = asiento;
        this.tipoPublico = tipoPublico;
        this.pagaTotal = pagaTotal;
        this.idReserva = idReserva;
        this.idVenta = idVenta;
        this.idCliente = idCliente;
    }

    public void mostrarInfo() {
        System.out.println("Asiento: " + asiento);
        System.out.println("Tipo de publico: " + tipoPublico);
        System.out.println("Costo Total: " + pagaTotal);
        System.out.println("ID de venta: " + idVenta);
        System.out.println("ID de reserva: " + idReserva);
        System.out.println("ID de cliente: " + idCliente);
    }

}