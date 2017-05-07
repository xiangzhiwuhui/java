package jianzhioffer;

import java.util.HashMap;

public class _35_DiYiGeZiFuChuXianCiShu {

	public static void main(String[] args) {
		String str = "abaccdeff";
		char res = ans(str);
		System.out.println(res);
	}

	static char ans(String str) {
		char[] cs = str.toCharArray();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (int i = 0; i < cs.length; i++) {
			if (map.containsKey(cs[i])) {
				map.put(cs[i], map.get(cs[i])+1);
			}else {
				map.put(cs[i], 1);
			}
		}
		
//		Iterator<Entry<Character, Integer>> entries = map.entrySet().iterator();
//		while (entries.hasNext()) {
//			Entry<Character, Integer> entry = (Entry<Character, Integer>) entries.next();
//			System.out.println(entry.getKey() + ":" + entry.getValue());
//		}
		
		for (int i = 0; i < cs.length; i++) {
			if (map.get(cs[i]) == 1) {
				return cs[i];
			}
		}
		
		
		return '#';
	}
}
