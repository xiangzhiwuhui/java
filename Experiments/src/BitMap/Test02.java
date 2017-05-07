package BitMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
public class Test02 extends BitSet{
	
	static BufferedWriter bw;
	/*
	public Test02() {
		// TODO 自动生成的构造函数存根
		try{
			bw = new BufferedWriter(new FileWriter("f://abc.txt"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void allCombine(String[] s, int index, int len2,
			LinkedList<String> list) throws Exception{
		// TODO Auto-generated method stub
		if(len2==0){
			StringBuffer sb = new StringBuffer();
			Iterator<String> iter = list.iterator();
			while(iter.hasNext()){
				sb.append(iter.next()+" ");
			}
			String tmp = sb.toString().trim();
			bw.write(tmp);
			return;
		}
		for(int i = index;i<s.length;++i){
			list.addLast(s[i]);
			allCombine(s, i+1, len2-1,list);
			list.removeLast();
		}
	}
	*/
	public static void main(String[] args) throws Exception{
		// TODO 自动生成的方法存根
		//创建一个具有10000000位的bitset　初始所有位的值为false
		try{
			bw = new BufferedWriter(new FileWriter("f://SNP.txt"));
		}catch(Exception e){
			e.printStackTrace();
		}
		java.util.BitSet[] BitSetArray = new java.util.BitSet[2000];
		java.util.BitSet bitSet = new java.util.BitSet(100);
		for(int i = 0; i < 2000; i++){
			BitSetArray[i] = new java.util.BitSet(100);
		}		
		Random r = new Random();
		
		for(int j = 0; j < 2000; j++){
			for(int i = 0; i < 100; i++){
				int k = r.nextInt(2);
				if(k==1){
					bw.write(1);
				}
				else{
					bw.write(0);
				}
			}
			bw.write('\n');
		}
		
		/*
		long time01 = System.currentTimeMillis();
		java.util.BitSet[] BitSetArray2 = new java.util.BitSet[79800];
		
		for(int i = 0; i < 79800; i++){
			BitSetArray2[i] = new java.util.BitSet(200000);
		}		
		int index = 0;
		for(int i = 0; i < 399; i++){
			for(int j = i + 1; j < 400; j++){
				for(int k = 0; k < 200000; k++){
					if(BitSetArray[i].get(k) == BitSetArray[j].get(k))
						BitSetArray2[index].set(k, true);
					else
						BitSetArray2[index].set(k, false);
				}
				index ++;
			}
		}
		long time02 = System.currentTimeMillis();
		long time = time02 - time01;
		System.out.println(time);
		System.out.println(BitSetArray2[index-1].length());
		*/
	}

}

