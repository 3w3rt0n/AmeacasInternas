package br.imd.modelo;


/**
 * 
 * @author Desktop
 *
 *		Exemplo: id,date,user,pc,url
 *				{M8H9-W9NL75TH-1322KOLO},01/04/2010 07:08:47,DTAA/AMA0606,PC-1514,"http://cnet.com"
 *				{V0E1-R0FE91SC-2381GTDZ},01/04/2010 07:35:19,DTAA/DBM0698,PC-1444,"http://force.open.com"
 *
 */
public class http extends no{

	private	String url;
	
	public http(String id, String date, String id_user, String pc, String url){
		super(id, date, id_user, pc);
		this.url = url;
	}
	
}
