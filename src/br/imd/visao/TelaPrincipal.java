package br.imd.visao;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import br.imd.controle.ArquivoCSV;
import br.imd.modelo.Arvore;


public class TelaPrincipal extends JFrame implements ActionListener{
	
	// ARVORE
	Arvore arvore = new Arvore();
	
	// ARVORE PADRÃO
	Arvore arvorePadrao = new Arvore();
	
	JDesktopPane dtp = new JDesktopPane();
	
	JMenuBar mnbar = new JMenuBar();	
	
	JMenu arquivoMenu = new JMenu("Arquivo");
	JMenu analiseMenu = new JMenu("Analise");
	JMenu sobreMenu = new JMenu("Sobre");
	
	JMenuItem mCarregarCSV = new JMenuItem("Carregar CSV's");
	//JMenuItem mItem2 = new JMenuItem("Fornecedores");
	//JMenuItem mItem3 = new JMenuItem("Produtos");
	
	JMenuItem mAnalisePerfil = new JMenuItem("Analise Perfil");
	JMenuItem mAnaliseAnomalia = new JMenuItem("Analise de Anomalia");
	JMenuItem mGraficoUsuario = new JMenuItem("Gráfico de usuário");
	
	JMenuItem mSobre = new JMenuItem("Sobre");
	JMenuItem mSair = new JMenuItem("Sair");
	
			 		
	public TelaPrincipal(){		

		//URL url = this.getClass().getResource("/resources/icon9.gif");
		//dtp.setBackground(new Color(43, 44, 47));
		URL url = this.getClass().getResource("/resources/icon6.gif");
		dtp.setBackground(new Color(43, 44, 47));
	    Icon icon = new ImageIcon(url);
	    JLabel gif = new JLabel(icon);
		
	    //this.getContentPane().setBackground(new Color(43, 44, 47));
		Container ct = this.getContentPane();
		ct.setLayout(new BorderLayout());
			
		setJMenuBar(mnbar);
		mnbar.add(arquivoMenu);
		mnbar.add(analiseMenu);
		mnbar.add(sobreMenu);

		arquivoMenu.add(mCarregarCSV);
		//fileMenu.add(mItem2);
		//fileMenu.add(mItem3);
		
		analiseMenu.add(mAnalisePerfil);
		analiseMenu.add(mAnaliseAnomalia);
		analiseMenu.add(mGraficoUsuario);
		//fileRels.add(mItem7);
		
		sobreMenu.add(mSobre);
		sobreMenu.add(mSair);
		
		gif.setBounds(0,0,800,600);
		dtp.add(gif);
		ct.add(BorderLayout.CENTER, dtp);
		
		setLocation(200, 100);
		setSize(800,600);
		setResizable(false);
		setTitle("Sistema de Detecção de Ameaças Internas - v0.1 - Ewerton L. de Sousa");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// eventos
		mCarregarCSV.addActionListener(this);	
		//mItem2.addActionListener(this);
		//mItem3.addActionListener(this);
		
		mAnalisePerfil.addActionListener(this);	
		mAnaliseAnomalia.addActionListener(this);
		mGraficoUsuario.addActionListener(this);
		//mItem7.addActionListener(this);	
		
		mSair.addActionListener(this);
		mSobre.addActionListener(this);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mCarregarCSV){
			TelaCarregamentoCSV tlCarregamentoCSV = new TelaCarregamentoCSV(arvore);
			dtp.add(tlCarregamentoCSV);
			tlCarregamentoCSV.setVisible(true);
		}else if(e.getSource() == mAnalisePerfil){
			TelaVisualizacaoUsuarios tlVisualizaUsuarios = new TelaVisualizacaoUsuarios(arvore);
			dtp.add(tlVisualizaUsuarios);
			tlVisualizaUsuarios.setVisible(true);
		}else if(e.getSource() == mAnaliseAnomalia){
			
		}else if(e.getSource() == mGraficoUsuario){
			TelaGraficoUsuario tlGraficoUsuario = new TelaGraficoUsuario(arvore, 13);
			dtp.add(tlGraficoUsuario);
			tlGraficoUsuario.setVisible(true);
		}else if(e.getSource() == mSobre){
			
		}else if(e.getSource() == mSair){
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.setVisible(true);
	}
}
