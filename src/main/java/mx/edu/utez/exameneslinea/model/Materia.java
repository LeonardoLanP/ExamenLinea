package mx.edu.utez.exameneslinea.model;

public class Materia {
    private int id_matera;
    private int grado;
    private String gupo;
    private String nombre_materia;

    public Materia() {
    }

    public Materia(int id_matera, int grado, String gupo, String nombre_materia) {
        this.id_matera = id_matera;
        this.grado = grado;
        this.gupo = gupo;
        this.nombre_materia = nombre_materia;
    }

    public int getId_matera() {
        return id_matera;
    }

    public void setId_matera(int id_matera) {
        this.id_matera = id_matera;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public String getGupo() {
        return gupo;
    }

    public void setGupo(String gupo) {
        this.gupo = gupo;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
}
