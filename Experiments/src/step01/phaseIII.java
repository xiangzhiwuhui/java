package step01;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class phaseIII {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time01 = System.currentTimeMillis();
		String[] b = new String[116566];
		char[][] SNP0123 = new char[116566][4];
		//char[] label = new char[90];
		try{
			File f = new File("F:\\HapMap数据\\染色体01\\genotypes_chr1_ASW_phase3.2_consensus.b36_fwd.txt");
			FileReader file = new FileReader(f);
			BufferedReader br = new BufferedReader(file);
			int i = 0;
			int line = 0;
			//String str = "QC\\+"; 
			//System.out.println(str);
			while(br.ready()){
				if(line == 0){
					String[] s = br.readLine().split("QCcode ");
					b[i] = s[1];
				}
				else{
					String[] s = br.readLine().split("QC\\+ ");
					b[i] = s[1];
					SNP0123[i][0] = b[i].charAt(0);
					SNP0123[i][1] = b[i].charAt(1);
					SNP0123[i][2] = b[i].charAt(3);
					SNP0123[i][3] = b[i].charAt(4);
				}
				i++;
				line++;
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		System.out.println(SNP0123[0].length);
		int k = intersectB.intersect(SNP0123);
		System.out.println(k);
		//char[] maxChar = FindMaxChar.count(b);
		//byte[][] SNP01 = dataTransfer.transfer(b, maxChar);
		//int[] unpos = FindMaxByte.count(SNP01);
		//byte[][] newSNP = DeleteSNP.deleteSNP(SNP01, unpos);
		//System.out.println(InteractSNP.interact(newSNP[0], newSNP[10]));
		//byte[] tag = new byte[2];
		//tag = countTag.countLabel(label);
		//System.out.println(tag[0]+" "+tag[1]);
		long time02 = System.currentTimeMillis();
		long runtime = time02 - time01;
		System.out.println(runtime);
	}
}
