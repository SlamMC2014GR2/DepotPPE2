package entite;
import java.sql.Date;

public class Vacation {
	private Integer noatelier;
	private Integer novacation;
	private Date datedebut;
	private Date datefin;
	
	// constructeur
	
	public Vacation(Integer wnoat, Integer wnovac, Date dd, Date df){
		noatelier = wnoat;
		novacation = wnovac;
		datedebut = dd;
		datefin = df;
	}
	
	public Integer getNoatelier(){
		return noatelier;
	}
	
	public Integer getNovacation(){
		return novacation;
	}
	
	public Date getDatedebut(){
		return datedebut;
	}
	
	public Date getDatefin(){
		return datefin;
	}
	
	public void setDatedebut(Date wdatedeb){
		datedebut = wdatedeb;	
	}

	public void setDatefin(Date wdatefin){
		datefin = wdatefin;
	}
	
	public String req_InsertVacation(){
		return "EXECUTE AjoutVacation "+this.getNoatelier()+", '"+this.getDatedebut()+"', '"+this.getDatefin()+"'";
	}
	
	public String req_UpdateVacation(){
		return "EXECUTE ModifVacation "+this.getNoatelier()+", "+this.getNovacation()+", '"+this.getDatedebut()+"', '"+this.getDatefin()+"'";
	}
}
