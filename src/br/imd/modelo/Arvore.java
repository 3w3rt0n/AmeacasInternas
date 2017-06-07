package br.imd.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Arvore {
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private boolean ordenada = false;
	
	
	/************************************************************************
	* 
	*	Funções para inserir usuário
	*
	*
	*************************************************************************/
	public boolean inserirUsuario(Usuario usuario){
		if(usuario != null && !usuarios.contains(usuario)){
			usuarios.add(usuario);
			//Collections.sort(usuarios); //Ordem o array toda vez que inserir um novo usuario
			ordenada = false;
			return true;		
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private Usuario buscaUsuarioSequencial(Usuario usuario){
		for(Usuario temp: usuarios){
			if(usuario.equals(temp)){
				return temp;
			}
		}
		return null;		
	}	
	
	@SuppressWarnings("unused")
	private Usuario buscaUsuarioSequencial(String user){
		for(Usuario temp: usuarios){
			if(temp.getUser().equalsIgnoreCase(user)){
				return temp;
			}
		}
		return null;		
	}	
	
	//Falta testa
	@SuppressWarnings("unused")
	private Usuario buscaUsuarioBinaria(Usuario usuario){
		
		if(!ordenada){
			Collections.sort(usuarios);
			System.out.println("[INFORMACAO] {Arvore} {buscaUsuarioBinario} - Ordendando Usuarios");
			ordenada = true;
		}
				     
		int inf = 0;     				// limite inferior (o primeiro índice de vetor em C é zero          )
		int sup = usuarios.size() - 1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
		int meio;
	    while (inf <= sup){
	    	
		    meio = (inf + sup)/2;
		    if (usuario.getUser().equalsIgnoreCase(usuarios.get(meio).getUser())){
		        return usuarios.get(meio);
		    }
		    if (usuario.getUser().compareToIgnoreCase(usuarios.get(meio).getUser()) < 0){  
		        sup = meio-1;
		    }else{
		        inf = meio+1;
		    }
		}
	    return null;  // não encontrado		
	}
	
	@SuppressWarnings("unused")
	private Usuario buscaUsuarioBinaria(String usuario){
		
		if(!ordenada){
			Collections.sort(usuarios);
			ordenada = true;
		}
				     
		int inf = 0;     				// limite inferior (o primeiro índice de vetor em C é zero          )
		int sup = usuarios.size() - 1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
		int meio;
	    while (inf <= sup){
	    	
		    meio = (inf + sup)/2;
		    if (usuario.equalsIgnoreCase(usuarios.get(meio).getUser())){
		        return usuarios.get(meio);
		    }
		    if (usuario.compareToIgnoreCase(usuarios.get(meio).getUser()) < 0){  
		        sup = meio-1;
		    }else{
		        inf = meio+1;
		    }
		}
	    return null;  // não encontrado		
	}
	
	/***************************************************************************
	 * 
	 * 	Funções inserir evento de Logon
	 * 
	 * 1º - Localizar o usuario, caso não localize retorne falso
	 * 2º - Verificar se já tem evento na data
	 * 	SIM: Busca o computador
	 *  NÃO: Inserir a data
	 * 3º - 
	 * 
	 * 
	 ***************************************************************************/	
	public boolean inserirLogon(String nomeUsuario, String data, String nomeComputador){
		System.out.println("[INFORMACAO] Inserir Logon");
		//Usuario usuario = buscaUsuarioSequencial(nomeUsuario);
		Usuario usuario = buscaUsuarioBinaria(nomeUsuario);
		System.out.println("[INFORMACAO] {inserirLogon} - Retorno da busca pelo usuario: " + usuario.getUser());
		if(usuario!=null){			
			Dia dia = usuario.buscaDiaBinaria(data);		
			if(dia==null){
				//System.out.println("Retorno busca dia: " + dia.getDate());
				//Inseri primeiro evento desse dia	
				dia = new Dia(data);
				dia.inserirComputadorLogon(nomeComputador); // falta ver como inserir o registro de logon IMPORTANTE
				usuario.inserirDia(dia); 
				ordenada = false;
				return true;
			}else{
				//Caso esse dia ainda tenha nenhum evento
				dia.inserirComputadorLogon(nomeComputador); // falta ver como inserir o registro de logon IMPORTANTE
				ordenada = false;
				return true;
			}
		}
		System.out.println("[ERRO] {Arvore} {inserirLogon} - retornou: falso");
		return false;
	}
	
	/***************************************************************************
	 * 
	 * 	Funções inserir evento de Logoff
	 * 
	 * 1º - Localizar o usuario, caso não localize retorne falso
	 * 2º - Verificar se já tem evento na data
	 * 	SIM: Busca o computador
	 *  NÃO: Inserir a data
	 * 3º - 
	 * 
	 * 
	 ***************************************************************************/	
	public boolean inserirLogoff(String nomeUsuario, String data, String nomeComputador){
		System.out.println("[INFORMACAO] Inserir Logon");
		//Usuario usuario = buscaUsuarioSequencial(nomeUsuario);
		Usuario usuario = buscaUsuarioBinaria(nomeUsuario);
		System.out.println("[INFORMACAO] {inserirLogon} - Retorno da busca pelo usuario: " + usuario.getUser());
		if(usuario!=null){			
			Dia dia = usuario.buscaDiaSequencial(data);		
			if(dia==null){
				//System.out.println("Retorno busca dia: " + dia.getDate());
				//Inseri primeiro evento desse dia	
				dia = new Dia(data);
				dia.inserirComputadorLogoff(nomeComputador); // falta ver como inserir o registro de logon IMPORTANTE
				usuario.inserirDia(dia); 
				ordenada = false;
				return true;
			}else{
				//Caso esse dia ainda tenha nenhum evento
				dia.inserirComputadorLogoff(nomeComputador); // falta ver como inserir o registro de logon IMPORTANTE
				ordenada = false;
				return true;
			}
		}
		System.out.println("[ERRO] {Arvore} {inserirLogon} - retornou: falso");
		return false;
	}
	
	/***************************************************************************
	 * 
	 * 	Funções inserir evento 
	 *  
	 * 1º - Localizar o usuario, caso não localize retorne falso
	 * 2º - Verificar se já tem evento na data
	 * 	SIM: Busca o computador
	 *  NÃO: Inserir a data
	 * 3º - 
	 * 
	 * 
	 ***************************************************************************/	
	public boolean inserirEvento(String nomeUsuario, String data, Computador computador){
		System.out.println("[INFORMACAO] Inserir Evento");
		//Usuario usuario = buscaUsuarioSequencial(nomeUsuario);
		Usuario usuario = buscaUsuarioBinaria(nomeUsuario);
		System.out.println("[INFORMACAO] {inserir Evento} - Retorno da busca pelo usuario: " + usuario.getUser());//------
		if(usuario!=null){			
			Dia dia = usuario.buscaDiaBinaria(data);		
			if(dia==null){//concluido
				//System.out.println("Retorno busca dia: " + dia.getDate());
				//Inseri primeiro evento desse dia	
				dia = new Dia(data);
				dia.inserirComputador(computador); // falta ver como inserir o registro de logon IMPORTANTE
				usuario.inserirDia(dia); 
				ordenada = false;
				return true;
			}else{
				//Caso esse dia já tenha evento
				Computador atual = dia.buscaComputadorSequencial(computador.getNomeComputador());		
				if(atual==null){//concluido
					System.out.println("[INFORMACAO] {Arvore} {inserirEvento} - Busca retornou: null  ");
					//Não foi cadastrado nenhum computador com esse nome
					dia.inserirComputador(computador);
					System.out.println("[INFORMACAO] {Arvore} {inserirEvento} - inserido dia com sucesso retorno true ");
					return true;
				}else{//falta implementar
					//Já existe computador com esse nome, atualizar registro
					//falta ver como atualiar o registro do evento IMPORTANTE
					//Atualiza computador atual
					System.out.println("[INFORMACAO] {Arvore} {inserirEvento} - ATUALIZAR EVENTO EM COMPUTADOR EXISTENTE :: ");
					if(computador.getConnects()>=1){
						atual.incrementeConnects();
						System.out.println("[INFORMACAO] C-");
					}
					if(computador.getDesconnects()>=1){
						atual.incrementeDesconnects();
						System.out.println("[INFORMACAO] D-");
					}
					if(computador.getLogons()>=1){
						atual.incrementeLogons();
						System.out.println("[INFORMACAO] L-");
					}
					if(computador.getLogoffs()>-1){
						atual.incrementeLogoffs();
						System.out.println("[INFORMACAO] LF-");
					}
					if(computador.getHttps().size()>=1){
						atual.incrementeHttps(computador.getHttps().get(0));
						System.out.println("[INFORMACAO] H-");
					}
					System.out.println("[INFORMACAO] {Arvore} {inserirEvento} - ATUALIZAR EVENTO EM COMPUTADOR EXISTENTE :: FIM");
					System.out.println("[INFORMACAO] {Arvore} {inserirEvento} - Busca retornou: " + computador.getNomeComputador());					
				}
				ordenada = false;
				return true;
			}
		}
		System.out.println("[ALERTA] {Arvore} {inserirEvento} - retornou: falso");
		return false;
	}
	
	/******************************************************************************
	 * 
	 * 
	 * Função de retorno de usuários
	 * 
	 * 
	 *****************************************************************************/
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	/******************************************************************************
	 * ----------------------------------------------------------------------------
	 * 
	 * Metodos para verificação de funcionamento
	 * 
	 * ----------------------------------------------------------------------------
	 ******************************************************************************/
	
	public void imprimirUsuariosConsole(){
		int id = 1;
		System.out.println("** Usuarios existentes na arvore (imprimirUsuarioConsole) **\n");
		for(Usuario temp: usuarios){
			System.out.println(id + " - " + temp.getUser());
			id++;
		}
	}
	
	public void imprimirDiaConsole(){
		int id = 1;
		System.out.println("** Usuarios e dias existentes na arvore (imprimirDiaConsole) **\n");
		for(Usuario temp: usuarios){
			System.out.println(id + " - " + temp.getUser());
			for(Dia tempDia: temp.getDias()){
				System.out.println("  - " + tempDia.getData());
			}
			id++;
		}
	}
}
