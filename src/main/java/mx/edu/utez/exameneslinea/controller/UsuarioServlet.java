package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Usuario;
import mx.edu.utez.exameneslinea.model.UsuarioDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsuarioServlet", value = "/login")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  rol = req.getParameter("rol");
        System.out.println(rol+ "doGet");
        req.getSession().setAttribute("rol", rol);
        resp.sendRedirect("./login.jsp?rol="+rol);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");
        String rol =(String) req.getSession().getAttribute("rol");
        int idRol;
        System.out.println(rol + "doPost");
        if(rol.equals("admin")){
            idRol = 1;
        }else if (rol.equals("docente")){
            idRol = 2;
        }else{
            idRol = 3;
        }
        UsuarioDao dao  = new UsuarioDao();
        Usuario usr = (Usuario) dao.findOne(usuario,contrasena,idRol);

        if (usr.getId_user() != 0 && usr.getRol_id() == idRol) {
            if (usr.getRol_id() == 1 && usr.getStatus() == 1) {
                req.getSession().setAttribute("sesion", usr);
                resp.sendRedirect("./Administrador/inicio.jsp");
            } else if (usr.getRol_id() == 2 && usr.getStatus() == 1) {
                req.getSession().setAttribute("sesion", usr);
                resp.sendRedirect("./Docente/materias.jsp");
            } else if (usr.getRol_id() == 3 && usr.getStatus() == 1) {
                req.getSession().setAttribute("sesion", usr);
                resp.sendRedirect("./Estudiante/acceso.jsp");
            } else {
                resp.sendRedirect("login.jsp?rol="+rol+"&status=desactivado");
            }
        } else {
            resp.sendRedirect("login.jsp?rol="+rol+"&status=noRegistrado");

        }
    }
}