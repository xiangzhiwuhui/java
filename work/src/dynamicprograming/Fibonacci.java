package dynamicprograming;
/**菲波那切数列*/
public class Fibonacci {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println("递归算法：" + fibonacciC(45));
		System.out.println("递归算法耗时：" + (System.currentTimeMillis() - time) + "ms");
		
		long time1 = System.currentTimeMillis();
		System.out.println("DP算法：" + fibonacciDP(45));
		System.out.println("DP算法耗时：" + (System.currentTimeMillis() - time1) + "ms");
		
	}
	
	
	//递归算法
	public static long fibonacciC(int n){
		
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return fibonacciC(n - 1) + fibonacciC(n - 2);
		}
	}
	
	//动态规划算法：记忆化搜索
	static long[] save = new long[100];
	public static long fibonacciDP(int n) {
		if (save[n] != 0) {
			return save[n];
		}
		if (n == 0 || n == 1) {
			save[n] = 1;
			return save[(int) n];
		} else {
			save[n] = fibonacciDP(n - 1) + fibonacciDP(n - 2);
			return save[n];
		}
	}

}
