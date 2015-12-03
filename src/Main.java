import java.io.UnsupportedEncodingException;
import java.util.Map;



public class Main {
	public static void main(String[] args) throws UnsupportedEncodingException{
		String teste = Util.lerArquivo("texto.txt");
		
		Node raiz = Huffman.montarArvore(teste);
		
		System.out.println("\n\n¡rvore Huffman");
		Node.buildHuffmanCode(raiz);
		Huffman.imprimirArvore(raiz);
		
		System.out.println("\n\nChar\tFreq\tCode");
		Huffman.imprimirTabela(raiz);
		
		Map<String, String> mapa = Huffman.getMapHuffmanCodes(raiz);
		
		String original = "";
		StringBuilder saida = new StringBuilder();

		for(byte b: teste.getBytes("UTF-8")){
			original += Integer.toBinaryString(b);
		}
		
		for(char c: teste.toCharArray()){
			saida.append(mapa.get("" + c));
		}
		
		System.out.println("\n\nOriginal: " + original.length() + " bits - " + original);
		System.out.println("Compactada: " + saida.length() + " bits - " + saida);
		
		Util.gravaArquivo(saida.toString(), "saida.txt");
	}
}
