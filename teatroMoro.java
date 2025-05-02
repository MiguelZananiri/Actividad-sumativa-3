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

    private List<ventaDeEntradas> listaEntradas = new ArrayList<>();
    private List<Descuento> listaDescuentos = new ArrayList<>();

    public teatroMoro() {
        inicializarDescuentos();
    }

    public void inicializarDescuentos() {
        listaDescuentos.add(new Descuento("tercera edad", 15));
        listaDescuentos.add(new Descuento("estudiante", 10));
    }

    public void venta() {
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        String seleccionarAsientoLetra, confirmarEdad;
        int seleccionarAsientoNumero, pagaBase = 9000, descuento = 0, pagaTotal = 0, id = 0;
        Set<String> letrasValidas = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        System.out.println("Bienvenido al teatro Moro");

        for (String letra : letrasValidas) {
            for (int numero = 1; numero <= 6; numero++) {
                System.out.print(letra + numero + " ");
            }
            System.out.println();
        }

        System.out.println("Seleccione su asiento por la letra: ");
        seleccionarAsientoLetra = scanner.nextLine().trim().toUpperCase();

        if (letrasValidas.contains(seleccionarAsientoLetra)) {
            System.out.println("Usted a elegido la letra " + seleccionarAsientoLetra);
        } else {
            System.out.println("Letra invalido");
        }

        System.out.println("Seleccione su asiento por el numero: ");
        seleccionarAsientoNumero = scanner.nextInt();

        if (seleccionarAsientoNumero >= 1 && seleccionarAsientoNumero <= 6) {
            System.out.println("Usted a elegido el numero " + seleccionarAsientoNumero);
        } else {
            System.out.println("Numero invalido");

        }

        System.out.println("Usted a elegido el asiento " + seleccionarAsientoLetra + seleccionarAsientoNumero);

        String asientoSeleccionado = seleccionarAsientoLetra + seleccionarAsientoNumero;

        System.out.println("eres estudiante o tercera edad");

        scanner.nextLine();
        confirmarEdad = scanner.nextLine().trim().toLowerCase();
        if (confirmarEdad.equals("tercera edad")) {
            System.out.println("eres de la tercera edad, se aplica un descuento del 15%");
            descuento = 15;
        } else if (confirmarEdad.equals("estudiante")) {
            System.out.println("eres estudiante, se aplica un descuento del 10%");
            descuento = 10;
        } else if (confirmarEdad.equals("publico general") || confirmarEdad.equals("ninguno")) {
            System.out.println("no eres estudiante ni tercera edad, no se aplica descuento");
        } else {
            System.out.println("opcion invalida");

        }

        Descuento descuentoAplicado = null;
        for (Descuento d : listaDescuentos) {
            if (d.getTipo().equalsIgnoreCase(confirmarEdad)) {
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
        id++;

        ventaDeEntradas nuevaEntrada = new ventaDeEntradas(asientoSeleccionado, confirmarEdad, pagaTotal, id);
        listaEntradas.add(nuevaEntrada);

    }

    public class ventaDeEntradas {
        String asiento, tipoPublico;
        int pagaTotal, id;

        public ventaDeEntradas(String asiento, String tipoPublico, int pagaTotal, int id) {
            this.asiento = asiento;
            this.tipoPublico = tipoPublico;
            this.pagaTotal = pagaTotal;
            this.id = id;
        }

        public void mostrarInfo() {
            System.out.println("Asiento: " + asiento);
            System.out.println("Tipo de publico: " + tipoPublico);
            System.out.println("Costo Total: " + pagaTotal);
            System.out.println("ID de venta: " + id);
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
        teatroMoro teatro = new teatroMoro();
        teatro.venta();
        if (!teatro.listaEntradas.isEmpty()) {
            ventaDeEntradas entrada = teatro.listaEntradas.get(0);
            entrada.mostrarInfo();
        }
    }
}
