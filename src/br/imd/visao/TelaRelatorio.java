package br.imd.visao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.imd.modelo.*;

public class TelaRelatorio extends JInternalFrame{
	
	
	
	JTextArea rl = new JTextArea();
	JScrollPane scroll = new JScrollPane(rl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public TelaRelatorio(String str,  int tipo)  {
		super(str,false,true);
		
		Container ct = this.getContentPane();
		ct.setLayout(new FlowLayout());
		/*
		if(tipo == 1 || tipo == 4){
			// Exibir os clientes cadastrados
			rl.append("\t *** Clientes Cadastrados ***\n");		
			for(Cliente c : bd.exibirTodosClientes()){
				rl.append("\n - Id: " + c.getIdCliente());
				rl.append("\n - Nome: " + c.getNome());
				rl.append("\n - Data: " + c.getData_nasc());
				rl.append("\n - CPF: " + c.getCpf() + "\n=====\n");
			}
		}
		
		if(tipo == 2 || tipo == 4){
			// Exibir os prdoutos cadastrados
			rl.append("\t *** Produtos Cadastrados ***\n");		
			for(Produto p : bd.exibirTodosProdutos()){
				rl.append("\n - Código: " + p.getCodigoProduto());
				rl.append("\n - Descrição: " + p.getDescricaoProduto());
				rl.append("\n - Unidade: " + p.getUnidadeProduto() + "\n=====\n");
			}
		}
		
		if(tipo == 3 || tipo == 4){
			// Exibir os fornecedores cadastrados
			rl.append("\t *** Fornecedores Cadastrados ***\n");		
			for(Fornecedor f : bd.exibirTodosFornecedores()){
				rl.append("\n - Código: " + f.getCodigoFornecedor());
				rl.append("\n - Nome: " + f.getNomeFornecedor());
				rl.append("\n - CNPJ: " + f.getCnpjFornecedor() + "\n=====\n");
			}
		}
		*/
		
		rl.setBounds(0, 0, 370, 400);
		rl.setEditable(false);
		rl.setLineWrap(true);
		rl.setFont(new Font("Verdana", 0, 11)); 
		
		// Tamanho da barra
        scroll.setPreferredSize(new Dimension(380, 360));
		
		// adicionando componentes
		ct.add(scroll);		
		
		// especificações do formulário
		setSize(400,400);
		setTitle(str);
	}

}
