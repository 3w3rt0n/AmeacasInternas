package br.imd.visao;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;

import br.imd.modelo.Arvore;
import br.imd.modelo.Computador;
import br.imd.modelo.Dia;
import br.imd.modelo.Usuario;

public class TelaGraficoUsuario extends JInternalFrame {
	
	public TelaGraficoUsuario(Arvore arvore, int idUsuario) {
		super("Gráfico de eventos por usuário", false, true);		
		
		/***********************************************************************
		 * 
		 * Grafico
		 * 
		 * 
		 ***********************************************************************/
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		Usuario usuario = arvore.getUsuarios().get(idUsuario);
		
		graph.setAllowDanglingEdges(false);
		graph.setCellsEditable(false);
		graph.setCellsMovable(false);
		
		//Linhas ortogonais
		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.TopToBottom);
		style.put(mxConstants.STYLE_ROUNDED, true);
		
		
		graph.getModel().beginUpdate();
		try	{			
			Object v1 = graph.insertVertex(parent, null, usuario.getUser(), (usuario.getDias().size()*40)+160, 20, 100, 20);
			int x = 0;
			for(Dia atual: usuario.getDias()){
				Object v2 = graph.insertVertex(parent, null, atual.getData(), (x*80)+160, 100, 70, 20);
				graph.insertEdge(parent, null, "", v1, v2);
				x++;
			}
		}finally{
			graph.getModel().endUpdate();
		}
		
		/**************************************************************************
		 * 
		 * Criação do nó pc apartir do click
		 * 
		 **************************************************************************/
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter(){
		
			public void mouseReleased(MouseEvent e)	{
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());
				
				if(e.getButton() == MouseEvent.BUTTON1){
					if (cell != null && e.getY()<180 && e.getY()>100){
						System.out.println("Expandir cell="+graph.getLabel(cell));
						Dia dia = usuario.buscaDiaBinaria(graph.getLabel(cell));
						
						int qtdx = dia.getComputadores().size();
						int x = 0;
						
						for(Computador atual : dia.getComputadores()){							
							
							//ajusta deslocamento para os computadores
							if(x<(qtdx/2)){	
								x = x*(-1); 					
							}
							//criar vertex do computador
							Object vComputador = graph.insertVertex(parent, null, atual.getNomeComputador(), e.getX()+(x*80)-35 , 180, 70, 20);
							
							graph.insertEdge(parent, null, "", cell, vComputador);
							
							//criar vertex dos eventos
							Object vLogon = graph.insertVertex(parent, null, "Logon: " + atual.getLogons(), e.getX()-160, 280, 70, 20);
							Object vLogoff = graph.insertVertex(parent, null, "Logoff: " + atual.getLogoffs(), e.getX()-80, 280, 70, 20);
							Object vConnect = graph.insertVertex(parent, null, "Connect: " + atual.getConnects(), e.getX(), 280, 70, 20);
							Object vDesconnect = graph.insertVertex(parent, null, "Desconnect: " + atual.getDesconnects(), e.getX()+80, 280, 75, 20);
							Object vSite = graph.insertVertex(parent, null, "Sites: " + atual.getHttps().size(), e.getX()+160, 280, 70, 20);
																				
							graph.insertEdge(parent, null, "", vComputador, vLogon);
							graph.insertEdge(parent, null, "", vComputador, vLogoff);
							graph.insertEdge(parent, null, "", vComputador, vConnect);
							graph.insertEdge(parent, null, "", vComputador, vDesconnect);
							graph.insertEdge(parent, null, "", vComputador, vSite);
							
							//Se existi eventos sites cria os vertex também
							if(atual.getHttps().size()>0){
								int qtdURL = atual.getHttps().size();
								int xURL = ((qtdURL/2)*115)*(-1);							
								
								for(String url : atual.getHttps()){										
									
									//criar vertex do computador
									Object vURL = graph.insertVertex(parent, null, url, e.getX()+xURL , 350, 110, 20);									
									graph.insertEdge(parent, null, "", vSite, vURL);	
									
									xURL+= 115;
								}								
							}
							
							x++;
						}
					}
				}else if(e.getButton() == MouseEvent.BUTTON3){
					if (cell != null && e.getY()>=180){
						System.out.println("Remover cell=" + graph.getLabel(cell));
						graph.setSelectionCell(cell);
				        graph.removeCells();
					}
				}
			}
		});
		
		
		
		/***************************************************************************
		 * 
		 * Finalização da tela
		 * 
		 * 
		 ***************************************************************************/
	

		this.setSize(700, 530);
		//this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);	
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}



}
