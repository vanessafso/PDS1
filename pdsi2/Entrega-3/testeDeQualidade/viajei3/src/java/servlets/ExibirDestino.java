package servlets;

import models.Destino;
import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import dao.Persistencia;

@WebServlet("/destino")
public class ExibirDestino extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Destino d = Persistencia.getDestino(id);
            
            out.println("<html>");
            out.println("<head><title>Viajei - " + d.getNome() + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + d.getNome() + "</h1></br><p>País: " + d.getPais() + "</p></br><p>Clima: " + d.getClima()
                    + "</p></br><p>Gasto médio: " + d.getGastos() + "</p>");
            out.println("</body></html>");
            out.close();
        } catch (Exception e) {
            Logger.getLogger(ExibirDestino.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
