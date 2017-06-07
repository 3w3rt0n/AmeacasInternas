package br.imd.modelo;

import java.util.ArrayList;

public class Computador {
	
	private int[] horas = new int[24];
	private String nomeComputador;
	private int logons = 0; // cada classe vai ter a hora armazenada também
	private int logoffs = 0;
	private int connects = 0;
	private int desconnects = 0;
	private ArrayList<String> https = new ArrayList<String>();

	public Computador(String nomeComputador){
		this.nomeComputador = nomeComputador;		
	}	
	
	public String getNomeComputador() {
		return nomeComputador;
	}

	public int getLogons() {
		return logons;
	}

	public int getLogoffs() {
		return logoffs;
	}

	public int getConnects() {
		return connects;
	}

	public int getDesconnects() {
		return desconnects;
	}

	public ArrayList<String> getHttps() {
		return https;
	}

	public void incrementeLogons() {
		this.logons++;
	}

	public void incrementeLogoffs() {
		this.logoffs++;
	}

	public void incrementeConnects() {
		this.connects++;
	}

	public void incrementeDesconnects() {
		this.desconnects++;
	}

	public void incrementeHttps(String https) {
		this.https.add(https);
	}
	
	
	
}
