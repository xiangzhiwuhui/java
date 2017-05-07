package step01;

public class intersectB {
	public static int intersect(char[][] SNP){
		int count = 0;
		for(int i = 1;i < SNP.length; i++){
			if((SNP[i][0] == SNP[i][2]) && (SNP[i][1] == SNP[i][3])){
				count++;
			}
		}
		return count;
	}
}
