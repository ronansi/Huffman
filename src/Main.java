import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {
	private JFrame janela;
	private JPanel painelPrincipal;
	private File arquivo;
	private JTextField caminhoArquivo;
	private JTextArea console;
	
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		System.out.println();
		new Main().montaTela();
	}
	
	private void escolherArquivo(){
		JFileChooser chooser = new JFileChooser();
		int retorno = chooser.showOpenDialog(null);
		
		if(retorno == JFileChooser.APPROVE_OPTION){
			arquivo = chooser.getSelectedFile();
			caminhoArquivo.setText(arquivo.getAbsolutePath());
		}
	}
	
	private void montaTela(){
		janela = new JFrame("Huffman");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
		
		JButton escolherArquivo = new JButton("Escolher arquivo...");
		escolherArquivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				escolherArquivo();
			}
		});
		
		painelPrincipal.add(escolherArquivo);
		caminhoArquivo = new JTextField(30);
		painelPrincipal.add(caminhoArquivo);
		
		console = new JTextArea();
		console.setRows(40);
		console.setColumns(70);
		console.setWrapStyleWord(true);
		console.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JButton botaoExecutar = new JButton("Executar");
		botaoExecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					StringBuilder out = new StringBuilder();
					Huffman.executar(caminhoArquivo.getText(), out);
					console.setText(out.toString());
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				console.setText("");
				caminhoArquivo.setText("");
			}
		});
		
		painelPrincipal.add(botaoExecutar);
		painelPrincipal.add(botaoLimpar);
		painelPrincipal.add(scroll);
		
		janela.pack();
		janela.setSize(810, 730);
		janela.setVisible(true);
		janela.setResizable(false);
	}
}
