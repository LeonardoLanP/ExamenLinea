package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MateriasServlet", value = "/ser-mater")
public class MateriasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int materiaId = Integer.parseInt(req.getParameter("materiaId"));
        ExamenDao dao = new ExamenDao();

        List<Person> lista;
        lista = dao.findAllExam(materiaId);
        req.getSession().setAttribute("exam", lista);
        resp.sendRedirect("./Docente/examenes.jsp");

    }
}
