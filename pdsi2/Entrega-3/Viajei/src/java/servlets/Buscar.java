package servlets;

import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import models.Preferencias;
import dao.Persistencia;

@WebServlet("/buscar")
public class Buscar extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Viajei - Buscar</title><style> body{ background-color: #C0C0C0 } </style></head>"); 

            out.println("<div align=\"center\"><img src=\"cabecalho.png\" alt=\"Logo da aplicação\"><br><br><a href=\".\"><input type=\"button\" name=\"inicio\" value=\"Início\" style=\"width: 120px; height: 40px\" ></a><br><br>");
            
            out.println("<center><form action=\"\" method=\"post\">");
            out.println("<label for=\"data_ida\">Data de Ida</label></br>");
            out.println("<input type=\"date\" name=\"data_ida\" id=\"data_ida\" required/></br></br>");
            out.println("<label for=\"data_volta\">Data de Volta</label></br>");
            out.println("<input type=\"date\" name=\"data_volta\" id=\"data_volta\" required/></br></br>");
            out.println("<label for=\"valor\">Quanto você está disposto a gastar?</label></br>");
            out.println("<input type=\"number\" placeholder=\"             R$: 0,00\" id=\"valor\" name=\"valor\" min=\"0\" required/></br></br>");
            out.println("<button type=\"submit\">Próximo</button>");
            out.println("</form></center></body></html>");
            out.close();
        } catch (Exception e) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        int uid = 1;

        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("data_ida") == null || request.getParameter("data_volta") == null || request.getParameter("valor") == null) {
                response.sendError(400);
                out.close();
            }
            Preferencias p = Persistencia.getPreferencias(uid);
            out.println("<html>");
            out.println("<head><title>Viajei - Buscar</title><style> body{ background-color: #C0C0C0 } </style></head>");
            out.println("<div align=\"center\"><img src=\"cabecalho.png\" alt=\"Logo da aplicação\"><br><br><a href=\"index.jsp\"><input type=\"button\" name=\"inicio\" value=\"Início\" style=\"width: 120px; height: 40px\" ></a><br><br>");
            out.println("<form action=\"resultados\" method=\"post\">");
            out.println("<legend>Selecione suas preferências</legend></br></div>");
            out.println("<left><input type=\"checkbox\" id=\"pref_clima\" name=\"pref_clima\" value=\""+p.getClima()+"\"/><label for=\"pref_clima\">Clima ideal: " + p.getClima() + "</label></br></br>");
            out.println("<input type=\"checkbox\" id=\"pref_transporte\" name=\"pref_transporte\" value=\""+p.getTransporte()+"\"/><label for=\"pref_transporte\">Transporte: " + p.getTransporte() + "</label></br></br>");
            String perfis[] = p.getPerfil().split(",");
            int i;
            for (i = 0; i < perfis.length; i++) {
                out.println("<input type=\"checkbox\" id=\"pref_perfil" + i + "\" name=\"pref_perfil" + i + "\" value=\""+perfis[i]+"\"/><label for=\"pref_perfil" + i + "\">Perfil do viajante: " + perfis[i] + "</label></br></br>");
            }
            out.println("<input type=\"hidden\" value=\"" + i + "\" name=\"perfis\"/>");
            out.println("<input type=\"checkbox\" id=\"pref_servicos\" name=\"pref_servicos\" value=\""+p.getServicos()+"\"/><label for=\"pref_servicos\">Serviços: " + p.getServicos() + "</label></br></br>");
            out.println("<input type=\"checkbox\" id=\"pref_alimentacao\" name=\"pref_alimentacao\" value=\""+p.getAlimentacao()+"\"/><label for=\"pref_alimentacao\">Alimentacao: " + p.getAlimentacao() + "</label></br></br>");
            out.println("<input type=\"checkbox\" id=\"pref_pagamento\" name=\"pref_pagamento\" value=\""+p.getPagamento()+"\"/><label for=\"pref_pagamento\">Tipo de pagamento: " + p.getPagamento() + "</label></br></br>");

            out.println("<input type=\"hidden\" value=\"" + request.getParameter("data_ida") + "\" name=\"data_ida\"/>");
            out.println("<input type=\"hidden\" value=\"" + request.getParameter("data_volta") + "\" name=\"data_volta\"/>");
            out.println("<input type=\"hidden\" value=\"" + request.getParameter("valor") + "\" name=\"valor\"/>");
            out.println("</left><center><button type=\"submit\">Buscar</button>");
            out.println("</form></body></html>");
        } catch (Exception e) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(500);
        }

    }
}
