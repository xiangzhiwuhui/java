package jianzhioffer;

public class _53_RegularExpressionsMatching {

	public static void main(String[] args) {
		char[] str = "aaa".toCharArray();
		char[] pattern = "a.a".toCharArray();
		
		boolean result = match(str, pattern);
		System.out.println(result);
	}

	static boolean match(char[] str, char[] pattern) {
		if (str == null || pattern == null) {
			return false;
		}
		
		return matchCore(str, 0, pattern, 0);
	}

	static boolean matchCore(char[] str, int i, char[] pattern, int j) {
		if (i == str.length && j == pattern.length) {
			return true;
		}
		
		if (i < str.length && j == pattern.length) {
			return false;
		}
		
		// 如果模式当前字符的下一个字符是*
		if (j+1 < pattern.length && pattern[j+1] == '*') {
			if (str[i] == pattern[j] || (pattern[j] == '.' && i < str.length)) {
				return matchCore(str, i+1, pattern, j+2)    // 移动到下一个状态
						|| matchCore(str, i+1, pattern, j)  // 保持在当前状态
						|| matchCore(str, i, pattern, j+2); // 忽略*
			}else {
				return matchCore(str, i, pattern, j+2);     // 忽略*
			}
		}
		
		// 如果模式当前字符的下一个字符不是*，并且当前字符和模式中的当前字符匹配，字符串中的字符和模式串中的字符都向后移动一个
		if (str[i] == pattern[j] || (pattern[j] == '.' && i < str.length)) {
			return matchCore(str, i+1, pattern, j+1);
		}
		
		return false;
	}
}


















