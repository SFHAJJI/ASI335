package com.sdzee.tp.servlets;
import java.io.IOException;

import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.aerogear.security.otp.Totp;

import com.sdzee.tp.beans.Utilisateur;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import ldapCodes.LdapAuthentification;

public class GoogleAuth extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String message;
		String code=request.getParameter( "code" );
		String id=request.getParameter( "id" );
		HttpSession session = request.getSession(); // session en cours 
	    DirContext contexte = (DirContext)session.getAttribute("contexte"); 
	    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");		
		
		Totp t=new Totp(utilisateur.getSecret());
        System.out.println(t.verify(code));
        
		if(t.verify(code)) {
			message="Authentification réussite!";
			request.setAttribute( "message", message );
			request.setAttribute( "utilisateur", utilisateur );
			this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );

		}else {
			message="Code erroné! veuillez réessayer";
			request.setAttribute( "message", message );
			request.setAttribute( "utilisateur", utilisateur );
			this.getServletContext().getRequestDispatcher( "/saisieQR.jsp" ).forward( request, response );
			 }
	}

	
}
