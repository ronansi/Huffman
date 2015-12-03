import java.util.PriorityQueue;


public class Huffman {

	public static ArvoreHuffman montarArvore(int[] qtd){
		PriorityQueue<ArvoreHuffman> arvores = new PriorityQueue<ArvoreHuffman>();
		
		for(int i = 0; i < qtd.length; i++){
			if(qtd[i] > 0){
				arvores.offer(new ArvoreHuffman(qtd[i], (char)i));
			}
		}
		
		while(arvores.size() > 1){
			ArvoreHuffman a = arvores.poll();
			ArvoreHuffman b = arvores.poll();
			
			arvores.offer(new ArvoreHuffman(b, a));
		}
		
		return arvores.poll();
	}
	
	public static void imprimir(ArvoreHuffman arvore){
		imprimir(arvore, new StringBuilder());
	}
	
	private static void imprimir(ArvoreHuffman arvore, StringBuilder prefixo){
		if(arvore.direita == null && arvore.esquerda == null){
			System.out.println(arvore.valor + "\t" + arvore.qtd + "\t" + prefixo);
		}else{
			prefixo.append("0");
			imprimir(arvore.esquerda, prefixo);
			prefixo.deleteCharAt(prefixo.length()-1);
			
			prefixo.append("1");
			imprimir(arvore.direita, prefixo);
			prefixo.deleteCharAt(prefixo.length()-1);
		}
	}
}
