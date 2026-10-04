package gestor;

import modelos.Producto;
import modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public void añadir(Usuario usuario){
        usuarios.add(usuario);
    }

    public void eliminar(int id){
        usuarios.removeIf(u -> u.getId()==id);
    }
    public List<Usuario> getUsuarios(){
        return new ArrayList<>(usuarios);
    }
    public Usuario buscarId(int id){
        for (Usuario u: usuarios) {
            if(u.getId()==id) return u;
        }
        return null;
    }
}
