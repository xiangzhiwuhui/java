package javamianshibaodian.algorithms.ohters;

//求数组中的最大最小值
public class MaxMin {
	static int max;
	static int min;
	public static void main(String[] args) {
//		int[] a ={4,6,2,7,3,5,1,6};
		int[] a ={2,4,4};
//		getMaxMin1(a);
		getMaxMin2(a);
		System.out.println("Max:"+ max);
		System.out.println("Min:"+ min);
	}
	
	public static void getMaxMin1(int[] a){
		if(a == null || a.length < 1)return;
		
		max = a[0];
		min = a[0];
		for(int i = 1; i < a.length; i++){
			if(a[i]>max)
				max = a[i];
			if(a[i] < min)
				min = a[i];
		}
	}
	
	public static void getMaxMin2(int[] a){
		if(a == null || a.length < 1)return;
		max = a[0];
		min = a[0];
		for(int i = 1; i < a.length-1; i=i+2){
			if(i+1 > a.length){
				if(a[i]>max)
					max = a[i];
				if(a[i]<min){
					min = a[i];
				}
			}
			
			if(a[i]>a[i+1]){
				if(a[i]>max){
					max = a[i];
				}
				if(a[i+1]<min){
					min = a[i+1];
				}
			}
			
			if(a[i]<a[i+1]){
				if(a[i+1]>max){
					max = a[i+1];
				}
				if(a[i]<min){
					min = a[i];
				}
			}
			
			if(a[i]==a[i+1]){
				if(a[i]>max){
					max = a[i+1];
				}
				if(a[i]<min){
					min = a[i];
				}
			}
			
			
		}
	}

}
