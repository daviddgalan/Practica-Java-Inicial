package modelos;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private Boolean estado;

    private static int contador=1;

    public Usuario(String nombre, String email, Boolean estado) {
        this.id = contador++;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | %.2f € | Activo: %d", id, nombre, email, estado);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
