package br.imd.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.imd.modelo.*;
import br.imd.visao.TelaDialogProgress;


/**
 * 
 * @author Ewerton L. de Sousa
 * 
 * Referencia
 * {@link http://brunobrasilweb.com.br/ler-arquivos-csv-em-java-de-forma-simples}
 */
public class ArquivoCSV {
	
	public int lerCSV_LDAP(String nome_do_arquivo_csv, Arvore arvore, TelaDialogProgress dlgProgress){
				
		int qtd = 0;
			
		try {
			
			BufferedReader csv = new BufferedReader(new FileReader(nome_do_arquivo_csv));
			String line = "";	
		
			while ((line = csv.readLine()) != null) {
			    String[] row = line.split(",");
			    Usuario usuario = new Usuario(row[0], row[1], row[2], row[3], row[4]);
			    arvore.inserirUsuario(usuario);		
				dlgProgress.incrementaUsuarios();
			    qtd++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dlgProgress.concluidoUsuarios();
			return qtd;
		}
	}
	
	
	/*
	 * 
	 * Formato do arquivo de entrada: id,date,user,pc,activity
	 * Exemplo de entrada: {Y6O4-A7KC67IN-0899AOZK},01/04/2010 00:10:37,DTAA/KEE0997,PC-1914,Logon
	 * 
	 * 
	 * Em desenvolvimento 80%
	 * 
	 */
	public int lerCSV_logon(String nome_do_arquivo_csv,  Arvore arvore, TelaDialogProgress dlgProgress){
				
		int qtd = 0;
		
		try {
			
			BufferedReader csv = new BufferedReader(new FileReader(nome_do_arquivo_csv));
			String line = "";	
		
			while ((line = csv.readLine()) != null) {
				
				// line = {Y6O4-A7KC67IN-0899AOZK},01/04/2010 00:10:37,DTAA/KEE0997,PC-1914,Logon
				
			    String[] row = line.split(",");
			    
			    // row[0] = {Y6O4-A7KC67IN-0899AOZK} - não utilizado
				// row[1] = 01/04/2010 00:10:37 	 - necessario separar a data da hora
				// row[2] = DTAA/KEE0997 			 - user
				// row[3] = PC-1914 				 - nomeComputador
				// row[4] = Logon					 - Evento
			    
			    // row[1] = 01/04/2010 00:10:37 	 - necessario separar a data da hora
			    
			    String[] data = row[1].split(" "); //separação data da hora
			    
			    // data[0] = 01/04/2010	- data
			    // data[1] = 00:10:37 	- hora
			    boolean teste = false;
			    if(row[4].equalsIgnoreCase("Logon"))
			    	teste = arvore.inserirLogon(row[2], data[0], row[3]); // NÃO ESTÁ INSERIDO O EVENTO DE LOGON AINDA VERIFICAR COM PROFESSOR	
			    else
			    	teste = arvore.inserirLogoff(row[2], data[0], row[3]); // NÃO ESTÁ INSERIDO O EVENTO DE LOGON AINDA VERIFICAR COM PROFESSOR
			    
				dlgProgress.incrementaLogons();
			    qtd++;
			    
			    //Comandos de verificação de funcionamento do metodo
			    System.out.println("[INFORMACAO] {lerCSV_Logon}");
			    System.out.println("[INFORMACAO] {lerCSV_Logon} :: " + qtd + " - USER: " + row[2] + " - COMPUTADOR: " + row[3] + " - DATA: " + data[0] + " - EVENTO: " + row[4]);
			    System.out.println(teste ? "[INFORMACAO] {lerCSV_Logon} :: inserido com Sucesso!" : "[ALERTA] {lerCSV_Logon} :: Falha ao inserir");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dlgProgress.concluidoLogons();
			return qtd;
		}
	}


	/*
	 * 
	 * Formato do arquivo de entrada: id,date,user,pc,activity
	 * Exemplo de entrada: {S7A7-Y8QZ65MW-8738SAZP},01/04/2010 07:12:31,DTAA/RES0962,PC-3736,Connect
	 * 
	 * 
	 * Em desenvolvimento 10%
	 * 
	 */
	public int lerCSV_device(String nome_do_arquivo_csv,  Arvore arvore, TelaDialogProgress dlgProgress){
			
			System.out.println("[INFORMACAO] {lerCSV_Evento}");
			int qtd = 0;
			
			try {
				
				BufferedReader csv = new BufferedReader(new FileReader(nome_do_arquivo_csv));
				String line = "";	
			
				while ((line = csv.readLine()) != null) {
					
					// line = {Y6O4-A7KC67IN-0899AOZK},01/04/2010 00:10:37,DTAA/KEE0997,PC-1914,Logon
					
				    String[] row = line.split(",");
				    
				    // row[0] = {Y6O4-A7KC67IN-0899AOZK} - não utilizado
					// row[1] = 01/04/2010 00:10:37 	 - necessario separar a data da hora
					// row[2] = DTAA/KEE0997 			 - user
					// row[3] = PC-1914 				 - nomeComputador
					// row[4] = Connect					 - Evento
				    
				    // row[1] = 01/04/2010 00:10:37 	 - necessario separar a data da hora
				    
				    String[] data = row[1].split(" "); //separação data da hora
				    
				    // data[0] = 01/04/2010	- data
				    // data[1] = 00:10:37 	- hora
				    boolean teste = false;
				    
				    Computador computador = new Computador(row[3]);
				    if(row[4].equalsIgnoreCase("Connect")){
				    	computador.incrementeConnects();
				    	dlgProgress.incrementaDevices();
				    	System.out.println("[INFORMACAO] C");
				    }else if(row[4].equalsIgnoreCase("Disconnect")){
				    	computador.incrementeDesconnects();
				    	dlgProgress.incrementaDevices();
				    	System.out.println("[INFORMACAO] D");
				    }else if(row[4].equalsIgnoreCase("Logon")){
				    	computador.incrementeDesconnects();
				    	dlgProgress.incrementaLogons();
				    	System.out.println("[INFORMACAO] L");
				    }else if(row[4].equalsIgnoreCase("Logoff")){
				    	computador.incrementeDesconnects();
				    	dlgProgress.incrementaLogons();
				    	System.out.println("[INFORMACAO] LF");
				    }else{
				    	computador.incrementeHttps(row[4]);
				    	dlgProgress.incrementaHTTPs();
				    	System.out.println("[INFORMACAO] SITE");
				    }	
				    	
				    teste = arvore.inserirEvento(row[2], data[0], computador); 
				    qtd++;
				    
				    //Comandos de verificação de funcionamento do metodo				    
				    System.out.println("[INFORMACAO] {lerCSV_Evento} :: " + qtd + " - USER: " + row[2] + " - COMPUTADOR: " + row[3] + " - DATA: " + data[0] + " - EVENTO: " + row[4]);
				    System.out.println(teste ? "[INFORMACAO] {lerCSV_Evento} :: inserido com Sucesso!" : "[ALERTA] {lerCSV_Logon} :: Falha ao inserir");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				dlgProgress.concluidoLogons();
				return qtd;
			}
	}
	
	/*
	public ArrayList<http> lerCSV_http(String nome_do_arquivo_csv){
		
		ArrayList<http> http = new ArrayList<http>();
		http noHTTP;
		
		try {
			
			BufferedReader csv = new BufferedReader(new FileReader(nome_do_arquivo_csv));
			String line = "";	
		
			while ((line = csv.readLine()) != null) {
			    // "," ou ";" de acordo com o arquivo
			    String[] row = line.split(",");
			    noHTTP = new http(row[0], row[1], row[2], row[3], row[4]);
			    http.add(noHTTP);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return http;
		}
	}
	
	
	
	
	*/

}
