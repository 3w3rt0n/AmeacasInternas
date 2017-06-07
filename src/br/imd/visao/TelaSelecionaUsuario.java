package br.imd.visao;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.imd.modelo.Arvore;
import br.imd.modelo.Usuario;

public class TelaSelecionaUsuario extends JDialog{
	
	public TelaSelecionaUsuario(Arvore arvore) {
		Container ct = this.getContentPane();
		ct.setLayout(null);		

		JLabel lQtdUsuarios = new JLabel("Usuários registrador: ");
		lQtdUsuarios.setBounds(20,450,200,25);
		
		//Tabela usuarios1
		JTable tabelaUsuarios = new JTable(0, 1) {
		    public boolean isCellEditable(int rowIndex, int vColIndex) {
		             return false;
		    }
		};
		JScrollPane scrollUsuarios = new JScrollPane(tabelaUsuarios);
		scrollUsuarios.setBounds(20, 20, 120, 400);
		tabelaUsuarios.getColumnModel().getColumn(0).setHeaderValue("Usuários");
		//tabelaUsuarios.getColumnModel().getColumn(1).setHeaderValue("Nome");
		
		// inserindo dados na tabela apartir da arvore
		DefaultTableModel tableModel = (DefaultTableModel) tabelaUsuarios.getModel();
		ArrayList<Usuario> usuarios = arvore.getUsuarios();
		for(Usuario atual: usuarios){
			tableModel.insertRow(tableModel.getRowCount(), new Object[]{ atual.getUser()});
		}		
		lQtdUsuarios.setText("Usuários registrador: " + tableModel.getRowCount());
	}

}
