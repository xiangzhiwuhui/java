package algorithms;

public class Algorithms {

	public static void main(String[] args) {
		Algorithms algorithms = new Algorithms();
		
		System.out.println("方法1：二进制数中1的个数为：" + algorithms.count1(7));
		System.out.println("方法2：二进制数中1的个数为：" + algorithms.count2(7));
		System.out.println("方法3：二进制数中1的个数为：" + algorithms.count3(7));

	}
	
	/**
	 *=============1.求二进制中1的个数================
	 * */
	//方法1：
	int count1(int n){
		int num = 0;
		while(n != 0){
			if(n % 2 == 1){
				num++;
			}
			n = n / 2;
		}
		
		return num;
	}
	
	//方法2：
	int count2(int n){
		int num = 0;
		while(n != 0){
			num += n & 1;
			n >>= 1;
		}
		
		return num;
	}
	
	//方法3：
	int count3(int n){
		int num = 0;
		while(n != 0){
			n &= (n - 1);
			num++;
		}
		
		return num;
	}
	
	/**
	 * =============2.求二进制中1的个数================
	 * */
	//方法1：

}
