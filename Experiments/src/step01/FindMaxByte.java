package step01;
public class FindMaxByte {
	public static  int[] unpos = new int[2362];
	public static int[] count(byte[][] a){
		int unvar = 0;
		byte[] maxByte = new byte[19772];
		int[][] count = new int[19772][2];
		int pos = 0;
		for(int k = 0; k < 19772;k++){
			for(int j = 0;j < 90; j++){
				switch(a[j][k]){
				case 0 : 
					count[k][0]++;
					break;
				case 1 : 
					count[k][1]++;
					break;
				default:
				}
			int max = count[k][0];
			byte[] ch = {0,1};
			byte maxbyte = ch[0];
			if(count[k][1] > max){
				max = count[k][1];
			}
			if(max==90){
				unvar++;
				unpos[pos++] = k;
			}
			}
		}
		return unpos;
	}
}
