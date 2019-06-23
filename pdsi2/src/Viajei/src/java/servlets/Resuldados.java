package servlets;

import java.io.*;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import models.*;
import dao.Persistencia;

@WebServlet("/resultados")
public class Resuldados extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("data_ida") == null || request.getParameter("data_volta") == null || request.getParameter("valor") == null) {
                response.sendError(400);
                out.close();
            }
            out.println("<html>");
            out.println("<head><title>Viajei - Destinos</title></head><body>");

            Date data_ida = Date.valueOf(request.getParameter("data_ida")), data_volta = Date.valueOf(request.getParameter("data_volta"));
            float valor = Float.valueOf(request.getParameter("valor"));
            Busca b = new Busca(data_ida, data_volta, valor);
            String clima, transporte, perfil = "", servicos, alimentacao, pagamento;
            clima = request.getParameter("clima") == null ? "" : request.getParameter("clima");
            transporte = request.getParameter("transporte") == null ? "" : request.getParameter("transporte");
            int count = Integer.parseInt(request.getParameter("perfis"));
            if (count == 0) {
                perfil = "";
            } else {
                for (int i = 0; i < count; i++) {
                    perfil += request.getParameter("pref_perfil"+i);
                }
            }
            servicos = request.getParameter("servicos") == null ? "" : request.getParameter("servicos");
            alimentacao = request.getParameter("alimentacao") == null ? "" : request.getParameter("alimentacao");
            pagamento = request.getParameter("pagamento") == null ? "" : request.getParameter("pagamento");
            Preferencias p = new Preferencias(clima, transporte, perfil, servicos, alimentacao, pagamento);

            List destinos = Persistencia.busca(b, p);
            Iterator it = destinos.iterator();

            if (!it.hasNext()) {
                out.println("<center>Nenhum destino encontrado!</center");
            }
            while (it.hasNext()) {
                Destino d = (Destino) it.next();
                out.println("<a href=\"../destino?id=\"" + d.getId() + "><div><h4>" + d.getNome() + ", " + d.getPais()
                        + "</h4>" + "<p>Gasto médio: " + d.getGastos() + "</p></div></a></br></br>");
            }

            out.println("</body></html>");
        } catch (Exception e) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(500);
        }
    }
}
