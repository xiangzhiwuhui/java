package step01;

public class countTag {
	public static byte[] countLabel(char[] tag){
		byte[] label = new byte[2];
		for(int i = 0;i < 90;i++){
			if(tag[i] == '1'){
				label[0]++;
			}
			else
				label[1]++;
		}
		return label;
	}
}
