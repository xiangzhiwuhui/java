package ByteAndCharStream;

import java.util.*;

public class FindMaxChar {
	public static int unvar = 0;
	public static LinkedList<Character> count(ArrayList<LinkedList> a){
		LinkedList<Character> maxChar = new LinkedList<Character>();
		ArrayList<LinkedList> count = new ArrayList<LinkedList>();
		for(int k = 0; k < a.get(0).size();k++){
			int A = 0,G = 0,C = 0,T = 0,B = 0;
			for(int j = 0;j < a.size(); j++){
				switch(a.get(k).get(j).toString().charAt(0)){
				case 'A' : 
					A++;
					count.get(k).set(0, A);
					break;
				case 'G' : 
					G++;
					count.get(k).set(1, G);
					break;
				case 'C' : 
					C++;
					count.get(k).set(2, C);
					break;
				case 'T' :
					T++;
					count.get(k).set(3, T);
					break;
				case '0' :
					B++;
					count.get(k).set(4, B);
					break;
				default:
				}
			int max = (int) count.get(k).get(0);
			char[] ch = {'A','G','C','T','0'};
			char maxchar = ch[0];
			for(int i = 1; i < 5; i++){
				if((int) count.get(k).get(i) > max){
					max = (int) count.get(k).get(i);
					maxchar = ch[i];
				}
			}
			if(max==90){
				unvar++;
			}
			maxChar.set(k, maxchar);
			}
		}
		return maxChar;
	}
}
