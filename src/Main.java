import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	private JFrame janela;
	private JPanel painelPrincipal;
	private File arquivo;
	private JTextField caminhoArquivo;
	
	
	public static void main(String[] args) throws UnsupportedEncodingException{
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
		
		JButton botaoExecutar = new JButton("Executar");
		botaoExecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Huffman.executar(caminhoArquivo.getText());
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		});
		painelPrincipal.add(botaoExecutar);
	
		janela.pack();
		janela.setSize(500, 100);
		janela.setVisible(true);
		janela.setResizable(false);
	}
}
