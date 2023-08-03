package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.*;
import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "LoginExamenServlet", value = "/ques_reload")
public class LoginExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigo = req.getParameter("codigo");
        int id = (int) req.getSession().getAttribute("idEstudiantePerson");
        System.out.println(id + " id del estudiante");
        ExamenDao dao = new ExamenDao();
        UsuarioDao daoUs = new UsuarioDao();

        Person per = (Person) daoUs.findOne(id);
        Exam exm = (Exam) dao.findOneByCode(codigo);
        System.out.println("Tipo de examen: " + exm.getType_exam());
        if (exm.getCode().equals(codigo)) {
            if (exm.getStatusex().equals("1")) {
                if(!(dao.finOpenExam(codigo,id)) && exm.getType_exam().equals("Abierta")){
                    System.out.println("Va registrar el nuevo examen Abierto");

                    Exam ex = (Exam) dao.findSubject(codigo);
                    if(dao.findOneUserSubRegitro(per.getUser_id(),ex.getSub_id())){
                        System.out.println("Se le creo un nuevo usuario Abierta");
                        dao.insertMateriaUsuario(per.getUser_id(),ex.getSub_id());
                    }
                    User_sub uS = (User_sub) dao.findOneUserSub(per.getUser_id(), ex.getSub_id());
                    int ExamInseId = dao.insertExam(1, uS.getId_user_sub(), ex.getNamex());
                    dao.updateCode(uS.getId_user_sub(),codigo,ExamInseId);

                    List<Question> questionList = dao.findAllExamStudent(codigo,exm.getUser_sub_id());
                    int cantidadPreguntasSeleccionar = questionList.size() / 2;
                    Set<Question> preguntasSeleccionadas = new HashSet<>();

                    List<Exam_Question_Answer> examQuestionAnswers = new ArrayList<>();
                    while (preguntasSeleccionadas.size() < cantidadPreguntasSeleccionar) {
                        int indiceAleatorio = (int) (Math.random() * questionList.size());
                        Question preguntaAleatoria = questionList.get(indiceAleatorio);
                        preguntasSeleccionadas.add(preguntaAleatoria);
                    }

                    Exam Newexam = (Exam) dao.finOpenExamID(codigo,id);
                    System.out.println("Id del nuevo examen :" + Newexam.getId_exam());
                    List<Question> preguntasSeleccionadasList = new ArrayList<>(preguntasSeleccionadas);
                    for (Question pregunta : preguntasSeleccionadasList) {
                        examQuestionAnswers.add(new Exam_Question_Answer(0, Newexam.getId_exam(), pregunta.getQues_id(),1,null));
                    }
                    dao.insertEQA(examQuestionAnswers);
                    req.getSession().setAttribute("examenid", Newexam.getId_exam());
                    req.getSession().setAttribute("quests", preguntasSeleccionadasList);
                    resp.sendRedirect("./Estudiante/examen.jsp");

                }else if(!(dao.finOpenExam(codigo,id)) && exm.getType_exam().equals("Multiple")){
                    System.out.println("Examen multiple a registrar");
                    Exam ex = (Exam) dao.findSubject(codigo);
                    if(dao.findOneUserSubRegitro(per.getUser_id(),ex.getSub_id())){
                        System.out.println("Se le creo un nuevo usuario Multiple");
                        dao.insertMateriaUsuario(per.getUser_id(),ex.getSub_id());
                    }
                    User_sub uS = (User_sub) dao.findOneUserSub(per.getUser_id(), ex.getSub_id());
                    int ExamInseId = dao.insertExam(1, uS.getId_user_sub(), ex.getNamex());
                    dao.updateCode(uS.getId_user_sub(),codigo,ExamInseId);

                    List<Question> questionList = dao.finAllQuestionMultiple(exm.getId_exam());
                    int cantidadPreguntasSeleccionar = questionList.size() / 2;
                    Set<Question> preguntasSeleccionadas = new HashSet<>();
                    List<Exam_Question_Answer> examQuestionAnswers = new ArrayList<>();

                    while (preguntasSeleccionadas.size() < cantidadPreguntasSeleccionar) {
                        int indiceAleatorio = (int) (Math.random() * questionList.size());
                        Question preguntaAleatoria = questionList.get(indiceAleatorio);
                        preguntasSeleccionadas.add(preguntaAleatoria);
                    }
                    Exam Newexam = (Exam) dao.finOpenExamID(codigo,per.getId_person());
                    List<Question> preguntasSeleccionadasList = new ArrayList<>(preguntasSeleccionadas);
                    for (Question pregunta : preguntasSeleccionadasList) {
                        examQuestionAnswers.add(new Exam_Question_Answer(0, Newexam.getId_exam(), pregunta.getQues_id(),2,"Multiple"));
                        System.out.println(pregunta.getQuestion());
                    }
                    dao.insertEQA(examQuestionAnswers);
                    req.getSession().setAttribute("quests", preguntasSeleccionadasList);
                    resp.sendRedirect("./Estudiante/examen.jsp");
                }else{
                    System.out.println("Entra aqui si hay un examen con ese usuario");
                    System.out.println(codigo + " id de la persona: " + per.getUser_id());
                    Exam exam = (Exam) dao.finOpenExamID(codigo,id);
                    Exam_Question_Answer EQA = (Exam_Question_Answer) dao.findOneQuestionOne(exam.getId_exam());
                    System.out.println("Id de la pregunta: " + EQA.getAnswer_id() + "Tipo de la pregunta: " + EQA.getOpen_Answer());

                    if(EQA.getAnswer_id() != 1 && EQA.getOpen_Answer().equals("Multiple")){
                        List<Question> questionList = dao.finAllQuestionMultipleEstudiante(exam.getId_exam());
                        req.getSession().setAttribute("examenid", exam.getId_exam());
                        req.getSession().setAttribute("quests", questionList);
                        resp.sendRedirect("./Estudiante/examen.jsp");
                    }else {
                        List<Question> questionList = dao.findAllExamStudentAbierta(codigo,exam.getUser_sub_id());
                        req.getSession().setAttribute("examenid", exam.getId_exam());
                        req.getSession().setAttribute("quests", questionList);
                        resp.sendRedirect("./Estudiante/examen.jsp");
                    }
                }
            } else {
                resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=desactivado");
            }
        } else {
            resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=noRegistrado");
        }
    }
}
