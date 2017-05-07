package jianzhioffer;


public class _54_PanDuanZiFuChuanShiFouShiShuZi {

	public static void main(String[] args) {
		
//		Pattern p = Pattern.compile("[+|-]?\\d+[.[\\d+]?[e|E][+|-]?\\d+]?");
		char[] c = {};
		String str1 = String.valueOf(c);
		System.out.println(str1.matches("[+|-]?\\d+(\\.\\d*)?([e|E]([+|-])?\\d+)?"));
	}
	
	static void p(Object o) {
		System.out.println(o);
	}
}
