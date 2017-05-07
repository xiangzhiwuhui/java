package ali;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//逆波兰是求值
public class Main {

    public static void main(String[] args) {
    	
//        ArrayList<Integer> inputs = new ArrayList<Integer>();
//        Scanner in = new Scanner(System.in);
//        String line = in.nextLine();
//        if(line != null && !line.isEmpty()) {
//            int res = resolve(line.trim());
//            System.out.println(String.valueOf(res));
//        }
    	String expr = "1 1 + 2 ^ *"; // 结果为6
    	
    	System.out.println(resolve(expr));

    	
    }

    static char[] toChar(String[] exp) {
		char[] exps = new char[exp.length];
		for (int i = 0; i < exps.length; i++) {
			exps[i] = exp[i].charAt(0);
		}
		return exps;
	}

	// write your code here
    public static int resolve(String expr) {
    	int result = 0;
    	String[] exp = expr.split("\\s+");
    	char[] ex = toChar(exp);
//    	System.out.println(Arrays.toString(ex));
    	ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
    	for (int i = 0; i < ex.length; i++) {
			if (Character.isDigit(ex[i])) {
				if (stack.size() < 16) {
					stack.push(new Integer(Integer.parseInt(ex[i]+"")));
				}else {
					return -2;
				}
			}else if(ex[i] == '^') {
				if (stack.size() >= 1) {
					Integer c = stack.pop();
					Integer c1 = ++c;
					stack.push(c1);
				}else {
					return -1;
				}
			}else if (ex[i] == '*') {
				if (stack.size() >= 2) {
					Integer c1 = stack.pop();
					Integer c2 = stack.pop();
					Integer res = c1 * c2;
					stack.push(res);
				}else {
					return -1;
				}
			}else if (ex[i] == '+') {
				if (stack.size() >= 2) {
					Integer c1 = stack.pop();
					Integer c2 = stack.pop();
					Integer res = c1 + c2;
					stack.push(res);
				}else {
					return -1;
				}
			}
		}
    	
    	return stack.pop();
    }
}
