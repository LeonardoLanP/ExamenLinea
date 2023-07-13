package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistroMateriaServlet", value = "/reg-materia")
public class RegistroMateriaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nombre");
        String grade = req.getParameter("grado");
        String group = req.getParameter("grupo");
        int gradeValue = Integer.parseInt(grade);

        System.out.println(name + grade + group);

        ExamenDao dao = new ExamenDao();
        dao.insertMateria(new Materia(0,gradeValue,group,name));
        Materia mater = (Materia) dao.findMateria(gradeValue,group,name);



        Persona per =(Persona) req.getSession().getAttribute("sesion");
        System.out.println(per.getFirstname());

        dao.insertMateriaUsuario(per.getId_user(),mater.getId_matera());

            req.getSession().setAttribute("materias", mater);
            resp.sendRedirect(req.getContextPath() + "/all-subjets");

    }
}
