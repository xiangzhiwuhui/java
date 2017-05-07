package ali;

//用数组中的数将该数组三等分，使得三个部分的和相等，如果有这样的切分返回true，否则返回false
public class AliTestBak {
	
	public static void main(String[] args){
//        int[] A = {2, 5, 2, 1, 1, 1, 4, 5, 7};
		int[] A = {1, 2, 4, 5, 3, 3};
        
        System.out.println(isQuarter(A));
    }
	
	static int sumOfSlice(int[] A, int start ,int end){
		int sum = 0;
		for(int i = start; i <= end; ++i)
			sum += A[i];
		return sum;
	}
	
	static boolean isQuarter(int[] A){
		int length = A.length;
	    int i = 1, j = length - 2;
	    
	    while(i < j - 2){
			int tmpSum = sumOfSlice(A, 0, i - 1);
			while(sumOfSlice(A , j + 1 , length - 1 ) != tmpSum  && j - i > 2)
				--j;
			if(sumOfSlice(A, i + 1, j -1 ) == tmpSum)
				return true;
			else{
				++i;
				j = length - 2;
			}
		}
		return false ;
	}

	

}
