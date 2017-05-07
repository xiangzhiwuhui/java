package step01;
import java.util.*;
public class interactSNP2 {
	public static List interact(byte[] SNP01, byte[] SNP02){
		List<Integer> pos = new ArrayList<>(2000);
		for(int i = 0;i < SNP01.length; i++){
			if((SNP01[i] == SNP02[i]) ) {
				pos.add(i);
			}
		}
		return pos;
	}
}
