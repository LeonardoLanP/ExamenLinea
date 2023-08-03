package mx.edu.utez.exameneslinea.controller.ServletsABorrar;

import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BuscarMateriasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person per =(Person) req.getSession().getAttribute("sesion");
        ExamenDao dao = new ExamenDao();

        List<Person> lista;
        lista = dao.findAllMa(per.getID_user());
        req.getSession().setAttribute("subjectlista", lista);
        resp.sendRedirect("./Docente/materias.jsp");
    }

}