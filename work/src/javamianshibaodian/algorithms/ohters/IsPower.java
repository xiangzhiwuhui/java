package javamianshibaodian.algorithms.ohters;

//判断一个数是否是2的n次方
public class IsPower {

	public static void main(String[] args) {
		int n =8;
		System.out.println(n + ":" + isPower1(n));
		System.out.println(n + ":" + isPower2(n));

	}
	public static boolean isPower1(int n){
		boolean flag = false;
		int i = 1;
		if(n < 1)return false;
		
		while(i <= n){
			if(i == n)
				return true;
			i <<= 1;
		}
		return flag;
	}
	
	public static boolean isPower2(int n){
		if(n < 1) return false;
		if((n&(n-1)) == 0)return true;
		return false;
	}

}
