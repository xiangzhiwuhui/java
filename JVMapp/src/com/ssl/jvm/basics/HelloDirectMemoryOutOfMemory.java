package com.ssl.jvm.basics;

import java.nio.ByteBuffer;

public class HelloDirectMemoryOutOfMemory {
	private static final int ONE_GB = 1024*1024*1024;
	
	private static int count = 1;

	public static void main(String[] args) {
		
		try {
			
			while(true){
				
				ByteBuffer buffer = ByteBuffer.allocate(ONE_GB);
				count++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Exception: instance created " + count);
			e.printStackTrace();
		}catch (Error e) {
			System.out.println("Error: instance created " + count);
		}
		
	}

}
