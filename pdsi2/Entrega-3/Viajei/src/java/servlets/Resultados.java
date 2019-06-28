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
public class Resultados extends HttpServlet {

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
            out.println("<head><title>Viajei - Destinos</title><style> body{ background-color: #C0C0C0 } </style></head><body>");
            out.println("<div align=\"center\"><img src=\"cabecalho.png\" alt=\"Logo da aplicação\"><br><br><a href=\".\"><input type=\"button\" name=\"inicio\" value=\"Início\" style=\"width: 120px; height: 40px\" ></a><br><br>");
            
            Date data_ida = Date.valueOf(request.getParameter("data_ida")), data_volta = Date.valueOf(request.getParameter("data_volta"));
            float valor = Float.valueOf(request.getParameter("valor"));
            Busca b = new Busca(data_ida, data_volta, valor);
            String clima, transporte, perfil = "", servicos, alimentacao, pagamento;
            clima = request.getParameter("pref_clima") == null ? "" : request.getParameter("pref_clima");
            transporte = request.getParameter("pref_transporte") == null ? "" : request.getParameter("pref_transporte");
            int count = Integer.parseInt(request.getParameter("perfis"));
            
                Logger.getLogger(Resultados.class.getName()).log(Level.SEVERE, clima);
            if (count == 0) {
                perfil = "";
            } else {
                for (int i = 0; i < count; i++) {
                    perfil += request.getParameter("pref_perfil"+i);
                }
            }
            servicos = request.getParameter("pref_servicos") == null ? "" : request.getParameter("pref_servicos");
            alimentacao = request.getParameter("pref_alimentacao") == null ? "" : request.getParameter("pref_alimentacao");
            pagamento = request.getParameter("pref_pagamento") == null ? "" : request.getParameter("pref_pagamento");
            Preferencias p = new Preferencias(clima, transporte, perfil, servicos, alimentacao, pagamento);

            List destinos = Persistencia.busca(b, p);
            Iterator it = destinos.iterator();

            if (!it.hasNext()) {
                out.println("<center>Nenhum destino encontrado!</center");
            }
            while (it.hasNext()) {
                Destino d = (Destino) it.next();
                out.println("<div><h4>" + d.getNome() + ", " + d.getPais()
                        + "</h4>" + "<p>Gasto médio: " + d.getGastos()+ "</p></div></br></br>");
            }

            out.println("</body></html>");
        } catch (Exception e) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
