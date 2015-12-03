
public class Node implements Comparable<Node> {
	public int qtd;
	public char valor;
	public Node direita;
	public Node esquerda;
	public String huffmanCode;
	
	public Node(int qtd) {
		this.qtd = qtd;
	}
	
	public Node(Node direita, Node esquerda) {
		this.qtd = direita.qtd + esquerda.qtd;
		this.direita = direita;
		this.esquerda = esquerda;
	}
	
	public Node(int qtd, char valor) {
		this.qtd = qtd;
		this.valor = valor;
	}
	
	@Override
	public int compareTo(Node o) {
		return qtd - o.qtd;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Node){
			return ((Node)obj).valor == this.valor;
		}
		return false;
	}
	
	public static void buildHuffmanCode(Node raiz){
		raiz.buildHuffmanCode("");
	}
	
	private void buildHuffmanCode(String code){
		if(direita == null && esquerda == null){
			huffmanCode = code;
		}
		
		if(esquerda != null){
			esquerda.buildHuffmanCode(code + "0");
		}
		
		if(direita != null){
			direita.buildHuffmanCode(code + "1");
		}
	}
	
	@Override
	public String toString() {
		return "(" + this.valor + "-" + this.qtd + ")";
	}

}
