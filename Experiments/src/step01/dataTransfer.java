package step01;
public class dataTransfer {
	public static byte[][] transfer(char[][] SNP,char[] maxChar){
		byte[][] newSNP = new byte[90][19772];
		for(int i = 0;i < 90;i++){
			for(int j = 0;j < 39543;){
				if((SNP[i][j] == maxChar[j]) && (SNP[i][j+1] == maxChar[j+1])){
					newSNP[i][j/2] = (byte)0;
				}
				else {
					newSNP[i][j/2] = (byte)1;
				}
				
				j = j + 2;
			}
		}
		return newSNP;
	}
}
