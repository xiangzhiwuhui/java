package BitMap;

import java.util.*;
public class Test01 extends BitSet {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//创建一个具有10000000位的bitset　初始所有位的值为false
				java.util.BitSet[] BitSetArray = new java.util.BitSet[1024];
				for(int i = 0; i < 1024; i++){
					BitSetArray[i] = new java.util.BitSet(1000000);
				}
				java.util.BitSet bitSet = new java.util.BitSet(1000000);
				java.util.BitSet bi01 = new java.util.BitSet(10000000);
				java.util.BitSet bi02 = new java.util.BitSet(10000000);
				java.util.BitSet bi03 = new java.util.BitSet(1000000);
				java.util.BitSet bi04 = new java.util.BitSet(1000000);
				//将指定位的值设为true
				
				Random r = new Random();
				/*
				for(int j = 0; j < 1024; j++){
					for(int i = 0; i < 999999; i++){
						int k = r.nextInt(2);
						if(k==1)
							BitSetArray[j].set(i, true);
						else
							BitSetArray[j].set(i, false);
					}
				}
				*/
				for(int i = 0; i < 10; i++){
					//int k = r.nextInt(2);
					int k = 1;
					if(k==1)
						bi03.set(i + 100000, true);
					else
						bi03.set(i + 100000, false);
				}
				for(int i = 0; i < 10; i++){
					//int k = r.nextInt(2);
					int k = 1;
					if(k==1)
						bi04.set(i + 100000, true);
					else
						bi04.set(i + 100000, false);
				}
				long time01 = System.currentTimeMillis();
				for(int i = 0; i < 100; i++){
					if(i > 0 && (i%1000)==0)
						System.out.println();
					if(bitSet.get(i))
						System.out.print(1);
					else
						System.out.print(0);
				}
				long time02 = System.currentTimeMillis();
				System.out.println();
				//输出指定位的值
				long time = time02 - time01;
				System.out.println(time);
				System.out.println(bitSet.get(9999));
				System.out.println(bitSet.get(9998));
				System.out.println(bitSet.size());
				System.out.println(bi03.toString().equals(bi04.toString()));
				
				

	}

}
