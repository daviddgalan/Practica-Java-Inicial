package modelos;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private Map<Producto, Integer> carrito = new HashMap<>();

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> e : carrito.entrySet()) {
            total += e.getKey().getPrecio() * e.getValue();
        }
        return total;
    }
    public void vaciar() {
        carrito.clear();
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "carrito=" + carrito +
                '}';
    }

    public void añadir(Producto producto, int cant) {
        carrito.put(producto,cant);
    }
    public void eliminar(Producto producto){
        carrito.remove(producto);
    }

    public boolean estaVacio(){
        return carrito.isEmpty();
    }

    public Map<Producto, Integer> getCarrito() {
        return carrito;
    }

}
