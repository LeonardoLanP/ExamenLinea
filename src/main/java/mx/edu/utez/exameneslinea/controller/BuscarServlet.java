package mx.edu.utez.exameneslinea.controller;

import com.google.gson.Gson;
import mx.edu.utez.exameneslinea.model.Persona;
import mx.edu.utez.exameneslinea.model.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
@WebServlet(name = "BuscarServlet", value = "/BuscarServlet")
@MultipartConfig
public class BuscarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ROL = request.getParameter("userId");
        int idRol = Integer.parseInt(ROL);


        UsuarioDao dao = new UsuarioDao();
        Persona usr = (Persona) dao.findOne(idRol);



        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println(usr.getFirstname());
        if(usr.getSecondname() != null){
            out.println(usr.getSecondname());
        }else{
            out.println("");
        }

        out.println(usr.getLastname1());
        if(usr.getLastname2() != null){
            out.println(usr.getLastname2());
        }else{
            out.println("");
        }
        out.println(usr.getCurp());
        out.println(usr.getEmail());
        out.println(usr.getUser());
        out.println(usr.getPass());
        out.println(usr.getRol_id());
        out.println(usr.getId_person());
        System.out.println("Rol"+usr.getRol_id() + " "+usr.getId_person());
        out.flush();
    }
}
