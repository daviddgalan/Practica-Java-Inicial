package vistas;

import modelos.Factura;
import modelos.Producto;
import modelos.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Vista {

    public void menuPrincipal(){
        System.out.println("=======================================");
        System.out.println("\n===== LA MEJOR TIENDA DEL MUNDO =====");
        System.out.println("====================================D.G=");
        System.out.println("1. Gestionar productos");
        System.out.println("2. Gestionar usuarios");
        System.out.println("3. Gestionar carrito");
        System.out.println("4. Cerrar pedido / generar factura");
        System.out.println("5. Ver historial de pedidos");
        System.out.println("0. Salir");
        System.out.println("Elige");
    }

    public void menuProductos() {
        System.out.println("\n--- PRODUCTOS ---");
        System.out.println("1. Alta");
        System.out.println("2. Baja");
        System.out.println("3. Listado");
        System.out.println("4. Buscar por categoría");
        System.out.println("0. Volver");
        System.out.println("Elige");
    }

    public void menuUsuarios() {
        System.out.println("\n--- USUARIOS ---");
        System.out.println("1. Alta");
        System.out.println("2. Baja");
        System.out.println("3. Listado de activos");
        System.out.println("0. Volver");
        System.out.println("Elige");    }

    public void menuCarrito() {
        System.out.println("\n--- CARRITO ---");
        System.out.println("1. Añadir producto");
        System.out.println("2. Quitar producto");
        System.out.println("3. Ver carrito y total");
        System.out.println("0. Volver");
        System.out.println("Elige");
    }

    public void mostrarUsuarios(List<Usuario> usuarios) {
        System.out.println("\n--- LOS MEJORES USUARIOS DEL MUNDO ---");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios.");
        }else {
            for (Usuario u : usuarios) if (u.getEstado()) System.out.println(u);
        }
    }

    public void mostrarProductos(List<Producto> productos) {
        System.out.println("\n--- LOS MEJORES PRODUCTOS DEL MUNDO ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos.");
        }else {
            for (Producto p : productos) System.out.println(p);
        }
    }
    public void mostrarProductosDigitales(List<Producto> productos) {
        System.out.println("\n--- LOS MEJORES PRODUCTOS DIGITALES DEL MUNDO ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos.");
        }else {
            for (Producto p : productos) if(p.getTipo()=="Digital") System.out.println(p);
        }
    }
    public void mostrarProductosFisicos(List<Producto> productos) {
        System.out.println("\n--- LOS MEJORES PRODUCTOS Físicos DEL MUNDO ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos.");
        }else {
            for (Producto p : productos) if(p.getTipo()=="Físico") System.out.println(p);
        }
    }

    public void mostrarCarrito(Map<Producto, Integer> carrito) {
        System.out.println("\n--- TU CARRITO ---");
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            for (Map.Entry<Producto, Integer> e : carrito.entrySet()) {
                Producto p = e.getKey();
                int cantidad = e.getValue();
                System.out.printf("[%d] %s x%d = %.2f €%n",
                        p.getId(), p.getNombre(), cantidad,
                        p.getPrecio() * cantidad);
            }
        }
    }
    public void mostrarFactura(LocalDate fecha, Usuario usuario,
                               Map<Producto, Integer> items, double total) {
        System.out.println("\n========== FACTURA ==========");
        System.out.println("Fecha: " + fecha);
        System.out.println("Cliente: " + usuario.getNombre());
        System.out.println("-----------------------------");
        for (Map.Entry<Producto, Integer> e : items.entrySet()) {
            Producto p = e.getKey();
            int cantidad = e.getValue();
            System.out.printf("%s x%d = %.2f €%n",
                    p.getNombre(), cantidad, p.getPrecio() * cantidad);
        }
        System.out.println("-----------------------------");
        System.out.printf("TOTAL: %.2f €%n", total);
    }

    public void mostrarHistorial(List<Factura> facturas) {
        System.out.println("\n--- HISTORIAL DE PEDIDOS ---");
        if (facturas.isEmpty()) {
            System.out.println("Aún no hay pedidos.");
        } else {
            double suma = 0;
            for (Factura f : facturas) {
                System.out.printf("%s | %s | %s x%d = %.2f €%n",
                        f.getFecha(), f.getUsuario().getNombre(), f.getProducto().getNombre(), f.getCantidad(), f.getTotal());
                suma += f.getTotal();
            }
            System.out.println("-----------------------------");
            System.out.printf("TOTAL VENDIDO: %.2f €%n", suma);
        }
    }

    public void pedirNombre() {
        System.out.println("Nombre: ");
    }
    public void pedirCategoria()   { System.out.println("Categoría: "); }
    public void pedirEmail()       { System.out.println("Email: "); }
    public void pedirPrecio()      { System.out.println("Precio: "); }
    public void pedirStock()       { System.out.println("Stock: "); }
    public void pedirPeso()        { System.out.println("Peso (kg): "); }
    public void pedirGastosEnvio() { System.out.println("Gastos de envío: "); }
    public void pedirTamanoMB()    { System.out.println("Tamaño de descarga (MB): "); }
    public void pedirLicencia()    { System.out.println("Licencia: "); }
    public void fisicoDigital(){
        System.out.println("(X) Producto Digital");
        System.out.println("(Z) Producto Físico");
    }
    public void pedirId(){
        System.out.println("Dime el id del producto.");
    }
    public void pedirIdUsuario(){
        System.out.println("Dime el id del Usuario.");
    }
    public void pedirEstado(){
        System.out.println("Activo (X) Inactivo (Z)");
    }
    public void pedirCantidad(){
        System.out.println("Dime la cantidad de Productos");
    }
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

}
