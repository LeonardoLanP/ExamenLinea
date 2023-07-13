package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Persona;
import mx.edu.utez.exameneslinea.model.Usuario;
import mx.edu.utez.exameneslinea.model.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistroPerServlet", value = "/reg-person")
public class RegitroPerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nombre");
        String last1 = req.getParameter("apellido1");
        String last2 = req.getParameter("apellido2");
        String curp = req.getParameter("CURP");
        String email = req.getParameter("correo");
        String rol = req.getParameter("rol");

        
        String[] nombres = name.split(" ");
        String firstname = "";
        String secondname = null;

        if (nombres.length >= 2) {
            firstname = nombres[0];
            secondname = nombres[1];
        } else if (nombres.length == 1) {
            firstname = nombres[0];
        }

        String[] userpass = email.toLowerCase().split("@");
        String user = "";
        String pass = "";
        int id_rol = 0;
        if(rol.equals("estudiante")){
            id_rol = 3;
             user = userpass[0];
             pass = curp.substring(curp.length() - 3);;
        }else{
            id_rol = 2;
             user = name+last1;
             pass = name+curp.substring(curp.length() - 3);
        }

        UsuarioDao dao = new UsuarioDao();
        dao.insertu(new Usuario(0,user,email.toLowerCase(),pass,id_rol,1));
        Usuario usr = (Usuario) dao.findOne(user,pass,id_rol);

        dao.insertp(new Persona(0,firstname,secondname,last1,last2,curp,usr.getId_user()));

        if(id_rol==3){
            req.getSession().setAttribute("personType", "estudiante");
            resp.sendRedirect(req.getContextPath() + "/person?id=estudiante");
        }else{
            req.getSession().setAttribute("personType", "docente");
            resp.sendRedirect(req.getContextPath() + "/person?id=docente");
        }

    }
}
