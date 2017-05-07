package step01;

public class DeleteSNP {
	public static byte[][] deleteSNP(byte[][] SNP01,int[] pos){
		byte[][] newSNP = new byte[90][17410];
		for(int i = 0; i < 90;i++){
			int k = 0;
			int pointer = 0;
			for(int j = 0;j < SNP01[0].length;j++){
				if(pos[pointer] != j){
					newSNP[i][k++] = SNP01[i][j];
				}
				else{
					if(pointer < pos.length - 1){
						pointer++;
					}
				}
			}
		}
		return newSNP;
	}
}
