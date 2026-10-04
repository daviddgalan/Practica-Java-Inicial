package modelos;

import java.time.LocalDate;

public class Factura {
    private int id;
    private LocalDate fecha;
    private Usuario usuario;
    private Producto producto;
    private int cantidad;
    private double total;

    private static int contador = 1;

    public Factura(LocalDate fecha, Usuario usuario, Producto producto, int cantidad, double total) {
        this.id = contador++;
        this.fecha = fecha;
        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
