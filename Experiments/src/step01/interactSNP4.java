package step01;

public class interactSNP4 {
	public static int interact(byte[] SNP01, byte[] SNP02,byte[] SNP03, byte[] SNP04){
		int k = 0;
		for(int i = 0;i < SNP01.length; i++){
			if((SNP01[i] == SNP02[i]) && (SNP02[i] == SNP03[i]) && (SNP03[i] == SNP04[i])) {
				k++;
			}
		}
		return k;
	}
}
