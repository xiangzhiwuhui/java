package regularexpression;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

	public static void main(String[] args) {
		// 使用字符串的matches()判断字符串是否匹配某个表达式，‘.’表示匹配任何一个字符
		print("abc".matches("..."));
		
		//将字符串"a2389a"中的数字用 * 替换 \d 表示0-9之间的任意一个数字
		print("a2389a".replaceAll("\\d", "*"));
		
		//将任何是a-z的字符串长度为3的字符串进行编译，这样可以加快匹配速度
		Pattern p = Pattern.compile("[a-z]{3}");
		// 进行匹配，并将匹配结果放在Matcher对象中
		Matcher m = p.matcher("abc");
		print(m.matches());
		
		// 上面的3行代码可以用下面一行替换
		print("abc".matches("[a-z]{3}"));
		
		//初步认识. * + ?
		print("a".matches(".")); // true
		print("aa".matches("aa")); // true
		print("aaaa".matches("a*")); //true
		print("".matches(".")); // false
		print("".matches("")); // true
		print("".matches("a*")); //true
		print("".matches("a?")); // true
		print("12345678975".matches("\\d{3,}")); //true
		print("192.168.0.aaa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\w{1,}"));// true
		print("192".matches("[0-2][0-9][0-9]")); // true
		
		// 范围
		print("a".matches("[abc]")); // true
		print("a".matches("[^abc]")); // false
		print("A".matches("[a-zA-Z]")); // true
		
		// 边界匹配
		// ^    行的开头
		// $    行的结尾
		// \b   单词边界
		// \B   非单词边界
		// \A   输入的开头
		// \G   上一个匹配的结尾
		// \Z   输入的结尾，仅用于最后的结束符(如果有的话)
		// \z   输入的结尾
		
		print("hello sir".matches("^h.*")); // true
		print("hello sir".matches(".*ir$")); // true
		print("hello sir".matches("^h[a-z]{1,3}o\\b.*")); //true
		print("hellosir".matches("^h[a-z]{1,3}o\\b.*")); //false
		// 空白行：一个或多个（空白并且非换行符）开头，并以换行符结尾
		print(" \n".matches("^[\\s&&[^\\n]]*\\n$")); //true
		
		print("asdsfdfagf@adsdsf.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+")); //true
		
		Pattern p1 = Pattern.compile("\\d{1,5}-\\d{1,5}-\\d{1,5}-\\d{1,5}");
		Matcher m1 = p1.matcher("123-345-567-00");
		print(m1.matches());
		m1.reset();
		
		print(m1.find());
		print(m1.start() + "--" + m1.end());
		
		// 字符串替换:将字符串中的忽略大小写的java全部替换成JAVA
		Pattern p2 = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		Matcher m2 = p2.matcher("java Java jAva ILoveJavA youHateJava adsfd");
		//存放字符串
		StringBuffer buf = new StringBuffer();
		
		while (m2.find()) {
			m2.appendReplacement(buf, "JAVA");
		}
		
		//不加这句话，字符串adsfd将会被遗漏
		m2.appendTail(buf);
		print("java Java jAva ILoveJavA youHateJava adsfd");
		print(buf); // JAVA JAVA JAVA ILoveJAVA youHateJAVA adsfd
		
		//分组
		Pattern p3 = Pattern.compile("(\\d{3,5})([a-z]{2})");
		print("模式为：" + p3.pattern()); //(\d{3,5})([a-z]{2})
		String str3 = "123aa-345bb-234cc-00"; 
		Matcher m3 = p3.matcher(str3);
		print(m3.groupCount()); // 2组
		
		while (m3.find()) {
			print(m3.group()); // 数字字母都有
			print(m3.group(1)); // 只有数字
			print(m3.group(2)); // 只有字母
		}
		
		print("\\B\\c".replaceAll("\\\\", "#")); //#B#c
		
		Pattern p4 = Pattern.compile("-");
		String str4 = "123aa-345bb-234cc-00"; 
		StringBuffer buf4 = new StringBuffer();
		Matcher m4 = p4.matcher(str4);
		m4.region(0, 12);
		while (m4.find()) {
			m4.appendReplacement(buf4, "#");
		}
		m4.appendTail(buf4);
		print(buf4.toString()); // 123aa#345bb#234cc-00
		print(Arrays.toString(p4.split(str4))); //[123aa, 345bb, 234cc, 00]
		
		//当使用quote()方法后,将”.*”转换为了它的字面量意思,也就是只能匹配”.*”
		String regex = Pattern.quote(".*"); // 将".*"转换成字面量
		Pattern p5 = Pattern.compile(regex);
		String str5 = ".*";
		print(p5.pattern()); // \Q.*\E
		print(p5.matcher(str5).matches()); // true
		
		Pattern p6 = Pattern.compile("\\d+");
		Matcher m6 = p6.matcher("aa378bbb245");
		m6.find();
		print(m6.group());
		print(m6.start()); //2 返回当前匹配到的字符串在原目标字符串中的位置
		print(m6.end()); // 5 返回当前匹配的字符串的最后一个字符在原目标字符串中的索引位置的下一位置.
		m6.find();
		print(m6.group());
		print(m6.start()); //8
		print(m6.end()); // 11
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}

}
