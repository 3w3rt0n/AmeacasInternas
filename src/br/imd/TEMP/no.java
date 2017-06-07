package br.imd.modelo;

public class no {
	
	private String id;
	private String id_user;
	private String date;
	private String pc;
	
	
	//Construtor
	public no(String id, String date, String id_user, String pc){
		this.id = id;
		this.id_user = id_user;
		this.date = date;
		this.pc = pc;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	

}
