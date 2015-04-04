package com.jez.p115;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledWebLog {

	private final static int NUM_THREADS = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();

		try (BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(args[0]), "UTF-8"));) {
			for(String entry = in.readLine();entry!=null;entry=in.readLine()){
				LookupTask task = new LookupTask(entry);
				Future<String> future = executor.submit(task);
				LogEntry result = new LogEntry(entry, future);
				results.add(result);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for(LogEntry result : results){
			try {
				System.out.println(result.future.get());
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(result.original);
			}
		}
	}

	private static class LogEntry {
		String original;
		Future<String> future;

		LogEntry(String original, Future<String> future) {
			// TODO Auto-generated constructor stub
			this.original = original;
			this.future = future;
		}
	}

}
