package entite;

public class Parametres 
{
	private String nomUtilisateur;
	private String motDePasse;
	private String serveurBD;
	private String driverSGBD;
	/*
	public Parametres (){
      	nomUtilisateur = "GR2";
	  	motDePasse = "GR2";		
	  	driverSGBD ="sun.jdbc.odbc.JdbcOdbcDriver";
	  	serveurBD = "jdbc:odbc:odbc_m2l";
	}
	
	*/
	
	public Parametres (){
      	nomUtilisateur = "Sim";
	  	motDePasse = "soyeuxjetaime";		
	  	driverSGBD ="sun.jdbc.odbc.JdbcOdbcDriver";
	  	serveurBD = "jdbc:odbc:ODBC_M2L_LOCAL";
	}
	
	
	public String getDriverSGBD() {
		return driverSGBD;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public String getServeurBD() {
		return serveurBD;
	}
	
}
