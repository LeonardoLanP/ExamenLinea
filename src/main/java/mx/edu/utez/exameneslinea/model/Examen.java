package mx.edu.utez.exameneslinea.model;

public class Examen {
    private int id_exam;
    private String code;
    private String grade;
    private String status;
    private String dateex;
    private int user_sub_id;

    public Examen() {
    }

    public Examen(int id_exam, String code, String grade, String status, String dateex, int user_sub_id) {
        this.id_exam = id_exam;
        this.code = code;
        this.grade = grade;
        this.status = status;
        this.dateex = dateex;
        this.user_sub_id = user_sub_id;
    }

    public int getId_exam() {
        return id_exam;
    }

    public void setId_exam(int id_exam) {
        this.id_exam = id_exam;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateex() {
        return dateex;
    }

    public void setDateex(String dateex) {
        this.dateex = dateex;
    }

    public int getUser_sub_id() {
        return user_sub_id;
    }

    public void setUser_sub_id(int user_sub_id) {
        this.user_sub_id = user_sub_id;
    }
}


