package ali;

//用数组中的数将该数组三等分，使得三个部分元素和相等，如果有这样的切分返回true，否则返回false
public class AliTest {
	
	public static void main(String[] args){
        int[] A = {2, 5, 2, 1, 1, 1, 4, 5, 7};
        
        System.out.println(isOneThird(A));
    }
	
	static int sumOfSlice(int[] A, int start ,int end){
		int sum = 0;
		for(int i = start; i <= end; ++i)
			sum += A[i];
		return sum;
	}
	
	static boolean isOneThird(int[] A){
		int length = A.length;
		
		if(A.length < 7)return false;
		
	    int i = 1, j = length - 2;
	    
	    while(i <= j - 2){
			int leftSum = sumOfSlice(A, 0, i - 1);
			int rightSum = sumOfSlice(A , j + 1 , length - 1);
			while(rightSum != leftSum && j - i > 2){
				--j;
				rightSum = sumOfSlice(A , j + 1 , length - 1);
			}
			
			if(rightSum == leftSum && sumOfSlice(A, i + 1, j - 1) == leftSum)
				return true;
			else{
				++i;
				j = length - 2;
			}
		}
		return false;
	}

}
