package javamianshibaodian.algorithms.ohters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//找出数组中重复元素最多的数
public class FindMostFrequentInArray {

	public static void main(String[] args) {
		int[] a ={5,6,6,5,5,5,1,6};
		System.out.println("数组中重复元素最多的数是：" + findMostFrequentInArray(a));
		
	}
	
	public static int findMostFrequentInArray(int[] a){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < a.length; i++){
			if (!map.containsKey(a[i])) {
				map.put(a[i], 1);
			}else {
				map.put(a[i], map.get(a[i]) + 1);
			}
		}
		
		int most = 0;//存储重复出现次数最多的元素出现的个数
		int elem = Integer.MAX_VALUE;//存储重复出现次数最多的元素
		Iterator<Map.Entry<Integer, Integer>>  iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry)iter.next();
			int key = (Integer)entry.getKey();
			int value = (Integer)entry.getValue();
			if(value > most){
				most = value;
				elem = key;
			}
		}
		
		return elem;
	}

}
