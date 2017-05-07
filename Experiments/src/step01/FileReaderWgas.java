package step01;
import java.util.*;
import java.io.*;
public class FileReaderWgas {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time01 = System.currentTimeMillis();
		char[][] b = new char[90][39544];
		char[] label = new char[90];
		try{
			File f = new File("F:\\example\\wgas1.ped");
			FileReader file = new FileReader(f);
			BufferedReader br = new BufferedReader(file);
			int i = 0;
			while(br.ready()){
				String[] s = br.readLine().substring(22,79111).split(" ");
				for (int j = 0; j < s.length - 1; j++) {
					b[i][j] = s[j+1].charAt(0);
				}
				label[i] = s[0].charAt(0);
				i++;
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		char[] maxChar = FindMaxChar.count(b);
		byte[][] SNP01 = dataTransfer.transfer(b, maxChar);
		int[] unpos = FindMaxByte.count(SNP01);
		byte[][] newSNP = DeleteSNP.deleteSNP(SNP01, unpos);
		byte[] LinkSNP01 = Arrays.copyOfRange(newSNP[0], 5000, 10000);
		byte[] LinkSNP02 = Arrays.copyOfRange(newSNP[10], 5000, 10000);
		byte[] LinkSNP03 = Arrays.copyOfRange(newSNP[20], 0, 5000);
		byte[] LinkSNP04 = Arrays.copyOfRange(newSNP[70], 0, 2000);
		System.out.println(newSNP[0].length);
		//System.out.println(interactSNP2.interact(newSNP[0],newSNP[1]).size());
		//System.out.println(interactSNP4.interact(LinkSNP01,LinkSNP02,LinkSNP01,LinkSNP03));
		List<Integer> pos01 = interactSNP2.interact(LinkSNP01, LinkSNP02);
		System.out.println(pos01.size());
		List<Integer> pos02 = interactSNP2.interact(LinkSNP01, LinkSNP03);
		//System.out.println(interPos.commomPos(pos01, pos02));
		//byte[] tag = new byte[2];
		//tag = countTag.countLabel(label);
		//System.out.println(tag[0]+" "+tag[1]);
		long time02 = System.currentTimeMillis();
		long runtime = time02 - time01;
		System.out.println(runtime);
		for(int k = 0;k < 20;k++){
			//System.out.print(SNP01[0][k]+" ");
		}	     
	}
}

