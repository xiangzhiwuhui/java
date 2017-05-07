package ByteAndCharStream;
import java.util.*;
import java.io.*;
public class FileReaderWgas {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		long time01 = System.currentTimeMillis();
		ArrayList<LinkedList> SNP = new ArrayList<LinkedList>();
		SNP.ensureCapacity(457388);
		File f = new File("F:/wgas1.ped");
		FileReader file = new FileReader(f);
		BufferedReader br = new BufferedReader(file);
		while(br.ready()){
			LinkedList<Character> tmpLinkedList = new LinkedList<Character>();
			String[] s = br.readLine().substring(24).split(" ");
			for (int j = 0; j < s.length; j++) {
				char ch = s[j].charAt(0);
				tmpLinkedList.add(ch);
			}
			SNP.add(tmpLinkedList);
		}
		br.close();
		file.close();
		// LinkedList<Character> maxChar = new LinkedList<Character>();
		// maxChar = FindMaxChar.count(SNP);
		/*for(int k = 0; k < 457388;k++){
			for(int j = 0;j < 90; j++){
				switch(b[j][k]){
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
			}
		}
		*/
		long time02 = System.currentTimeMillis();
		long runtime = time02 - time01;
		System.out.println(runtime);
		System.out.println(SNP.size()+" ");
		// System.out.println(FindMaxChar.unvar);
		// for(int k = 0;k < 100;k++){
			// System.out.print(maxChar.get(k)+" ");
		// }
	}
}
