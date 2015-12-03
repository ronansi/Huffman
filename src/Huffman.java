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

	public static Node montarArvore(String str){
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
		
		System.out.println("Vetor de frequencias: ");
		System.out.println(nodeList);
		
		while(nodeList.size() > 1){
			Node esquerda = nodeList.get(0);
			Node direita = nodeList.get(1);
			nodeList.remove(esquerda);
			nodeList.remove(direita);
			
			Node novaRaiz = new Node(direita, esquerda);
			
			Node.buildHuffmanCode(novaRaiz);
			System.out.println("\nSubárvore:");
			imprimirArvore(novaRaiz);
			
			nodeList.add(novaRaiz);
			Collections.sort(nodeList);
		}
		
		return nodeList.get(0);
	}
	
	public static void imprimirTabela(Node node){
		if(node.direita != null || node.esquerda != null){
			imprimirTabela(node.direita);
			imprimirTabela(node.esquerda);
		}else{
			System.out.println(node.valor + "\t" + node.qtd + "\t" + node.huffmanCode);
		}
	}
	
	public static void imprimirArvore(Node raiz){
		imprimirArvore(raiz, 0);
	}
	
	private static void imprimirArvore(Node node, int nivel){
		
		if(node.direita != null)
			imprimirArvore(node.direita, nivel + 1);
		
		for(int i = 0; i < nivel; i++){
			System.out.print("\t");
		}
		System.out.print(node.qtd);
		if(node.huffmanCode != null){
			System.out.print(" " + node.valor + " " + node.huffmanCode);
		}
		System.out.println();
		
		if(node.esquerda != null)
			imprimirArvore(node.esquerda, nivel + 1);
	}
}
