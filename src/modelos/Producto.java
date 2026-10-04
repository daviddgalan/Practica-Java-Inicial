package modelos;

public abstract class Producto {
    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    private static int contador = 1;

    public Producto(String nombre, double precio, int stock) {
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | %.2f € | stock: %d", id, nombre, precio, stock);
    }

    public int getId() {
        return id;
    }

    public abstract String getTipo();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
