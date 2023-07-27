package mx.edu.utez.exameneslinea.model;

public class Question extends Exam_Question_Answer{
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

    public Question(int id_answer, String answer, int id_exam_question, int exam_id, int ques_id, int answer_id, String open_Answer, int id_ques, String question, String type_question) {
        super(id_answer, answer, id_exam_question, exam_id, ques_id, answer_id, open_Answer);
        this.id_ques = id_ques;
        this.question = question;
        this.type_question = type_question;
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
