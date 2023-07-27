package mx.edu.utez.exameneslinea.controller.Registro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Question;

@WebServlet(name = "RegistrarQuestionServlet", value = "/RegistrarQuestionServlet")
public class RegistrarQuestionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        int id = Integer.parseInt(jsonObject.get("id").getAsString());
        String value = jsonObject.get("value").getAsString();
        int idexam = (int) req.getSession().getAttribute("examenidques");

        System.out.println("ID: " + id);
        System.out.println("Valor: " + value);
        System.out.println("ID: " + idexam);
        ExamenDao daoex = new ExamenDao();

        if (id == 1) {
            daoex.insertQUES(value, "Abierta");
            Question QUES = (Question) daoex.findQues(value, "Abierta");
            Question ques = (Question) daoex.findEQA(idexam, 1);
            daoex.updateEQA(QUES.getId_ques(), idexam, ques.getId_exam_question());

            // Obtener el nuevo ID de la pregunta insertada
            int newId = QUES.getId_ques();

            // Enviar el nuevo ID como parte de la respuesta AJAX
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("newId", newId);

            // Configurar el tipo de contenido y enviar la respuesta JSON al cliente
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(jsonResponse.toString()); // Corregido aquí, convertir el objeto JSON a cadena
            out.flush();
        } else {
            System.out.println("Actualiza la pregunta actual");
            daoex.updateQues(id, value);
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Datos recibidos en el servidor con éxito");
        }

    }

}


