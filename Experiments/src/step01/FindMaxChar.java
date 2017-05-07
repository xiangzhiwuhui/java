package step01;
public class FindMaxChar {
	public static char[] count(char[][] a){
		char[] maxChar = new char[39544];
		int[][] count = new int[39544][5];
		for(int k = 0; k < 39544;k++){
			for(int j = 0;j < 90; j++){
				switch(a[j][k]){
				case 'A' : 
					count[k][0]++;
					break;
				case 'G' : 
					count[k][1]++;
					break;
				case 'C' : 
					count[k][2]++;
					break;
				case 'T' :
					count[k][3]++;
					break;
				case '0' :
					count[k][4]++;
					break;
				default:
				}
			int max = count[k][0];
			char[] ch = {'A','G','C','T','0'};
			char maxchar = ch[0];
			for(int i = 1; i < 5; i++){
				if(count[k][i] > max){
					max = count[k][i];
					maxchar = ch[i];
				}
			}
			maxChar[k] = maxchar;
			}
		}
		return maxChar;
	}
}

