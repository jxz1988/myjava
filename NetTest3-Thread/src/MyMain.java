import javax.xml.bind.DatatypeConverter;


public class MyMain {
	public static String PATH="/Users/jinenzai/Documents/data.txt";
	private String filename;
	private byte[] digest;
	
	public MyMain(String filename){
		this.filename=filename;
	}
	
	public void calculateDigest(){
		DigestRunnable dr = new DigestRunnable(filename,this);
		Thread t = new Thread(dr);
		t.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(args.length);
		for(String filename:args){
			MyMain m = new MyMain(filename);
			m.calculateDigest();
			
		}
		MyMain m = new MyMain(PATH);
		m.calculateDigest();
	}
	
	public void receiveDigest(byte[] digest){
		this.digest=digest;
		System.out.println(this);
	}
	
	@Override
	public String toString(){
		String result = filename+":";
		if(digest!=null){
			result+=DatatypeConverter.printHexBinary(digest);
		}else{
			result+="digest not available";
		}
		return result;
	}

}
