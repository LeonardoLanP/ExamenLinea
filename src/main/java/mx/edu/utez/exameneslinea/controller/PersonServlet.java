package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;
import mx.edu.utez.exameneslinea.model.Person;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PersonServlet", value = "/person")
public class PersonServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int idRol;
        System.out.println(id);

        if (id.equals("docente")) {
            idRol = 2;
            req.getSession().setAttribute("personType", "docente");
        } else if (id.equals("estudiante")) {
            idRol = 3;
            req.getSession().setAttribute("personType", "estudiante");
        } else {
            idRol = 0;
        }

        UsuarioDao dao = new UsuarioDao();
        List<Person> lista;

        if (idRol > 0) {
            lista = dao.findAll(idRol); // Obtener la lista de usuarios por ID de rol
        } else {
            lista = dao.findAll(); // Obtener la lista de todos los usuarios
        }
        req.getSession().setAttribute("personList", lista);
        resp.sendRedirect(req.getContextPath() + "/Administrador/inicio.jsp");

    }
}
