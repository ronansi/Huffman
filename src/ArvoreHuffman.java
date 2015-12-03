
public class ArvoreHuffman implements Comparable<ArvoreHuffman> {
	public int qtd;
	public char valor;
	public ArvoreHuffman direita;
	public ArvoreHuffman esquerda;
	
	public ArvoreHuffman(int qtd) {
		this.qtd = qtd;
	}
	
	public ArvoreHuffman(ArvoreHuffman direita, ArvoreHuffman esquerda) {
		this.qtd = direita.qtd + esquerda.qtd;
		this.direita = direita;
		this.esquerda = esquerda;
	}
	
	public ArvoreHuffman(int qtd, char valor) {
		this.qtd = qtd;
		this.valor = valor;
	}
	
	@Override
	public int compareTo(ArvoreHuffman o) {
		return qtd - o.qtd;
	}

}
