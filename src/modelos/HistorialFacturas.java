package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistorialFacturas {
    private List<Factura> facturas = new ArrayList<>();
    public void añadir(LocalDate fecha, Usuario usuario, Producto producto, int cantidad, double total){
        facturas.add(new Factura(fecha, usuario, producto, cantidad, total));
    }

    @Override
    public String toString() {
        return "HistorialFacturas{" +
                "facturas=" + facturas +
                '}';
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
