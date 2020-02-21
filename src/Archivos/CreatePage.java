/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import Objetos.Token;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import Objetos.Error;
/**
 *
 * @author Steven Sis
 */
public class CreatePage {
    //Metodo que va crear la pagina donde estara la lista de token
    public void pageTokens(ArrayList<Token> lstToken){
        String page="";
        page+="<!DOCTYPE HTML> \n "+
        "<HTML lang=\"es\"> \n"
        +"<head> \n"
        +"<title> Lista de Tokens </title> \n"
        +"<meta charset=\"UTF-8\"> \n"
        +"<meta name=\"description\" content=\"Reporte de Tokens\"> \n"
        +"<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"> \n"
        +"</head> \n"
        +"<body> \n"
        +"<div> \n"
        +"<h1 class=\"container\">Lista Tokens</h1> \n"
        +"</div> \n"
        +"<div class=\"container\"> \n"
        +"<table class=\"table table-dark\">\n"
        +"<thead>\n"
        +"<tr>\n"
        +"<th scope=\"col\">No.</th>\n"
        +"<th scope=\"col\">Lexema</th>\n"
        +"<th scope=\"col\">Token</th>\n"
        +"<th scope=\"col\">Fila</th>\n"
        +"<th scope=\"col\">Columna</th>\n"
        +"</tr>\n"
        +"</thead>\n"
        +"<tbody>\n";
        for (int i = 0; i < lstToken.size(); i++) {
          page+="<tr>\n"
                  +"<th scope=\"row\">"
                  +lstToken.get(i).getContadorToken()
                  +"</th>\n"
                  +"<td>\n"
                  +lstToken.get(i).getValor()
                  +"\n</td\n>"
                  +"<td>\n"
                  +lstToken.get(i).getTipo()
                  +"\n</td\n>"
                  +"<td>\n"
                  +lstToken.get(i).getFila()
                  +"\n</td\n>"
                  +"<td>\n"
                  +lstToken.get(i).getColumna()
                  +"\n</td\n>"
                  +"</tr>\n";  
        }
        page+="</tbody>\n";
        page+="</table>\n";
        page+="</div>\n";
        page+="</body>\n";
        page+="</HTML>";
        File file = new File("ReporteTokens.html");
         try {

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(page);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Desktop.getDesktop().open(file);
            //archivo.delete();

        } catch (Exception ex) {
                System.out.println("<<<<<------Problema con archivo de pagina de Tokens---------->>>>>>>");
        }
    }
    
    public void pageError(ArrayList<Error> lstError){
        String page="";
        page+="<!DOCTYPE HTML> \n "+
        "<HTML lang=\"es\"> \n"
        +"<head> \n"
        +"<title> Lista de Tokens </title> \n"
        +"<meta charset=\"UTF-8\"> \n"
        +"<meta name=\"description\" content=\"Reporte de Errores\"> \n"
        +"<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"> \n"
        +"</head> \n"
        +"<body> \n"
        +"<div> \n"
        +"<h1 class=\"container\">Lista Errores</h1> \n"
        +"</div> \n"
        +"<div class=\"container\"> \n"
        +"<table class=\"table table-dark\">\n"
        +"<thead>\n"
        +"<tr>\n"
        +"<th scope=\"col\">No.</th>\n"
        +"<th scope=\"col\">Error</th>\n"
        +"<th scope=\"col\">Descripcion</th>\n"
        +"<th scope=\"col\">Fila</th>\n"
        +"<th scope=\"col\">Columna</th>\n"
        +"</tr>\n"
        +"</thead>\n"
        +"<tbody>\n";
        for (int i = 0; i < lstError.size(); i++) {
          page+="<tr>\n"
                  +"<th scope=\"row\">"
                  +lstError.get(i).getContadorE()
                  +"</th>\n"
                  +"<td>\n"
                  +lstError.get(i).getValor()
                  +"\n</td\n>"
                  +"<td>\n"
                  +lstError.get(i).getDescripcion()
                  +"\n</td\n>"
                  +"<td>\n"
                  +lstError.get(i).getFila()
                  +"\n</td\n>"
                  +"<td>\n"
                  +lstError.get(i).getColumna()
                  +"\n</td\n>"
                  +"</tr>\n";  
        }
        page+="</tbody>\n";
        page+="</table>\n";
        page+="</div>\n";
        page+="</body>\n";
        page+="</HTML>";
        File file = new File("ReporteErrores.html");
         try {

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(page);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Desktop.getDesktop().open(file);
            //archivo.delete();

        } catch (Exception ex) {
                System.out.println("<<<<<------Problema con archivo de pagina de Errores---------->>>>>>>");
        }
    }
}
