//Precio base: 9.000
//tercera edad: 7.650 15% descuento
//estudiante: 8.100 10% descuento

import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class teatroMoro {

    static Scanner scanner = new Scanner(System.in);

    private static List<ventaDeEntradas> listaEntradas = new ArrayList<>();
    private static List<Descuento> listaDescuentos = new ArrayList<>();
    private static int idVenta = 0;
    private static Set<String> asientosOcupados = new HashSet<>();

    public teatroMoro() {
        inicializarDescuentos();
    }

    public void inicializarDescuentos() {
        listaDescuentos.add(new Descuento("tercera edad", 15));
        listaDescuentos.add(new Descuento("estudiante", 10));
    }

    public static ventaDeEntradas realizarVenta(){
        System.out.println("");

        String seleccionarAsientoLetra, confirmarEdad, confirmarPago;
        int seleccionarAsientoNumero, pagaBase = 9000, descuento = 0, pagaTotal = 0, idCliente = -1;
        int idReservaRNG = (int)(Math.random() * 1001);
        Set<String> letrasValidas = new HashSet<>(Arrays.asList("A", "B", "C", "D"));

        for (String letra : letrasValidas) {
            for (int numero = 1; numero <= 6; numero++) {
                String asiento = letra + numero;
                if (asientosOcupados.contains(asiento)) {
                    System.out.print("[XX] "); // ocupado
                } else {
                    System.out.print("[" + asiento + "] ");
                }
            }
            System.out.println();
        }

        System.out.println("Seleccione su asiento por la letra: ");
        scanner.nextLine();
        while (true) {
            seleccionarAsientoLetra = scanner.nextLine().trim().toUpperCase();

            if (letrasValidas.contains(seleccionarAsientoLetra)) {
                System.out.println("Usted a elegido la letra " + seleccionarAsientoLetra);
                System.out.println("");
                break;
            } else {
                System.out.println("Letra invalido");
                System.out.println("");
            }
        }

        System.out.println("Seleccione su asiento por el numero: ");
        while (true) {
            seleccionarAsientoNumero = scanner.nextInt();

            if (seleccionarAsientoNumero >= 1 && seleccionarAsientoNumero <= 6) {
                System.out.println("Usted a elegido el numero " + seleccionarAsientoNumero);
                System.out.println("");
                break;
            } else {
                System.out.println("Numero invalido");
                System.out.println("");
            }
        }


        System.out.println("Usted a elegido el asiento " + seleccionarAsientoLetra + seleccionarAsientoNumero);
        String asientoSeleccionado = seleccionarAsientoLetra + seleccionarAsientoNumero;

        if (asientosOcupados.contains(asientoSeleccionado)) {
            System.out.println("Ese asiento ya está ocupado. Intente otro.");
            return null;
        }

        scanner.nextLine();
        System.out.println("");
        System.out.println("Eres estudiante o tercera edad");

        while (true) {
            confirmarEdad = scanner.nextLine().trim().toLowerCase();
            if (confirmarEdad.equals("tercera edad")) {
                System.out.println("");
                System.out.println("Eres de la tercera edad");
                descuento = 15; 
                break;
            } else if (confirmarEdad.equals("estudiante")) {
                System.out.println("");
                System.out.println("Eres estudiante");
                descuento = 10;
                break;
            } else if (confirmarEdad.equals("publico general") || confirmarEdad.equals("ninguno")) {
                System.out.println("");
                System.out.println("No eres estudiante ni tercera edad");
                break;
            } else {
                System.out.println("");
                System.out.println("opcion invalida");
            }
        }


        Descuento descuentoAplicado = null;
        for (Descuento d : teatroMoro.listaDescuentos) {
            if (d.getTipo().equals(confirmarEdad)) {
                descuentoAplicado = d;
                break;
            }
        }

        if (descuentoAplicado != null) {
            descuento = descuentoAplicado.getPorcentaje();
            System.out.println("Se aplica un descuento del " + descuento + "%");
        } else {
            System.out.println("No se aplica descuento");
        }

        pagaTotal = pagaBase - (pagaBase * descuento / 100);

        System.out.println("");
        System.out.println("¿Desea confirmar el pago? SI/NO");

        while (true) {
            confirmarPago = scanner.nextLine().trim().toLowerCase();
            if (confirmarPago.equals("si")) {
                System.out.println("Introduzca su ID de cliente(rut)");
                while (true) {
                    idCliente = scanner.nextInt();
                    if (idCliente >= 10000000 && idCliente <= 999999999) {
                        System.out.println("ID de cliente confirmado");
                        System.out.println("Pago confirmado");
                        break;
                    }
                    else {
                        System.out.println("ID de cliente invalido");
                    }
                }
                break;
            } else if (confirmarPago.equals("no")) {
                System.out.println("Pago cancelado");
            }
            else {
                System.out.println("Confirme con un si o no");
            }
        }

        idVenta++;
        asientosOcupados.add(asientoSeleccionado);

        ventaDeEntradas nuevaEntrada = new ventaDeEntradas(asientoSeleccionado, confirmarEdad, pagaTotal, idReservaRNG, idVenta, idCliente);
        listaEntradas.add(nuevaEntrada);
        return nuevaEntrada;
    }

    public void eliminarVenta() {
        System.out.println("Ingrese el ID de venta a eliminar:");
        int idEliminar = scanner.nextInt();
        boolean eliminado = false;
    
        for (int i = 0; i < listaEntradas.size(); i++) {
            if (listaEntradas.get(i).idVenta == idEliminar) {
                asientosOcupados.remove(listaEntradas.get(i).asiento);
                listaEntradas.remove(i);
                System.out.println("Venta eliminada.");
                eliminado = true;
                break;
            }
        }
    
        if (!eliminado) {
            System.out.println("ID de venta no encontrado.");
        }
    }

    public static class ventaDeEntradas {
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

    public static void main(String[] args) {
        new teatroMoro().inicializarDescuentos();
        int menu;

        System.out.println("");
        System.out.println("Bienvenido al teatro Moro");
        do{
            System.out.println("1- Realizar venta");
            System.out.println("2- Eliminar venta");
            System.out.println("3- Visualizar resumen de entradas");
            System.out.println("3- Salir");
    
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                teatroMoro.realizarVenta();
                if (!teatroMoro.listaEntradas.isEmpty()) {
                    ventaDeEntradas entrada = teatroMoro.listaEntradas.get(teatroMoro.listaEntradas.size() - 1);
                    System.out.println("");
                    entrada.mostrarInfo();
                    System.out.println("");
                    break;
                }
                case 2:
                for (ventaDeEntradas entrada : listaEntradas) {
                    System.out.println("");
                    entrada.mostrarInfo();
                new teatroMoro().eliminarVenta();
                    break;
                }
                
                case 3:
                    break;
            
                default:
                    System.out.println("Opcion invalida");
                    break;
            } 
        } while (menu != 3);
    }
}