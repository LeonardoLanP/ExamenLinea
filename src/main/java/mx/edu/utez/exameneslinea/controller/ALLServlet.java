package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.ExamenDao;
import mx.edu.utez.exameneslinea.model.Persona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExamenServlet", value = "/all-subjets")
public class ALLServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Persona per =(Persona) req.getSession().getAttribute("sesion");
        ExamenDao dao = new ExamenDao();
        System.out.println("Hola"+ per.getFirstname());
        List<Persona> lista;
        lista = dao.findAllMa(per.getId_user());
        req.getSession().setAttribute("subjectlista", lista);
        resp.sendRedirect("./Docente/materias.jsp");
    }

}
