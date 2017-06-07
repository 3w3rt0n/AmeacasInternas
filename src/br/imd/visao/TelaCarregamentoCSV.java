package br.imd.visao;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.imd.controle.ArquivoCSV;
import br.imd.modelo.*;

/**
 * 
 * @author Ewerton L. de Sousa
 *
 */
public class TelaCarregamentoCSV extends JInternalFrame implements ActionListener{
	
	// ARVORE
    Arvore arvore;;
			
	// rótulos
	JLabel lCarregamentoCSV = new JLabel("Selecione os arquivos CSV's");
	JLabel lLDAP = new JLabel("LDAP:");
	JLabel lDevice = new JLabel("Device:");
	JLabel lLogon = new JLabel("Logon:");
	JLabel lHTTP = new JLabel("HTTP:");	
			
	// campos
	JTextField tLDAP = new JTextField();
	JTextField tDevice = new JTextField();
	JTextField tLogon = new JTextField();
	JTextField tHTTP = new JTextField();
	
	// botões
	JButton bLDAP = new JButton("...");
	JButton bDevice = new JButton("...");
	JButton bLogon = new JButton("...");
	JButton bHTTP = new JButton("...");
			
	// botões	
	JButton bCancelar = new JButton("Cancelar");
	JButton bConfirmar = new JButton("Carregar");
	
	//File Chooser
	/**
	 * http://www.caelum.com.br/apostila-java-testes-xml-design-patterns/interfaces-graficas-com-swing/#5-6-exercicios-escolhendo-o-xml-com-jfilechooser
	 * http://www.devmedia.com.br/jtextfield-com-jfilechooser-java-swing-componentes-netbeans-parte-4/21572
	 */
	JFileChooser file = new JFileChooser(); 
  

	public TelaCarregamentoCSV(Arvore arvore)  {
		super("Carregamento dos CSV's",false,true);		
		
		this.arvore = arvore;
		
		Container ct = this.getContentPane();
		ct.setLayout(null);
			
		// coordenadas
		lCarregamentoCSV.setBounds(85, 10, 250, 25);
		lLDAP.setBounds(10,50,200,25);
		tLDAP.setBounds(55,50,200,25);
		bLDAP.setBounds(270,50,30,25);
		lLogon.setBounds(10,80,200,25);
		tLogon.setBounds(55,80,200,25);
		bLogon.setBounds(270,80,30,25);
		lDevice.setBounds(10,110,200,25);
		tDevice.setBounds(55,110,200,25);
		bDevice.setBounds(270,110,30,25);		
		lHTTP.setBounds(10,140,200,25);
		tHTTP.setBounds(55,140,200,25);
		bHTTP.setBounds(270,140,30,25);

		// idem
		bCancelar.setBounds(30,180,120,30);
		bConfirmar.setBounds(180,180,120,30);		
		
		//File Chooser
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		file.setFileFilter(new FileNameExtensionFilter("Apenas CSV", "csv"));
		
		// adicionando componentes
		ct.add(lCarregamentoCSV);
		ct.add(lLDAP);
		ct.add(tLDAP);
		ct.add(bLDAP);
		ct.add(lLogon);
		ct.add(tLogon);
		ct.add(bLogon);
		ct.add(lDevice);
		ct.add(tDevice);
		ct.add(bDevice);
		ct.add(lHTTP);
		ct.add(tHTTP);
		ct.add(bHTTP);		
		ct.add(bConfirmar);
		ct.add(bCancelar);
				
		
		// evento dos botões
		bConfirmar.addActionListener(this);		
		bCancelar.addActionListener(this);
		bDevice.addActionListener(this);
		bHTTP.addActionListener(this);
		bLDAP.addActionListener(this);
		bLogon.addActionListener(this);
		
		// especificações do formulário
		setSize(350,280);
		setTitle("Carregamento dos CSV's");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bConfirmar){
			
			if(tLDAP.getText().isEmpty() || tLogon.getText().isEmpty() || tDevice.getText().isEmpty() || tHTTP.getText().isEmpty()){
				
				JOptionPane.showMessageDialog(null, "Preenchar todos os campos.", "Error...", JOptionPane.WARNING_MESSAGE);
				
			}else{
			
				TelaDialogProgress dlgProgress = new TelaDialogProgress();		
				dlgProgress.setLocation(400, 200);
				
		        dlgProgress.setVisible(true);
				dlgProgress.setAlwaysOnTop(true);
		        
				//Carregar o arquivo CSV
				ArquivoCSV csv = new ArquivoCSV();
				
							
				// http://www.caelum.com.br/apostila-java-testes-xml-design-patterns/apendice-mais-swing-e-recursos-avancados/#11-4-exercicio-escolhendo-indicadores-para-o-grafico
				SwingWorker sw1 = new SwingWorker(){				
					@Override
					protected Void doInBackground() throws Exception{						
						csv.lerCSV_LDAP(tLDAP.getText(), arvore, dlgProgress);
						dlgProgress.incrementaProgresso(25);
						csv.lerCSV_logon(tLogon.getText(), arvore, dlgProgress);
						dlgProgress.incrementaProgresso(25);	
						csv.lerCSV_device(tDevice.getText(), arvore, dlgProgress);
						dlgProgress.incrementaProgresso(25);
						csv.lerCSV_device(tHTTP.getText(), arvore, dlgProgress);
						dlgProgress.incrementaProgresso(25);
						return null;
					}		
					
			        @Override
			        protected void done() {
			        	dlgProgress.incrementaProgresso(100);		        	
			        }				
				};
				sw1.execute();
				
				//Fecha janela ao fim do carregamento
				this.dispose();
			
			}	
			
			
		}else if(e.getSource() == bCancelar){
			System.out.println("cancelar");
		}else if(e.getSource() == bDevice){
			int i= file.showOpenDialog(null);
			if (i==1){
				tDevice.setText("");
			} else {
				File arquivo = file.getSelectedFile();
			    tDevice.setText(arquivo.getPath());
			}
		}else if(e.getSource() == bHTTP){
			int i= file.showOpenDialog(null);
			if (i==1){
				tHTTP.setText("");
			} else {
				File arquivo = file.getSelectedFile();
			    tHTTP.setText(arquivo.getPath());
			}
		}else if(e.getSource() == bLDAP){
			int i= file.showOpenDialog(null);
			if (i==1){
				tLDAP.setText("");
			} else {
				File arquivo = file.getSelectedFile();
			    tLDAP.setText(arquivo.getPath());
			}
		}else if(e.getSource() == bLogon){
			int i= file.showOpenDialog(null);
			if (i==1){
				tLogon.setText("");
			} else {
				File arquivo = file.getSelectedFile();
			    tLogon.setText(arquivo.getPath());
			}
		}
	}
	
}
