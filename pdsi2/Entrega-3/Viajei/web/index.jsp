<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% session.setMaxInactiveInterval(1800); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viajei</title>
         <style>
        body{
         background-color: #C0C0C0
        }
        </style>
    </head>
  
    <body>
        <div align="center">
            <img src="cabecalho.png" alt="Logo da aplicação"><br><br>
                
            <a href="#"><input type="button" name="inicio" value="Início" style="width: 120px; height: 40px" ></a><br>
            <a href="buscar"><input type="button" name="buscar" value="Buscar" style="width: 120px; height: 40px"></a><br>
            <a href="#"><input type="button" name="buscaSalva" value="Buscas Salvas" style="width: 120px; height: 40px"></a><br>
            <a href="#"><input type="button" name="preferencias" value="Preferências" style="width: 120px; height: 40px"></a><br>
            </div>
            
    </body>
</html>
