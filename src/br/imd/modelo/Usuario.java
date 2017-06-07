package br.imd.modelo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * 
 * @author Desktop
 *
 *
 * Referência Sort: http://blog.caelum.com.br/ordenando-colecoes-com-comparable-e-comparator/
 */
public class Usuario implements Comparable<Usuario> {
	
	private String employee_name;
	private String id_user;
	private String domain;
	private String email;
	private String role;
	private String user; 
	
	private boolean ordenada = false;
	private ArrayList<Dia> dias = new ArrayList<Dia>();
	private int[] horas = new int[24];
		
	
	//Construtor
	public Usuario(String employee_name, String id_user, String domain, String email, String role){
		this.employee_name = employee_name;
		this.id_user = id_user;
		this.domain = domain.toUpperCase();
		this.email = email;
		this.role = role;
		this.user = this.domain.replace(".COM","") + "/" + id_user; //se for diferente de ".com" vai dá erro
	}
	
	
	/**
	 *
	 * @param String data formato mm/dd/yy
	 * @return Dia
	 * 
	 * 
	 */
	@SuppressWarnings("unused")
	public Dia buscaDiaSequencial(String data){
		System.out.println("[INFORMACAO] {Usuario} {buscaDiaSequencial} - iniciado busca: " + data);
		if(dias!=null){
			for(Dia temp: dias){
				if(temp.getData().equalsIgnoreCase(data)){
					System.out.println("[INFORMACAO] {Usuario} {buscaDiaSequencial} - data localizada: " + temp.getData());
					return temp;
				}
			}
		}
		System.out.println("[ALERTA] {Usuario} {buscaDiaSequencial} - retorna null");
		return null;		
	}
	
	
	/*************************************************************************
	 * 
	 * @param data
	 * @return
	 * 
	 * 
	 *************************************************************************/
	@SuppressWarnings("unused")
	public Dia buscaDiaBinaria(String data){
		
		if(!ordenada){
			Collections.sort(dias);
			ordenada = true;
		}
				     
		int inf = 0;     				// limite inferior (o primeiro índice de vetor em C é zero          )
		int sup = dias.size() - 1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
		int meio;
	    while (inf <= sup){
	    	
		    meio = (inf + sup)/2;
		    if (data.equalsIgnoreCase(dias.get(meio).getData())){
		        return dias.get(meio);
		    }
		    if (data.compareToIgnoreCase(dias.get(meio).getData()) < 0){  
		        sup = meio-1;
		    }else{
		        inf = meio+1;
		    }
		}
	    return null;  // não encontrado		
	}
	
	public boolean inserirDia(Dia dia){
		//Analisar empacto no desempenho dessa segunda verificação
		//if(dia!=null && !dias.contains(dia)){
		System.out.println("[INFORMACAO] {Usuario} {inserirDia} - verificando se o dia é null  " );
		if(dia!=null){
			System.out.println("[INFORMACAO] {Usuario} {inserirDia} - dia não é nulo  " );
			ordenada = false;
			dias.add(dia);
			System.out.println("[INFORMACAO] {Usuario} {inserirDia} - inserido dia com sucesso  " );
			return true;
		}	
		System.out.println("[ALERTA] {Usuario} {inserirDia} - falha ao inserir dia " );
		return false;
	}
	
	public boolean inserirComputador(Computador computador){
		
		
		return false;
	}
	
	
	
	
	
	
	
	
	public String getUser() {
		return user;
	}
	
	
	//Função de teste insecção 
	public ArrayList<Dia> getDias(){
		return dias; 
	}











	public void adicionarEvento(int horaEvento){
		if(horaEvento >= 0 && horaEvento < 24)
			horas[horaEvento]++;
	}
	
	
	
	
	// Implementa da interface Comparable para permitir a utilização do metodo Sort
	public int compareTo(Usuario temp) {
        if (this.user.compareTo(temp.user) < 0) {
            return -1;
        }
        if (this.user.compareTo(temp.user) > 0) {
            return 1;
        }
        return 0;
    }

}
