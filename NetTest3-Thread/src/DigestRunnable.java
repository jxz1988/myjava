import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;


public class DigestRunnable implements Runnable {
	private String filename;
	private MyMain callback;
	
	public DigestRunnable(String filename,MyMain callback){
		this.filename=filename;
		this.callback=callback;
	}
	
	@Override
	public void run() {
		System.out.println("start");
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while(din.read()!=-1);
			din.close();
			byte[] digest = sha.digest();
			//回调通知
			callback.receiveDigest(digest);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
