package br.imd.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class TelaDialogProgress extends JDialog{
	
	private int qtdU = 0;
	private int qtdL = 0;
	private int qtdD = 0;
	private int qtdH = 0;
	
	private Timer relogio;
    private int minutos;
    private int segundos;
	
	JPanel panelProgress = new JPanel();
    JProgressBar progressBar = new JProgressBar(0, 100);
    
    JLabel msgUsuarios = new JLabel("Carregando Usuarios...");
    JLabel qtdUsuarios = new JLabel("Usuários: " + qtdU);
    JLabel msgLogons = new JLabel("Carregando eventos de logon...");
    JLabel qtdLogons = new JLabel("Logons: " + qtdL);
    JLabel msgDevices = new JLabel("Carregando eventos de devices...");
    JLabel qtdDevices = new JLabel("Device: " + qtdD);
    JLabel msgHTTPs = new JLabel("Carregando URL's...");
    JLabel qtdHTTPs = new JLabel("Sites: " + qtdH);
    JLabel lCronometro = new JLabel("Tempo: 00:00 seg" );
    JButton btn = new JButton("Ok");
	
	public TelaDialogProgress(){	
		
		this.getContentPane().setLayout(null);
		
		URL url = this.getClass().getResource("/resources/loading21.gif");
	    Icon icon = new ImageIcon(url);
	    JLabel gif = new JLabel(icon);
	    gif.setBounds(20, 20, 100, 100);	
	    
	    lCronometro.setBounds(20, 100, 200, 50);
	    
	    msgUsuarios.setBounds(150, 5, 200, 100);
	    qtdUsuarios.setBounds(150, 20, 200, 100);	
	    msgLogons.setBounds(150, 35, 200, 100);
	    qtdLogons.setBounds(150, 50, 200, 100);
	    msgDevices.setBounds(150, 65, 200, 100);
	    qtdDevices.setBounds(150, 80, 200, 100);
	    msgHTTPs.setBounds(150, 95, 200, 100);
	    qtdHTTPs.setBounds(150, 110, 200, 100);
	   
	    btn.setBounds(150, 180, 80, 30);
	    btn.setVisible(false);
	    btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Fecha janela ao fim do carregamento
				dispose();
			}
		});
	    
	    //Configurações da barra de progresso
	    progressBar.setStringPainted(true);
	    progressBar.setSize(new Dimension(200, 40));
	    progressBar.setValue(0);
	    progressBar.setString(progressBar.getValue()+"%");
	    
	    panelProgress.setBounds(150, 20, 150, 50);
	    panelProgress.add(progressBar);
	    
	   
	    this.add(msgUsuarios);
	    this.add(qtdUsuarios);
	    this.add(msgLogons);
	    this.add(qtdLogons);
	    this.add(msgDevices);
	    this.add(qtdDevices);
	    this.add(msgHTTPs);
	    this.add(qtdHTTPs);
	    this.add(panelProgress); 
	    this.add(lCronometro);
	    this.add(gif);
	    this.add(btn);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.setSize(370, 250);
	    this.setTitle("Carregando...");
	    this.setResizable(false);
	    
	    relogio = new Timer(1000, new ActionListener() {  
	        public void actionPerformed(ActionEvent e) {
	        	segundos++;
	            if(segundos>=60){
	            	segundos=0;
	            	minutos++;
	            }
	            String s = String.format("Tempo: %02d:%02d seg", minutos, segundos);  
	            lCronometro.setText(s);  
	        }  
	    });  
	    relogio.start();
	}
	
	public void incrementaProgresso(int porcetagem){
		if(progressBar.getValue()>=100){
			relogio.stop();
			btn.setVisible(true);
		}else{
			progressBar.setValue(progressBar.getValue() + porcetagem);
			progressBar.setString(progressBar.getValue() + "%");
			if(progressBar.getValue()>=100){
				relogio.stop();
				btn.setVisible(true);
			}
		}
	}
	
	public void concluidoUsuarios(){
		msgUsuarios.setText("Usuários carregados.");
	}
	
	public void concluidoLogons(){
		msgLogons.setText("Eventos de Logons carregados.");
	}
	
	public void concluidoDevices(){
		msgDevices.setText("Eventos de Devices carregados.");
	}
	
	public void concluidoHTTPs(){
		msgHTTPs.setText("Sites carregados.");
	}
	
	public void incrementaUsuarios(){
		qtdU++;
		qtdUsuarios.setText("Dados: " + qtdU);
	}
	
	public void incrementaLogons(){
		qtdL++;
		qtdLogons.setText("Dados: " + qtdL);
	}
	
	public void incrementaDevices(){
		qtdD++;
		qtdDevices.setText("Dados: " + qtdD);
	}
	
	public void incrementaHTTPs(){
		qtdH++;
		qtdHTTPs.setText("Dados: " + qtdH);
	}
	
}
