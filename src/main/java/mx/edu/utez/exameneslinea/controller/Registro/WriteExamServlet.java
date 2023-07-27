package mx.edu.utez.exameneslinea.controller.Registro;

import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WriteExamServlet", value = "/wri-exam")
public class WriteExamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idexam = Integer.parseInt(req.getParameter("examenid"));
        int id = idexam;
        ExamenDao dao = new ExamenDao();
        List<Question> lista;
        lista = dao.findQuestion(idexam);
        req.getSession().setAttribute("questions", lista);
        req.getSession().setAttribute("examenidques", id);
        resp.sendRedirect("./Docente/preguntas.jsp");
    }
}
