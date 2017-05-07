package step01;
import java.util.concurrent.*;
import java.io.*;
public class DataConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] snp = new int[914800];
		byte[] bbuf = new byte[914800];
		try(FileInputStream fis = new FileInputStream("F:\\wgas1.ped"))
		{
			int i = 0;
			while(fis.read()>0 && i<914800){
				snp[i] = bbuf[i];
				i++;
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		for(int i = 0; i < 100; i++)
		{
			System.out.print(snp[i]);
		}
	}

}
