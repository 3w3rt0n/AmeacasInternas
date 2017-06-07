package br.imd.modelo;


/**
 * 
 * @author Desktop
 *
 *		Exemplo: id,date,user,pc,activity
 *				{Y6O4-A7KC67IN-0899AOZK},01/04/2010 00:10:37,DTAA/KEE0997,PC-1914,Logon
 * 				{O5Y6-O7CJ02JC-6704RWBS},01/04/2010 00:52:16,DTAA/KEE0997,PC-1914,Logoff
 *
 */
public class logon extends no{
	
	private int activity = -1; //1=logon 0=logoff
	
	public logon(String id, String date, String id_user, String pc, int activity){
		super(id, date, id_user, pc);
		this.activity = activity;
	}
	
	public logon(String id, String date, String id_user, String pc, String activity){
		super(id, date, id_user, pc);
		if(activity.equals("Logon")){
			this.activity = 1;
		}else{
			this.activity = 0;
		}
	}

}
