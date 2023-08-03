package mx.edu.utez.exameneslinea.controller.ServletsABorrar;

import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;
import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegitroPerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("nombre");
        String last1 = req.getParameter("apellido1");
        String last2 = req.getParameter("apellido2");
        String curp = req.getParameter("CURP");
        String email = req.getParameter("correo");
        String rol = req.getParameter("rol");
        String matricula = req.getParameter("matricula");
        System.out.println(last1);

        
        String[] nombres = name.split(" ");
        String firstname = nombres[0];


        String user = "";
        String pass = "";
        int id_rol = 0;
        if(rol.equals("estudiante")){
            id_rol = 3;
             user = matricula;
             pass = curp.substring(curp.length() - 3);;
        }else{
            id_rol = 2;
             user = firstname+"_"+last1;
             pass = firstname+curp.substring(curp.length() - 3);
        }

        UsuarioDao dao = new UsuarioDao();
        dao.insertu(new User(0,user,email.toLowerCase(),pass,1,id_rol));
        User usr = (User) dao.findOneUSU(user,email,id_rol,pass);
        System.out.println(usr.getID_user());
        dao.insertp(new Person(0,name,last1,last2,curp,usr.getID_user()));

        if(id_rol==3){
            req.getSession().setAttribute("personType", "estudiante");
            resp.sendRedirect(req.getContextPath() + "/person?id=estudiante");
        }else{
            req.getSession().setAttribute("personType", "docente");
            resp.sendRedirect(req.getContextPath() + "/person?id=docente");
        }

    }
}