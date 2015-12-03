import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class Util {
	
	public static String lerArquivo(String caminho){
		String arquivo = "";
		
		try {
			FileReader file = new FileReader(caminho);
			BufferedReader buffer = new BufferedReader(file);
			while(buffer.ready()){
				arquivo += buffer.readLine();
			}
			buffer.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arquivo;
	}
	
	public static void gravaArquivo(String conteudo, String caminho){
		File file = new File(caminho);
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			out.write(conteudo);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
