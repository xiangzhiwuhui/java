package step01;

public class charToBinary {
	public static byte[][] charTB(char[][] ch){
		char[] tmp = FindMaxChar.count(ch);
		byte[][] newBSNP = new byte[90][ch[0].length];
		for(int i = 0;i < 90; i++){
			for(int j = 0;j < newBSNP[0].length;j++){
				if(tmp[j] == ch[i][j]){
					newBSNP[i][j] = 0;
				}
				else 
					newBSNP[i][j] = 1;
			}
		}
		return newBSNP;
	}
}
