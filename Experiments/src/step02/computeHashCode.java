package step02;

public class computeHashCode {
	int hashcode = 0; 
	int power = 1;
	public computeHashCode(byte[] snp){
		 for(int i = 0;i < snp.length;i++){
			 hashcode = hashcode + snp[i] * power;
			 power *= 10;
		 }
	}
}
