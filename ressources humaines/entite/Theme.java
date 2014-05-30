package entite;

public class Theme {
	
	private Integer noatelier;
	private Integer notheme;
	private String libelletheme;
	
	//Construct'
	
	public Theme(Integer wnoat, Integer wnotheme, String wlib){
		noatelier = wnoat;
		notheme = wnotheme;
		libelletheme = wlib;
	}
	
	public Integer getNoatelier(){
		return noatelier;
	}
	
	public Integer getNotheme(){
		return notheme;
	}
	
	public String getLibTheme(){
		return libelletheme;
	}
	
	public void setLibTheme(String wlibTheme){
		libelletheme = wlibTheme;
	}
	
	public String req_InsertTheme(){
		return "EXECUTE AjoutTheme "+this.getNoatelier()+", "+this.getLibTheme();
	}
	

}
