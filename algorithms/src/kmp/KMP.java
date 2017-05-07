package kmp;

/**KMP算法*/
 
public class KMP {
    public boolean chkRotation(String a, int lena, String b, int lenb) {
        if (a == null || b == null || lena != lenb) {
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1;
    }
    
    public static void main(String[] args) {
		String s = "BBCABCDABABCDABCDABDE";
//    	String s = "BBC ABCDAB ABCDABCDABDE";
		String m = "ABCDABD";
		System.out.println(getIndexOf(s, m));
	}
 
    // KMP Algorithm
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }
    
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }
} 
