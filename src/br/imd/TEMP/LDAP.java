package br.imd.modelo;

/**
 * 
 * @author Desktop
 *
 *		Exemplo: employee_name,user_id,Domain,Email,Role
 *				Aaron C Donovan,ACD0647,dtaa.com,Aaron.C.Donovan@dtaa.com,Senior Manger
 *				Abbot R Cherry,ARC0677,dtaa.com,Abbot.R.Cherry@dtaa.com,Security
 */
public class LDAP {
	
	private String employee_name;
	private String id_user;
	private String domain;
	private String email;
	private String role;
	
	public LDAP(String employee_name, String id_user, String domain, String email, String role){
		this.employee_name = employee_name;
		this.id_user = id_user;
		this.domain = domain;
		this.email = email;
		this.role = role;
	}

}
