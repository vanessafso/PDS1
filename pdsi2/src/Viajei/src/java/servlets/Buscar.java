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
            out.println("<head><title>Viajei - Buscar</title></head>");
            out.println("<body><center><form action=\"\" method=\"post\">");
            out.println("<label for=\"data_ida\">Quando você pretende sair?</label></br>");
            out.println("<input type=\"date\" name=\"data_ida\" id=\"data_ida\" required/></br></br>");
            out.println("<label for=\"data_volta\">Quando você pretende voltar?</label></br>");
            out.println("<input type=\"date\" name=\"data_volta\" id=\"data_volta\" required/></br></br>");
            out.println("<label for=\"valor\">Quanto você pode gastar?</label></br>");
            out.println("<label for=\"valor\">R$</label>");
            out.println("<input type=\"number\" placeholder=\"0,00\" id=\"valor\" name=\"valor\" min=\"0\" required/></br></br>");
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
            out.println("<head><title>Viajei - Buscar</title></head>");
            out.println("<body><center><form action=\"../resultados\" method=\"post\">");
            out.println("<legend>Selecione suas preferências</legend>");
            out.println("<checkbox name=\"pref_clima\">Clima ideal: " + p.getClima() + "</checkbox>");
            out.println("<checkbox name=\"pref_transporte\">Transporte: " + p.getTransporte() + "</checkbox>");
            String perfis[] = p.getPerfil().split(",");
            int i;
            for (i = 0; i < perfis.length; i++) {
                out.println("<checkbox name=\"pref_perfil"+i+"\">Perfil do viajante: " + perfis[i] + "</checkbox>");
            }
            out.println("<input type=\"hidden\" value=\""+i+"\" name=\"perfis\"/>");
            out.println("<checkbox name=\"pref_servicos\">Servicos: " + p.getServicos() + "</checkbox>");
            out.println("<checkbox name=\"pref_alimentacao\">Alimentacao: " + p.getAlimentacao() + "</checkbox>");
            out.println("<checkbox name=\"pref_pagamento\">Tipo de pagamento: " + p.getPagamento() + "</checkbox>");

            out.println("<input type=\"hidden\" value=\"" + request.getParameter("data_ida") + "\" name=\"data_ida\"/>");
            out.println("<input type=\"hidden\" value=\"" + request.getParameter("data_volta") + "\" name=\"data_volta\"/>");
            out.println("<input type=\"hidden\" value=\"" + request.getParameter("valor") + "\" name=\"valor\"/>");
            out.println("<button type=\"submit\">Buscar</button>");
            out.println("</form></center></body></html>");
        } catch (Exception e) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(500);
        }

    }
}
