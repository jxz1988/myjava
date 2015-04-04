import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class GeneCharacters {
	public static String PATH="/Users/jinenzai/Documents/data.txt";
	public static void main(String[] args) throws IOException{
		try (OutputStream out = new FileOutputStream(PATH)){
			FileWriter writer = new FileWriter(PATH);
			writer.write("hello");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		File file = new File(PATH);
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(file));
			BufferedReader reader = new BufferedReader(is);
//			byte[] input = new byte[10];
//			for(int i = 0;i<input.length;i++){
//				int b = is.read();
//				if(b==-1)break;
//				input[i]=(byte)b;
//			}
			//网络流常用技术 40页
			InputStream in = new FileInputStream(file);
			int bytesRead=0;
			int bytesToRead=1024;
			byte[] input = new byte[bytesToRead];
			while(bytesRead<bytesToRead){
				int result = in.read(input,bytesRead,bytesToRead-bytesRead);
				if(result==-1)break;
				bytesRead+=result;
			}
			//不阻塞情况多少字节可用 41Page
			int bytesAvailable = in.available();
			byte[] input2 = new byte[bytesAvailable];
			int bytesRead2 = in.read(input2,0,bytesAvailable);
			///
			String lineTxt = null;
            try {
				while((lineTxt = reader.readLine()) != null){
				    System.out.println(lineTxt);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
