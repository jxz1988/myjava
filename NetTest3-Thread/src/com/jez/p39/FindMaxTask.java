package com.jez.p39;

import java.util.concurrent.Callable;
//查找区间中都最大数字
public class FindMaxTask implements Callable {
	
	private int[] data;
	private int start;
	private int end;
	
	
	FindMaxTask(int[] data,int start,int end){
		this.data=data;
		this.start=start;
		this.end=end;
	}
	
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int max=Integer.MAX_VALUE;
		for(int i=start;i<end;i++){
			if(data[i]>max)max=data[i];
		}
		return max;
	}

}
