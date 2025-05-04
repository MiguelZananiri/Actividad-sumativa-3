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

    public static void mostrarAsientos() {
        Set<String> letrasValidas = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        for (String letra : letrasValidas) {
            for (int numero = 1; numero <= 6; numero++) {
                String asiento = letra + numero;
                if (asientosOcupados.contains(asiento)) {
                    System.out.print("[XX] ");
                } else {
                    System.out.print("[" + asiento + "] ");
                }
            }
            System.out.println("");
        }
    }

    public static ventaDeEntradas realizarVenta() {
        System.out.println("");

        String seleccionarAsientoLetra, confirmarEdad;
        int seleccionarAsientoNumero, pagaBase = 9000, descuento = 0, pagaTotal = 0, idCliente = -1;
        int idReservaRNG = (int) (Math.random() * 1001);
        Set<String> letrasValidas = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        mostrarAsientos();

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
        System.out.println("Ingrese su ID de cliente: ");

        while (true) {
            idCliente = scanner.nextInt();
        
            if (idCliente < 10000000 || idCliente > 999999999) {
                System.out.println("ID de cliente inválido. Intente nuevamente.");
                continue;
            }
        
            boolean idYaRegistrado = false;
            for (ventaDeEntradas entrada : listaEntradas) {
                if (entrada.idCliente == idCliente) {
                    idYaRegistrado = true;
                    break;
                }
            }
        
            if (idYaRegistrado) {
                System.out.println("Este ID ya tiene una venta registrada. No puede registrar otra.");
            } else {
                System.out.println("ID de cliente confirmado.");
                break;
            }
        }

        System.out.println("Venta registrada.");

        idVenta++;
        asientosOcupados.add(asientoSeleccionado);

        ventaDeEntradas nuevaEntrada = new ventaDeEntradas(asientoSeleccionado, confirmarEdad, pagaTotal, idReservaRNG,
                idVenta, idCliente);
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

    public void actualizarVenta() {
        System.out.println("Ingrese el ID de venta a actualizar:");
        int idActualizar, seleccionarAsientoNumero = 0, descuento = 0, pagaBase = 9000;
        String seleccionarAsientoLetra, confirmarEdad;
        Set<String> letrasValidas = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        scanner.nextLine();

        idActualizar = scanner.nextInt();
        scanner.nextLine();

        ventaDeEntradas ventaAActualizar = null;

        for (ventaDeEntradas venta : listaEntradas) {
            if (venta.idVenta == idActualizar) {
                ventaAActualizar = venta;
                break;
            }
        }

        if (ventaAActualizar == null) {
            System.out.println("ID de venta no encontrado.");
            return;
        }

        System.out.println("Seleccione una opción:");
        System.out.println("1- Cambiar asiento");
        System.out.println("2- Cambiar tipo de público");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            mostrarAsientos();

            String nuevoAsiento;
            while (true) {
                System.out.println("Seleccione su asiento por la letra:");
                seleccionarAsientoLetra = scanner.nextLine().trim().toUpperCase();

                if (!letrasValidas.contains(seleccionarAsientoLetra)) {
                    System.out.println("Letra inválida. Intente de nuevo.");
                    continue;
                }

                System.out.println("Seleccione su asiento por el número:");
                try {
                    seleccionarAsientoNumero = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                }

                if (seleccionarAsientoNumero < 1 || seleccionarAsientoNumero > 6) {
                    System.out.println("Número fuera de rango. Intente de nuevo.");
                    continue;
                }

                nuevoAsiento = seleccionarAsientoLetra + seleccionarAsientoNumero;

                if (asientosOcupados.contains(nuevoAsiento)) {
                    System.out.println("Ese asiento ya está ocupado. Intente otro.");
                } else {
                    break;
                }
            }

            asientosOcupados.remove(ventaAActualizar.asiento);
            asientosOcupados.add(nuevoAsiento);
            ventaAActualizar.asiento = nuevoAsiento;

            System.out.println("Asiento actualizado exitosamente.");
        } else if (opcion == 2) {
            while (true) {
                System.out.println("Ingrese el nuevo tipo de público:");
                System.out.println("Estudiante o tercera edad");
                scanner.nextLine();
                confirmarEdad = scanner.nextLine().trim().toLowerCase();
                if (confirmarEdad.equals("estudiante")) {
                    System.out.println("Eres estudiante");
                    descuento = 10;
                } else if (confirmarEdad.equals("tercera edad")) {
                    System.out.println("Eres tercera edad");
                    descuento = 15;
                } else if (confirmarEdad.equals("publico general") || confirmarEdad.equals("ninguno")) {
                    System.out.println("Eres publico general");
                } else {
                    System.out.println("Opcion no valida");
                    continue;
                }
                ventaAActualizar.pagaTotal = pagaBase - ((pagaBase * descuento) / 100);
                ventaAActualizar.tipoPublico = confirmarEdad;
                System.out.println("Tipo de público actualizado exitosamente.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new teatroMoro().inicializarDescuentos();
        int menu;
        teatroMoro teatro = new teatroMoro();

        System.out.println("");
        System.out.println("Bienvenido al teatro Moro");
        do {
            System.out.println("1- Realizar venta");
            System.out.println("2- Eliminar venta");
            System.out.println("3- Actualizar venta");
            System.out.println("4- Salir");

            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    teatroMoro.realizarVenta();
                    if (!teatroMoro.listaEntradas.isEmpty()) {
                        ventaDeEntradas entrada = teatroMoro.listaEntradas.get(teatroMoro.listaEntradas.size() - 1);
                        System.out.println("");
                        entrada.mostrarInfo();
                        System.out.println("");
                    }
                    break;
                case 2:
                    for (ventaDeEntradas entrada : listaEntradas) {
                        System.out.println("");
                        entrada.mostrarInfo();
                    }
                    teatro.eliminarVenta();
                    break;

                case 3:
                    for (ventaDeEntradas entrada : listaEntradas) {
                        System.out.println("");
                        entrada.mostrarInfo();
                    }
                    teatro.actualizarVenta();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (menu != 4);
    }
}