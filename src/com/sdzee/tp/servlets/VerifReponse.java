package com.sdzee.tp.servlets;
import java.io.IOException;

import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Utilisateur;

import ldapCodes.LdapAuthentification;

public class VerifReponse extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String reponseSaisie = request.getParameter( "reponseSaisie" );
		String reponseI = request.getParameter("reponseI");
		String id = request.getParameter("id");
	
		boolean test =reponseI.equals(reponseSaisie);
		if(test){
			String message = "Réponse correcte";
        	request.setAttribute( "message", message );
        	request.setAttribute( "id", id );
        	this.getServletContext().getRequestDispatcher( "/nouveauPwd.jsp" ).forward( request, response );
			
		}else{
			String message = "Réponse incorrecte - authentification échouée";
            request.setAttribute( "message", message );
        	this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
		}
	}

}
