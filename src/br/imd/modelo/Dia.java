package br.imd.modelo;

import java.util.ArrayList;

public class Dia implements Comparable<Dia>{
	
	private String data;
	private ArrayList<Computador> computadores  = new ArrayList<Computador>();
	private int[] horas = new int[24];
	
	public Dia(String data){
		this.data = data;
	}
	
	
	/**
	 *
	 * @param String com o nome do computador 
	 * @return O objeto caso exista Computador caso contrario retorna null
	 * 
	 * 
	 */
	public Computador buscaComputadorSequencial(String nomeComputador){
		for(Computador temp: computadores){
			if(temp.getNomeComputador().equalsIgnoreCase(nomeComputador)){
				System.out.println("[INFORMACAO] {Dia} {buscaComputadorSequencial} - Retorno busca: " + temp.getNomeComputador());
				return temp;
			}
		}
		System.out.println("[INFORMACAO] {Dia} {buscaComputadorSequencial} - Retorno busca: null ");
		return null;		
	}
	
	public boolean inserirComputadorLogon(String nomeComputador){
		System.out.println("[INFORMACAO] {Dia} {inserirComputador} - Inicio ");
		Computador computador = buscaComputadorSequencial(nomeComputador);		
		if(computador==null){
			System.out.println("[INFORMACAO] {Dia} {inserirComputador} - Busca retornou: null  " );
			//Não foi cadastrado nenhum computador com esse nome
			computador = new Computador(nomeComputador);
			computador.incrementeLogons();
			computadores.add(computador);
			System.out.println("[INFORMACAO] {Dia} {inserirComputador} - inserido dia com sucesso retorno true " );
			return true;
		}else{
			//Já existe computador com esse nome, atualizar registro
			//falta ver como atualiar o registro do evento IMPORTANTE
			computador.incrementeLogons();
			System.out.println("[INFORMACAO] {Dia} {inserirComputador} - Busca retornou: " + computador.getNomeComputador() );
			
		}
		System.out.println("[ALERTA] {Dia} {inserirComputador} - retorna falso " );
		return false;
	}
	
	public boolean inserirComputadorLogoff(String nomeComputador){
		System.out.println("[INFORMACAO] {Dia} {inserirComputador} - Inicio ");
		Computador computador = buscaComputadorSequencial(nomeComputador);		
		if(computador==null){
			System.out.println("[INFORMACAO] {Dia} {inserirComputador} - Busca retornou: null  " );
			//Não foi cadastrado nenhum computador com esse nome
			computador = new Computador(nomeComputador);
			computador.incrementeLogoffs();
			computadores.add(computador);
			System.out.println("[INFORMACAO] {Dia} {inserirComputador} - inserido dia com sucesso retorno true " );
			return true;
		}else{
			//Já existe computador com esse nome, atualizar registro
			//falta ver como atualiar o registro do evento IMPORTANTE
			computador.incrementeLogoffs();
			System.out.println("[INFORMACAO] {Dia} {inserirComputador} - Busca retornou: " + computador.getNomeComputador() );
			
		}
		System.out.println("[ALERTA] {Dia} {inserirComputador} - retorna falso " );
		return false;
	}
	
	
	
	public String getData() {
		return data;
	}
	
	public ArrayList<Computador> getComputadores(){
		return computadores;
	}
	
	public void inserirComputador(Computador computador){
		computadores.add(computador);
	}
	
	// Implementa da interface Comparable para permitir a utilização do metodo Sort
	public int compareTo(Dia temp) {
        if (this.data.compareTo(temp.data) < 0) {
            return -1;
        }
        if (this.data.compareTo(temp.data) > 0) {
            return 1;
        }
        return 0;
    }
	
	

}
