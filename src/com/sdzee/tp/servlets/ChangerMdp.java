package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;

import com.sdzee.tp.beans.Utilisateur;

import ldapCodes.LdapAuthentification;
@WebServlet("/ChangerMdp")
public class ChangerMdp extends HttpServlet {

       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession(); 
		String id = (String) session.getAttribute("id");
		System.out.println("id is: "+id);
		String Actuel = request.getParameter( "Actuel" );
				

				
		String nouveau = request.getParameter( "Nouveau" );	
		String message;
				

				Utilisateur utilisateur=(Utilisateur)session.getAttribute("utilisateur");

					System.out.println("le password est"+ utilisateur.getPwd());
					
				     DirContext contexte = (DirContext)session.getAttribute("contexte");
		    		
				LdapShaPasswordEncoder isValid= new LdapShaPasswordEncoder() ; 
			      
			      if( isValid.isPasswordValid(utilisateur.getPwd(), Actuel, null)) {
			    		 try {    
			    		     
			    			 if(LdapAuthentification.edit_user_password(contexte,id,nouveau )) {
			    				 	message = "Mise à jour réussite du mot  de passe ";
			    				 	try {
			    				 		LdapAuthentification.disconnect(contexte);
			    				 		
			    				 	} catch (NamingException e) {
			    					// TODO Auto-generated catch block
			    				 		e.printStackTrace();
			    				 	}

			    				 	if (session != null) {
			    				 		session.invalidate();
			    				 		System.out.println(8);
			    				 	}
			    				 	DirContext Nouveau_contexte = LdapAuthentification.user_connect(id, nouveau);
			    				 	
			    				 	 session = request.getSession(true); 
			    				 	
			    				 	session.setAttribute("contexte",Nouveau_contexte);

			    				 	session.setAttribute("id", id);

			    				
			    				 	utilisateur = LdapAuthentification.get_attributes(id,Nouveau_contexte);
			    					session.setAttribute("utilisateur", utilisateur);
			    				 	request.setAttribute( "message", message );
			    	       	  		request.setAttribute( "utilisateur", utilisateur );
			    	       	  		this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );

			    			}else {
			    				message = "ECHEC : mot de passe non modifié ";
			    		
			    				utilisateur = LdapAuthentification.get_attributes(id,contexte);
			    					
			    	        	request.setAttribute( "id", id );
			    				request.setAttribute( "message", message );
			    	            this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
			    	    	
			    			}
			    		
			    			 
			    		}catch(java.lang.NullPointerException e) {

			    	    	message = "mot de passe incorrecte";
			    	    	/* Ajout du message à l'objet requête */
			    	        request.setAttribute( "message", message );
			    	      
			    	    	this.getServletContext().getRequestDispatcher( "/securite.jsp" ).forward( request, response );
			    	    	
			    		}
		        
				}else {
					message = "Votre mot de passe est incorrect.";
			       
			            request.setAttribute( "message", message );
			           request.setAttribute( "utilisateur", utilisateur );
			        	this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
				}
			      
			   
		 
	
	     
	    

	
	
		}


}
