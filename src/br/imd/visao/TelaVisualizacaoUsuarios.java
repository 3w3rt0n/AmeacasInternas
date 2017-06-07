package br.imd.visao;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.imd.modelo.Arvore;
import br.imd.modelo.Computador;
import br.imd.modelo.Dia;
import br.imd.modelo.Usuario;

/**
 *  
 * @author Ewerton
 * 
 * Referencia:
 * - http://www.devmedia.com.br/jtable-aprendendo-a-utilizar-tabelas-em-java/30733
 * - http://www.douglaspasqua.com/2012/02/04/java-tips-operacoes-com-jtable/
 * - http://www.douglaspasqua.com/2012/05/24/java-tips-operacoes-com-jtable-parte-2/
 * - http://www.douglaspasqua.com/2012/12/18/java-tips-operacoes-com-jtable-parte-3/
 * - http://www.mauda.com.br/?p=201
 *
 */

public class TelaVisualizacaoUsuarios extends JInternalFrame implements ActionListener {
	
	ArrayList<Dia> dias;
	ArrayList<Computador> computadores;
	
	public TelaVisualizacaoUsuarios(Arvore arvore) {
		super("Tela de visualização de usuários",false,true);		
		
		Container ct = this.getContentPane();
		ct.setLayout(null);
		
		//rotulos
		JLabel lUsuarios = new JLabel("Usuário:");
		JLabel lDatas = new JLabel("Data (mm/dd/yyyy):");
		JLabel lQtdUsuarios = new JLabel("Usuários registrador: ");
		JLabel lQtdDatas = new JLabel("Datas registradas: ");	
		
		// campos
		JTextField tUsuarios = new JTextField();
		JTextField tDatas = new JTextField();
		
		// botões
		JButton bUsuarios = new JButton("Buscar");
		JButton bDatas = new JButton("Buscar");
		
		lUsuarios.setBounds(20,5,200,20);
		tUsuarios.setBounds(20,25,130,20);
		bUsuarios.setBounds(150,25,80,20);
		lDatas.setBounds(240,5,200,20);
		tDatas.setBounds(240,25,130,20);
		bDatas.setBounds(370,25,80,20);
		
		lQtdUsuarios.setBounds(20,450,200,25);
		lQtdDatas.setBounds(240,450,200,25);
		
		//JPanel jpanel = new JPanel();
		//this.setContentPane(jpanel);		
		
		//Tabela usuarios1
		JTable tabelaUsuarios = new JTable(0, 1) {
		    public boolean isCellEditable(int rowIndex, int vColIndex) {
		             return false;
		    }
		};
		JScrollPane scrollUsuarios = new JScrollPane(tabelaUsuarios);
		scrollUsuarios.setBounds(20, 50, 210, 400);
		tabelaUsuarios.getColumnModel().getColumn(0).setHeaderValue("Usuários");
		//tabelaUsuarios.getColumnModel().getColumn(1).setHeaderValue("Nome");
		
		// inserindo dados na tabela apartir da arvore
		DefaultTableModel tableModel = (DefaultTableModel) tabelaUsuarios.getModel();
		ArrayList<Usuario> usuarios = arvore.getUsuarios();
		for(Usuario atual: usuarios){
			tableModel.insertRow(tableModel.getRowCount(), new Object[]{ atual.getUser()});
		}		
		lQtdUsuarios.setText("Usuários registrador: " + tableModel.getRowCount());
		
		//Tabela dias1
		JTable tabelaDias = new JTable(0, 1) {
		    public boolean isCellEditable(int rowIndex, int vColIndex) {
		             return false;
		    }
		};
		JScrollPane scrollDias = new JScrollPane(tabelaDias);
		scrollDias.setBounds(240, 50, 210, 400);
		tabelaDias.getColumnModel().getColumn(0).setHeaderValue("Datas");
		
		
		//Tabela computadores
		JTable tabelaComputadores = new JTable(0, 1) {
		    public boolean isCellEditable(int rowIndex, int vColIndex) {
		             return false;
		    }
		};
		JScrollPane scrollComputadores = new JScrollPane(tabelaComputadores);
		scrollComputadores.setBounds(460, 50, 210, 400);
		tabelaComputadores.getColumnModel().getColumn(0).setHeaderValue("Computadores");
		
		
		//Tramento de click's tabela usuario			
		tabelaUsuarios.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent e) {
		           if (e.getClickCount() == 2) {
		               JTable target = (JTable)e.getSource();
		               int linha = target.getSelectedRow(); // linha selecionada
		               int coluna = target.getSelectedColumn(); // coluna selecionada
		               
		               dias = usuarios.get(linha).getDias();
		               // inserindo dados em uma tabela com 2 colunas
		               DefaultTableModel tableModel1 = (DefaultTableModel) tabelaDias.getModel();
				       tableModel1.getDataVector().removeAllElements();
				       DefaultTableModel tableModel2 = (DefaultTableModel) tabelaComputadores.getModel();
				       tableModel2.getDataVector().removeAllElements();
				       
				       for(Dia atual: dias){
							tableModel1.insertRow(tableModel1.getRowCount(), new Object[]{ atual.getData()});
				       }		
				       lQtdDatas.setText("Datas registradas: " + tableModel1.getRowCount()); 
		           }
		       } 
		});
		
		
		//Tramento de click's tabela dias		
		tabelaDias.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent e) {
		           if (e.getClickCount() == 2) {
		               JTable target = (JTable)e.getSource();
		               int linha = target.getSelectedRow(); // linha selecionada
		               int coluna = target.getSelectedColumn(); // coluna selecionada
		 
		               computadores = dias.get(linha).getComputadores();
		               // inserindo dados em uma tabela com 2 colunas
		               DefaultTableModel tableModel = (DefaultTableModel) tabelaComputadores.getModel();
				       tableModel.getDataVector().removeAllElements();
				       for(Computador atual: computadores){
							tableModel.insertRow(tableModel.getRowCount(), new Object[]{ atual.getNomeComputador()});
				       }
				       
		           }
		       } 
		});
		
		
		//Tramento de click's tabela computadores
		tabelaComputadores.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent e) {
		           if (e.getClickCount() == 2) {
		               JTable target = (JTable)e.getSource();
		               int linha = target.getSelectedRow(); // linha selecionada
		               int coluna = target.getSelectedColumn(); // coluna selecionada
		 
		               Computador computador = computadores.get(linha);
		               // inserindo dados em uma tabela com 1 coluna
			       		JOptionPane.showMessageDialog(null, "Computador: " + computador.getNomeComputador() + "\n Logon: " + computador.getLogons() + "\n Logoff: " + computador.getLogoffs() + "\n Connects: " + computador.getConnects() + "\n Desconnects: " + computador.getDesconnects() + "\n HTTP: " + computador.getHttps().size() + "\n" + computador.getHttps());
		           
		           }
		       } 
		});
		
		
		//jpanel.add(scrollUsuarios);
		//jpanel.add(scrollDias);
		//jpanel.add(scrollComputadores);
		ct.add(lUsuarios);
		ct.add(tUsuarios);
		ct.add(bUsuarios);
		ct.add(lDatas);
		ct.add(tDatas);
		ct.add(bDatas);
		ct.add(lQtdUsuarios);
		ct.add(lQtdDatas);
		ct.add(scrollUsuarios);
		ct.add(scrollDias);
		ct.add(scrollComputadores);
		
		
		// especificações do formulário
		setSize(700,530);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

}
