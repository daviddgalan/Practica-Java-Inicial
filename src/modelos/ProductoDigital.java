package modelos;

public class ProductoDigital extends Producto {
    private double tamDescarga;
    private int licencia;

    public ProductoDigital(String nombre, double precio, int stock, double tamDescarga, int licencia) {
        super(nombre, precio, stock);
        this.tamDescarga = tamDescarga;
        this.licencia = licencia;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Digital | descarga: %.1f MB | licencia: %s", tamDescarga, licencia);
    }

    @Override
    public String getTipo() {
        return "Digital";
    }

    public double getTamDescarga() {
        return tamDescarga;
    }

    public void setTamDescarga(double tamDescarga) {
        this.tamDescarga = tamDescarga;
    }

    public int getLicencia() {
        return licencia;
    }

    public void setLicencia(int licencia) {
        this.licencia = licencia;
    }
}
