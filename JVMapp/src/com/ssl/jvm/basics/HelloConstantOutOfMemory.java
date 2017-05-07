package com.ssl.jvm.basics;

import java.util.ArrayList;
import java.util.List;

public class HelloConstantOutOfMemory {

	public static void main(String[] args) {
		try {
			
			List<String> stringList = new ArrayList<String>();
			
			int item = 0;
			while(true){
				
				stringList.add(String.valueOf(item++).intern());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
