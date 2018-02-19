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
		//recup�ration du code saisie par l'utilisateur
		String code=request.getParameter( "code" );
		//recup�ration de l'utilisateur enregistr� dans le param�tre session
		HttpSession session = request.getSession(); 
	    DirContext contexte = (DirContext)session.getAttribute("contexte"); 
	    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");		
		
		Totp t=new Totp(utilisateur.getSecret());
        System.out.println(t.verify(code));
        
		if(t.verify(code)) {
			//si le code saisie est correcte
			message="Authentification r�ussite!";
			request.setAttribute( "message", message );
			request.setAttribute( "utilisateur", utilisateur );
			this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );

		}else {
			//si le code saisie est correcte
			message="Code erron�! veuillez r�essayer";
			request.setAttribute( "message", message );
			request.setAttribute( "utilisateur", utilisateur );
			this.getServletContext().getRequestDispatcher( "/saisieQR.jsp" ).forward( request, response );
			 }
	}

	
}
