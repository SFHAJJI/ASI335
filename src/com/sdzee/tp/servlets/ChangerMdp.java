package com.sdzee.tp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangerMdp extends HttpServlet {

       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Actuel = request.getParameter( "Actuel" );
		String Nouveau = request.getParameter( "Nouveau" );
        String Confirmer = request.getParameter( "Confirmer" );
      
		
	
	
	
	}

	

}
