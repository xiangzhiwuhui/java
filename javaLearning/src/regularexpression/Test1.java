package regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

	public static void main(String[] args) {
		String rex = "1*trade*done";
		String str = "100-trade-done";
		
		String rex1 = rex.replaceAll("\\*", ".*");
		print(Pattern.matches(rex1, str));
		
		Pattern p = Pattern.compile(rex1);
		print(Pattern.quote(rex1));
		Matcher m = p.matcher(str);
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
	
}
