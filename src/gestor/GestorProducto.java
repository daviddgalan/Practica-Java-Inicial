package gestor;

import modelos.Producto;

import java.util.ArrayList;
import java.util.List;

public class GestorProducto {
    private List<Producto> productos = new ArrayList<>();

    public void añadir(Producto p) {
        productos.add(p);
    }

    public void eliminar(int id) {
        productos.removeIf(p -> p.getId() == id);
    }
    public Producto buscarId(int id){
        for (Producto p: productos) {
            if(p.getId()==id) return p;
        }
        return null;
    }
    public boolean revisarCantidadProductos(int id, int cantidad){
        Producto producto = buscarId(id);
        if (producto.getStock()>=cantidad) return true;
        return false;
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

}
