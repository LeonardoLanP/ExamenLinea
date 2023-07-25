package mx.edu.utez.exameneslinea.model;

public class Question {
    private int id_ques;
    private String question;
    private String type_question;

    public Question() {
    }

    public Question(int id_ques, String question, String type_question) {
        this.id_ques = id_ques;
        this.question = question;
        this.type_question = type_question;
    }

    public int getId_ques() {
        return id_ques;
    }

    public void setId_ques(int id_ques) {
        this.id_ques = id_ques;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType_question() {
        return type_question;
    }

    public void setType_question(String type_question) {
        this.type_question = type_question;
    }
}
