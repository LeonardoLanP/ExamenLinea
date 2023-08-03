package mx.edu.utez.exameneslinea.controller.ServletsABorrar;

import mx.edu.utez.exameneslinea.model.*;
import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistroMateriaServlet", value = "/reg-materia///SSS")
public class RegistroMateriaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nombre");
        int grade = Integer.parseInt(req.getParameter("grado"));
        String group = req.getParameter("grupo");
        Person per =(Person) req.getSession().getAttribute("sesion");

        ExamenDao dao = new ExamenDao();
        dao.insertMateria(new Subject(0,grade,group,name,1));
        Subject mater = (Subject) dao.findMateria(grade,group,name);
        dao.insertMateriaUsuario(per.getID_user(),mater.getId_sub());

            req.getSession().setAttribute("materias", mater);
            resp.sendRedirect(req.getContextPath() + "/all-subjets");

    }
}
