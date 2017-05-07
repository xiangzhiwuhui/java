package javamianshibaodian.algorithms.ohters;



//找到数组中第二大的数
public class FindSecondMax {
	static int max;
	static int secondMax;
	public static void main(String[] args) {
		int[] a ={4,6,2,7,3,5,1,6};
		findSecondMax(a);
		System.out.println("第二大的数为：" + secondMax);

	}
	
	public static void findSecondMax(int[] a){
		if(a == null || a.length < 1)return;
		
		max = a[0];
		secondMax = Integer.MIN_VALUE;
		
		for(int i = 1; i < a.length; i++){
			if(a[i] > max){
				secondMax = max;
				max = a[i];
			}else if(a[i] > secondMax){
				secondMax = a[i];
			}
		}
	}

}
