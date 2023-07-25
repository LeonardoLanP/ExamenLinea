package mx.edu.utez.exameneslinea.model;

public class Exam {
    private int id_exam;
    private String code;
    private String grade;
    private String statusex;
    private String dateex;
    private int user_sub_id;

    private String namex;

    public Exam() {
    }

    public Exam(int id_exam, String code, String grade, String statusex, String dateex, int user_sub_id, String namex) {
        this.id_exam = id_exam;
        this.code = code;
        this.grade = grade;
        this.statusex = statusex;
        this.dateex = dateex;
        this.user_sub_id = user_sub_id;
        this.namex = namex;
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

    public String getStatusex() {
        return statusex;
    }

    public void setStatusex(String statusex) {
        this.statusex = statusex;
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

    public String getNamex() {
        return namex;
    }

    public void setNamex(String namex) {
        this.namex = namex;
    }
}