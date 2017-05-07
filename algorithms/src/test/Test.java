package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Test {

	
	public static void main(String[] args){
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(0);
		System.out.println(list.get(1));
		
		
		
//		Derived derived = new Derived();
//		char[] a = "abcd".toCharArray();
//		System.out.println(Arrays.toString(a));
//		
//		StringTokenizer st = new StringTokenizer("Welcome:china:!", ":");
//		while(st.hasMoreTokens()){
//			System.out.println(st.nextToken());
//		}
		
	}

}

interface IBase{}






class Base{
	public int f(){
		return 1;
	}
}

class Derived extends Base{
	public Derived() {
		System.out.println("Derived constructor !");
	}
	void print(){
		System.out.println("print");
	}
	public int f(){
		return 1;
	}
}
