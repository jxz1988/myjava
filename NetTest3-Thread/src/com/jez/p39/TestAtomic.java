package com.jez.p39;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestAtomic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		Collections.synchronizedList(list);
	}

}
