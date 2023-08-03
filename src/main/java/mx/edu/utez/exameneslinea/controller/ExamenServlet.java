package mx.edu.utez.exameneslinea.controller;

import com.google.gson.Gson;
import mx.edu.utez.exameneslinea.model.*;
import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ExamenServlet", value = "/examen/*")

public class ExamenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getPathInfo();

        switch (action){
            case "/buscar-pregunta":
                int idexam = Integer.parseInt(req.getParameter("examenid"));
                int id = idexam;
                ExamenDao dao = new ExamenDao();
                Exam_Question_Answer eqa = (Exam_Question_Answer) dao.findOneQuestionOne(idexam);
                List<Question> lista = null;
                if(eqa.getAnswer_id() >=2){
                    lista = dao.finAllQuestionMultiple(id);
                }else{
                    lista = dao.findQuestion(idexam);
                }
                req.getSession().setAttribute("questions", lista);
                req.getSession().setAttribute("examenidques", id);
                resp.sendRedirect(req.getContextPath() +  "/Docente/preguntas.jsp");
                break;
            case "":
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getPathInfo();
        List<Exam> lista = null;
        ExamenDao daoex = new ExamenDao();


        switch (action){
            case "/registrar-examen":
                String examen = req.getParameter("nombreex");
                int numQues = Integer.parseInt(req.getParameter("numberex"))*2;
                String typeQues = req.getParameter("tipo");
                Person per =(Person) req.getSession().getAttribute("sesion");
                int idSub = (int) req.getSession().getAttribute("idsub");

                User_sub usersub = (User_sub) daoex.findOneUserSub(per.getID_user(),idSub);
                daoex.insertExam(1,usersub.getId_user_sub(),examen);
                Exam exm = (Exam) daoex.findOneEXAMUSI(usersub.getId_user_sub(),examen);

                List<Exam_Question_Answer> examQuestionAnswers = new ArrayList<>();

                for (int i = 0; i < numQues; i++) {
                    if (typeQues.equals("Abierta")) {
                        examQuestionAnswers.add(new Exam_Question_Answer(0, exm.getId_exam(), 1,1,null));
                    } else if (typeQues.equals("Multiple")) {
                        examQuestionAnswers.add(new Exam_Question_Answer(0, exm.getId_exam(), 2,2,"Multiple"));
                    }
                }

                daoex.insertEQA(examQuestionAnswers);

                lista = daoex.findAllExam(idSub,per.getID_user());
                req.getSession().removeAttribute("exam");
                req.getSession().setAttribute("exam", lista);
                resp.sendRedirect(req.getContextPath() + "/Docente/examenes.jsp");
                break;
            case "/Registrar-Respuestas":
                BufferedReader reader = req.getReader();
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                Gson gson = new Gson();
                Exam_Question_Answer respuesta = gson.fromJson(sb.toString(), Exam_Question_Answer.class);

                int id = respuesta.getQues_id();
                int idEQA = respuesta.getAnswer_id();
                String value = respuesta.getOpen_Answer();

                daoex.updateEQAnswer(value,id,idEQA);
                break;
            default:
        }
    }
}
