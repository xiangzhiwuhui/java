package kmp;

/**自己写的KMP算法：这个算法和在自己qq空间的算法解释相对应*/

public class MyKMP {
	
    public static void main(String[] args) {
//		String s = "BBCABCDABABCDABCDABDE";
    	String s = "BBC ABCDAB ABCDABCDABDE";
		String m = "ABCDABD";
		System.out.println(s);
		System.out.println(m);
		System.out.println(getIndexOf(s, m));
	}
 
    // KMP Algorithm
    public static int getIndexOf(String s, String m) {
    	if(m==null||m.length()<=0){
			 return -1;
		 }
		 if(s==null||s.length()<=0){
			 return -1;
		 }
		 if(s.length()<m.length()){
			 return -1;
		 }
        if (s.length() < m.length()) {
            return -1;
        }
        
        char[] sc = s.toCharArray();
        char[] mc = m.toCharArray();
        int[] next = getNextArray(m); // next数组存储部分匹配值
        //移动位数 = 已匹配的字符数 - 对应的部分匹配值
        int mv = 0; //存储移动位数
        for (int i = 0; i < sc.length; i=i+mv) {
        	int count = 0; // 记录已经匹配的字符数
        	int j = 0; //sc下标
        	int t = 0; // mc下标
			for (j = i, t = 0; j < sc.length && t < mc.length;j++, t++) {
				if (mc[t] == sc[j]) {
					count++;
				}else {
					if (count == 0) { // 说明第一次都没匹配，所以匹配串整个后移1位
						mv = 1;
					}
					break;
				}
			}
			
			if (count == mc.length) {
				return i;
			}else {
				if (count > 0) {
					mv = count - next[t-1];
				}
			}
			
		}
        
        return -1;
    }
    
    public static int[] getNextArray(String m) {
    	// 1，如果模式串长度为1
        if (m.length() == 1) { 
            return new int[] {0};
        }
        
        //2,如果模式串长度大于1
        int[] next = new int[m.length()];
        next[0] = 0;
        //
        for (int i = 1; i < m.length(); i++) {
        	boolean hasEqual = false; // 指示是否有匹配的串，如果有为true
			for (int j = 1; j <= i; j++) {//计算第i个字符的模式串部分匹配值
				String prefix = m.substring(0, i); //取[0,i)之间的字符串
				String suffix = m.substring(j, i+1);//取[j,i+1)之间的字符串
				if (prefix.startsWith(suffix)) {
					hasEqual = true;
					next[i] = suffix.length();
					break;
				}
			}
			if (hasEqual == false) {
				next[i] = 0;
			}
		}
        
        return next;
    }
} 
