package mx.edu.utez.exameneslinea.model;

public class UsuarioMateria {
    private int id_ususario_materia;
    private int usuario_id;
    private int materia_id;

    public UsuarioMateria() {
    }

    public UsuarioMateria(int id_ususario_materia, int usuario_id, int materia_id) {
        this.id_ususario_materia = id_ususario_materia;
        this.usuario_id = usuario_id;
        this.materia_id = materia_id;
    }

    public int getId_ususario_materia() {
        return id_ususario_materia;
    }

    public void setId_ususario_materia(int id_ususario_materia) {
        this.id_ususario_materia = id_ususario_materia;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(int materia_id) {
        this.materia_id = materia_id;
    }
}
