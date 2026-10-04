package modelos;

public class ProductoFisico extends Producto {
    private double peso;
    private double gastosEnvio;

    public ProductoFisico(String nombre, double precio, int stock, double peso, double gastosEnvio) {
        super(nombre, precio, stock);
        this.peso = peso;
        this.gastosEnvio = gastosEnvio;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Físico | peso: %.2f kg | envío: %.2f €", peso, gastosEnvio);
    }

    @Override
    public String getTipo() {
        return "Físico";
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }
}
