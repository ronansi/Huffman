

public class Main {
	public static void main(String[] args){
		String teste = "this is an example for huffman encoding";
		
		int[] qtd = new int[256];
		
		for(char c : teste.toCharArray()){
			qtd[c]++;
		}
		
		ArvoreHuffman arvore = Huffman.montarArvore(qtd);
		System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
		Huffman.imprimir(arvore);
		
	}
}
