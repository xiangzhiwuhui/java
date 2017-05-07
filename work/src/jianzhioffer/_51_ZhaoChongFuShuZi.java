package jianzhioffer;

/**51. 数组中重复的数字*/
public class _51_ZhaoChongFuShuZi {

	public static void main(String[] args) {
		int[] a = {2, 3, 1, 0, 2, 5, 3};
		
		int result = resolution(a);
		System.out.println(result);

	}

	static int resolution(int[] a) {
		if (a == null || a.length <= 0) {
			return -1;
		}
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] < 0 || a[i] > a.length-1) {
				return -1;
			}
		}
		
		for (int i = 0; i < a.length; i++) {
			while (a[i] != i) {
				if (a[i] == a[a[i]]) {
					return a[i];
				}
				int temp = a[i];
				a[i] = a[temp];
				a[temp] = temp;
			}
		}
		return -1;
	}
	
	

}
