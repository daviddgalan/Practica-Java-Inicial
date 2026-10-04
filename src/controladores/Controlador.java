package controladores;

import gestor.GestorProducto;
import gestor.GestorUsuarios;
import modelos.*;
import vistas.Vista;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Controlador {
    Scanner s = new Scanner(System.in);
    private Carrito carrito = new Carrito();
    private HistorialFacturas historialFacturas = new HistorialFacturas();
    private Vista vista = new Vista();
    private GestorProducto gestorProducto = new GestorProducto();
    private GestorUsuarios gestorUsuarios = new GestorUsuarios();

    public void empezar(){
        int opcion;
        do{
            vista.menuPrincipal();
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> gestionarProducto();
                case 2 -> gestionarUsuarios();
                case 3 -> gestionarCarrito();
                case 4 -> cerrarPedido();
                case 5 -> verHistorial();
                case 6 -> exportarHistorial();
            }
        }while (opcion != 0);
    }

    public void exportarHistorial() {
        List<Factura> facturas = historialFacturas.getFacturas();
        if (facturas.isEmpty()) {
            vista.mostrarMensaje("No hay pedidos que exportar.");
            return;
        }
        try {
            ExportadorCSV.exportar(facturas, "historial_pedidos.csv");
            vista.mostrarMensaje("Historial exportado a historial_pedidos.csv");
        } catch (IOException e) {
            vista.mostrarMensaje("No se pudo guardar el fichero: " + e.getMessage());
        }
    }

    public void verHistorial(){
        vista.mostrarHistorial(historialFacturas.getFacturas());
    }

    public void cerrarPedido() {
        if (carrito.estaVacio()) {
            vista.mostrarMensaje("El carrito está vacío.");
            return;
        }

        vista.pedirIdUsuario();
        int idUsuario = leerEntero();
        Usuario usuario = gestorUsuarios.buscarId(idUsuario);

        if (usuario == null) {
            vista.mostrarMensaje("Ese usuario no existe.");
            return;
        }
        if (!usuario.getEstado()) {
            vista.mostrarMensaje("El usuario está inactivo.");
            return;
        }

        Map<Producto, Integer> items = carrito.getCarrito();
        double total = carrito.getTotal();
        LocalDate fecha = LocalDate.now();

        for (Map.Entry<Producto, Integer> e : items.entrySet()) {
            Producto p = e.getKey();
            int cantidad = e.getValue();

            double totalLinea = p.getPrecio() * cantidad;
            historialFacturas.añadir(fecha, usuario, p, cantidad, totalLinea);
            p.setStock(p.getStock() - cantidad);
        }

        vista.mostrarFactura(fecha, usuario, items, total);
        carrito.vaciar();
    }

    public void gestionarCarrito(){
        int opcion;
        do {
            vista.menuCarrito();
            opcion = leerEntero();
            switch (opcion){
                case 1 -> añadirCarrito();
                case 2 -> eliminarCarrito();
                case 3 -> verCarrito();
            }
        }while(opcion!=0);
    }

    public void verCarrito() {
        vista.mostrarCarrito(carrito.getCarrito());
    }

    public void eliminarCarrito(){
        vista.pedirId();
        int idProducto = leerEntero();

        Producto producto = gestorProducto.buscarId(idProducto);
        carrito.eliminar(producto);
    }

    public void añadirCarrito(){
        vista.pedirId();
        int idProducto = leerEntero();
        vista.pedirCantidad();
        int cant = leerEntero();

        Producto producto = gestorProducto.buscarId(idProducto);
        if (producto == null) {
            vista.mostrarMensaje("Ese producto no existe.");
        } else if (!gestorProducto.revisarCantidadProductos(idProducto, cant)) {
            vista.mostrarMensaje("No hay stock suficiente.");
        } else {
            carrito.añadir(producto, cant);
            vista.mostrarMensaje("Producto añadido al carrito.");
        }
    }

    public void gestionarUsuarios(){
        int opcion;
        do{
            vista.menuUsuarios();
            opcion = leerEntero();
            switch (opcion) {
                case 1-> añadirUsuario();
                case 2-> eliminarUsuario();
                case 3-> listarUsuario();
            }
        }while (opcion!=0);
    }

    public void añadirUsuario(){
        vista.pedirNombre();
        String nombre = leerTexto();
        vista.pedirEmail();
        String email = leerTexto();
        vista.pedirEstado();
        boolean estado = leerTexto().equalsIgnoreCase("x");
        gestorUsuarios.añadir(new Usuario(nombre,email,estado));
    }

    public void eliminarUsuario(){
        vista.pedirId();
        int id = leerEntero();
        gestorUsuarios.eliminar(id);
    }

    public void listarUsuario(){
        vista.mostrarUsuarios(gestorUsuarios.getUsuarios());
    }

    public void gestionarProducto(){
        int opcion;
        do {
            vista.menuProductos();
            opcion = leerEntero();
            switch (opcion){
                case 1-> anadirProducto();
                case 2-> bajarProducto();
                case 3-> listarProductos();
                case 4-> buscarProductoCategoria();
            }
        }while (opcion!=0);
    }

    public void anadirProducto(){
        vista.pedirNombre();
        String nombre = leerTexto();
        vista.pedirPrecio();
        double precio = leerDecimal();
        vista.pedirStock();
        int stock = leerEntero();
        vista.fisicoDigital();
        String opcion = leerTexto();
        switch (opcion.toLowerCase()){
            case "x" -> {
                vista.pedirLicencia();
                int licencia = leerEntero();
                vista.pedirTamanoMB();
                double tamanoMB = leerDecimal();
                gestorProducto.añadir(new ProductoDigital(nombre,precio,stock,tamanoMB,licencia));
            }
            case "z" ->{
                vista.pedirPeso();
                double peso = leerDecimal();
                vista.pedirGastosEnvio();
                double gastosEnvio = leerDecimal();
                gestorProducto.añadir(new ProductoFisico(nombre,precio,stock,peso,gastosEnvio));
            }
        }
    }

    public void bajarProducto(){
        vista.pedirId();
        int id = leerEntero();
        gestorProducto.eliminar(id);
    }

    public void listarProductos(){
        vista.mostrarProductos(gestorProducto.getProductos());
    }
    public void buscarProductoCategoria(){
        vista.fisicoDigital();
        String opcion = leerTexto();
        if (opcion.toLowerCase()=="X") vista.mostrarProductosDigitales(gestorProducto.getProductos());
        else vista.mostrarProductosFisicos(gestorProducto.getProductos());
    }

    public void mostrarError(String mensaje){
        System.out.println("⚠ " + mensaje);
    }

    private String leerTexto() {
        return s.nextLine().trim();
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(leerTexto());
            } catch (NumberFormatException e) {
                mostrarError("Introduce un número entero válido.");
            }
        }
    }

    private double leerDecimal() {
        while (true) {
            try {
                return Double.parseDouble(leerTexto().replace(",", "."));
            } catch (NumberFormatException e) {
                mostrarError("Introduce un número válido.");
            }
        }
    }
}
