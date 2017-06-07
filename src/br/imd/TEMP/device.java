package br.imd.modelo;


/**
 * 
 * @author Ewerton Leandro de Sousa
 *
 *	Exemplo: id,date,user,pc,activity
 *			{S7A7-Y8QZ65MW-8738SAZP},01/04/2010 07:12:31,DTAA/RES0962,PC-3736,Connect
 *			{P7R6-C5TV18CT-1677DWWM},01/04/2010 08:20:17,DTAA/RES0962,PC-3736,Disconnect
 */
public class device extends no {
	
	private int activity = -1; //1=connect 0=desconnect
	
	public device(String id, String date, String id_user, String pc, int activity){
		super(id, date, id_user, pc);
		this.activity = activity;
	}
	
	public device(String id, String date, String id_user, String pc, String activity){
		super(id, date, id_user, pc);
		if(activity.equals("Connect")){
			this.activity = 1;
		}else{
			this.activity = 0;
		}
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}
	
	

}
