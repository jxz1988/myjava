package com.jez.p92;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {

	public static final int THREAD_COUNT = 4;

	public static void main(String[] args) {
		String[] filesnames = {"/Users/jinenzai/Documents/data.txt","/Users/jinenzai/Documents/data.txt"};
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
		for(String filename : filesnames){
			File f = new File(filename);
			if(f.exists()){
				if(f.isDirectory()){
					File[] files = f.listFiles();
					for(int i = 0;i<files.length;i++){
						if(!files[i].isDirectory()){
							Runnable task = new GZipRunnable(files[i]);
							pool.submit(task);
						}
					}
				}else{
					Runnable task = new GZipRunnable(f);
					pool.submit(task);
				}
			}
		}
		pool.shutdown();
		//pool.shutdownNow();
	}
}
