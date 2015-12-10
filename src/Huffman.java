import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Huffman {
	
	private static Map<String, String> mapHuffmanCodes;
	
	public static Map<String, String> getMapHuffmanCodes(Node raiz){
		mapHuffmanCodes = new HashMap<String, String>();
		addMap(raiz);
		return mapHuffmanCodes;
	}
	
	private static void addMap(Node node){
		if(node.huffmanCode != null){
			mapHuffmanCodes.put("" + node.valor, node.huffmanCode);
		}else{
			addMap(node.direita);
			addMap(node.esquerda);
		}
	}

	public static Node montarArvore(String str, StringBuilder out){
		List<Node> nodeList = new ArrayList<Node>();
		for(char c: str.toCharArray()){
			boolean contem = false;
			int i;
			for(i = 0; i < nodeList.size(); i++){
				if(nodeList.get(i).valor == c){
					contem = true;
					break;
				}
			}
			
			if(contem){
				nodeList.get(i).qtd++;
			}else{
				nodeList.add(new Node(1, c));
			}
		}
		
		Collections.sort(nodeList);
		
		out.append("Vetor de frequencias:\n");
		out.append(nodeList);
		out.append("\n\n");
		
		while(nodeList.size() > 1){
			Node esquerda = nodeList.get(0);
			Node direita = nodeList.get(1);
			nodeList.remove(esquerda);
			nodeList.remove(direita);
			
			Node novaRaiz = new Node(direita, esquerda);
			
			Node.buildHuffmanCode(novaRaiz);
			out.append("Subárvore: \n");
			imprimirArvore(novaRaiz, out);
			out.append("---------------------------------------------------------------------------------------------\n");
			
			nodeList.add(novaRaiz);
			Collections.sort(nodeList);
		}
		
		return nodeList.get(0);
	}
	
	public static void imprimirTabela(Node node, StringBuilder out){
		if(node.direita != null || node.esquerda != null){
			imprimirTabela(node.direita, out);
			imprimirTabela(node.esquerda, out);
		}else{
			out.append("\n" + node.valor + "\t" + node.qtd + "\t" + node.huffmanCode);
		}
	}
	
	public static void imprimirArvore(Node raiz, StringBuilder out){
		imprimirArvore(raiz, 0, out);
	}
	
	private static void imprimirArvore(Node node, int nivel, StringBuilder out){
		
		if(node.direita != null)
			imprimirArvore(node.direita, nivel + 1, out);
		
		for(int i = 0; i < nivel; i++){
			out.append("\t");
		}
		out.append(node.qtd);
		if(node.huffmanCode != null){
			out.append(" " + node.valor + " " + node.huffmanCode);
		}
		out.append("\n");
		
		if(node.esquerda != null)
			imprimirArvore(node.esquerda, nivel + 1, out);
	}
	
	public static void executar(String caminhoArquivo, StringBuilder out) throws UnsupportedEncodingException{
		String teste = Util.lerArquivo(caminhoArquivo);
		
		Node raiz = Huffman.montarArvore(teste, out);
		
		out.append("Árvore Huffman\n");
		Node.buildHuffmanCode(raiz);
		Huffman.imprimirArvore(raiz, out);
		out.append("\n---------------------------------------------------------------------------------------------\n");
		out.append("Char\tFreq\tCode");
		Huffman.imprimirTabela(raiz, out);
		
		Map<String, String> mapa = Huffman.getMapHuffmanCodes(raiz);
		
		String original = "";
		StringBuilder saida = new StringBuilder();

		for(byte b: teste.getBytes("UTF-8")){
			original += Integer.toBinaryString(b);
		}
		
		for(char c: teste.toCharArray()){
			saida.append(mapa.get("" + c));
		}
		
		out.append("\n\nOriginal: " + original.length() + " bits");
		out.append("\nCompactada: " + saida.length() + " bits");
		
		Util.gravaArquivo(saida.toString(), "saida.txt");
	}
}
