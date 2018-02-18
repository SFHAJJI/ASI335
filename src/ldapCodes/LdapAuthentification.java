package ldapCodes;
/**
 *
 * @author NCIBI
 */

import java.util.Hashtable;
import com.sdzee.tp.beans.Utilisateur;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

 
public class LdapAuthentification{
	
	public static void mainfunction() throws NamingException { //test, it works 
		// User authentification
		DirContext contexte = user_connect("ncibi", "passwordncibi");
		// Get the user's information
		Utilisateur user= new Utilisateur ();
		user = get_attributes("ncibi", contexte);
		// Test printing user's information (to delete later)
		System.out.println(user.getNom());
		System.out.println(user.getPrenom());
		// Edit an attribute of the user (it works :D )
		edit_user(contexte, user.getIdentifiant(), "cn", "Alaeddine");
		// User disconnection from the server
		disconnect(contexte);
		// now you can no longer get or set the user's information
	}

	public static DirContext user_connect(String uid, String pwd){	 
		//Adresse du serveur sur lequel se trouve l'annuaire LDAP
		String serverIP = "localhost";
		//Port du serveur sur lequel se trouve l'annuaire LDAP
		String serverPort = "10389";
		//Login de connexion à l'annuaire LDAP : Le login dois être sous forme de "distinguished name"
		//ce qui signifie qu'il dois être affiché sous la forme de son arborescence LDAP
		String serverLogin = "uid="+uid+",ou=users,dc=example,dc=com";
		//Mot de passe de connexion à l'annuaire LDAP
        String serverPass = pwd;
		//On remplis un tableau avec les parametres d'environement et de connexion au LDAP
		Hashtable<String, String> environnement = new Hashtable<String, String>();
		environnement.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		environnement.put(Context.PROVIDER_URL, "ldap://"+serverIP+":"+serverPort+"/");
        // Enable connection pooling
        environnement.put("com.sun.jndi.ldap.connect.pool", "true");
        // Authentification information
		environnement.put(Context.SECURITY_AUTHENTICATION, "simple");
		environnement.put(Context.SECURITY_PRINCIPAL, serverLogin);
		environnement.put(Context.SECURITY_CREDENTIALS, serverPass);
		
		try {
			//On appelle le contexte à partir de l'environnement
			DirContext contexte = new InitialDirContext(environnement);
			//Si ça ne plante pas c'est que la connexion est faite
			System.out.println("User authentification to server : SUCCES");
			return contexte;
	
		} catch (NamingException e) {

			System.out.println("User authentification to server : ECHEC");
			//e.printStackTrace();
			DirContext contexte=null;
			return contexte;
		}

	}
	
	
	public static DirContext sudo_connect() {	 
		String serverIP = "localhost";
		String serverPort = "10389";
		String serverLogin = "uid=admin,ou=system";
        String serverPass = "secret";
		Hashtable<String, String> environnement = new Hashtable<String, String>();
		environnement.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		environnement.put(Context.PROVIDER_URL, "ldap://"+serverIP+":"+serverPort+"/");
        environnement.put("com.sun.jndi.ldap.connect.pool", "true");
		environnement.put(Context.SECURITY_AUTHENTICATION, "simple");
		environnement.put(Context.SECURITY_PRINCIPAL, serverLogin);
		environnement.put(Context.SECURITY_CREDENTIALS, serverPass);

		try {
			DirContext contexte = new InitialDirContext(environnement);
			System.out.println("Sudo connexion au serveur : SUCCES");
			return contexte;
		
		} catch (NamingException e) {
			System.out.println("Sudo connexion au serveur : ECHEC");
			e.printStackTrace();
			DirContext contexte=null;
			return contexte;
			
		}
		
	}
	
	
	public static void disconnect(DirContext contexte) throws NamingException {	
		 contexte.close();
	}
	
	
	public static Utilisateur get_attributes(String uid, DirContext contexte) {	
		Utilisateur user= new Utilisateur ();
		try {
			//On recupere les attributs de uid
			Attributes attributes = contexte.getAttributes("uid="+uid+",ou=users,dc=example,dc=com");
			System.out.println("Recuperation des attributs de" + uid + " : SUCCES");
			user.setPrenom((attributes.get("cn")).toString().substring(4));
			user.setEmail((attributes.get("mail")).toString().substring(6));
			user.setNom((attributes.get("sn")).toString().substring(4));
			user.setQuestion((attributes.get("carLicense")).toString().substring(12));
			user.setReponse((attributes.get("initials")).toString().substring(10));
			user.setSecret((attributes.get("street")).toString().substring(8));
			user.setIdentifiant((attributes.get("uid")).toString().substring(4));
			Attribute userPassword = attributes.get("userPassword");
		 	String pwd = new String((byte[]) userPassword.get());
		 	System.out.println(pwd);
		 	user.setPwd(pwd);
		} catch (NamingException e) {
			System.out.println("Recuperation des attributs de "+uid+" : ECHEC");
			
			e.printStackTrace();
			return null;
		}
		return user;
	}
	public static boolean edit_user_password(DirContext contexte, String uid, String attributeValue ) {
		Attributes new_attrs = new BasicAttributes();
        //Attribute new_attr= new BasicAttribute("userPassword");
        //new_attr.add(attributeValue);
        //new_attrs.put(new_attr);
       new_attrs.put("userPassword",attributeValue);
        try {
			contexte.modifyAttributes("uid="+uid+",ou=users,dc=example,dc=com", DirContext.REPLACE_ATTRIBUTE, new_attrs);
			return true;
		} catch (NamingException e) {
			System.out.println("password modification: ECHEC");
			e.printStackTrace();
			return false;
		}
                       
	}

	public static void edit_user(DirContext contexte, String uid, String attributeType, String attributeValue ) {
		Attributes new_attrs = new BasicAttributes();
        Attribute new_attr= new BasicAttribute(attributeType);
        new_attr.add(attributeValue);
        new_attrs.put(new_attr);
       // new_attrs.put("cn", "ala");
        try {
			contexte.modifyAttributes("uid="+uid+",ou=users,dc=example,dc=com", DirContext.REPLACE_ATTRIBUTE, new_attrs);
		} catch (NamingException e) {
			System.out.println("Attributes modification: ECHEC");
			e.printStackTrace();
		}
                       
	}
		
}
