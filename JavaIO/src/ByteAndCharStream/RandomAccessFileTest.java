package ByteAndCharStream;
import java.io.*;
public class RandomAccessFileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(
			RandomAccessFile raf = new RandomAccessFile("F:/wgas1.ped","r"))
		{
			//获取"F:/wags1.ped"对象文件指针的位置，初始位置是0
			System.out.println("F:/wgss1.ped的文件指针初始位置："+raf.getFilePointer());
			//移动raf的文件记录指针的位置
			raf.seek(24);
			byte[] bbuf = new byte[1024];
			//用于保存实际读取的字节数
			int hasRead = 0;
			//使用循环来重复取水的过程
			hasRead = raf.read(bbuf);
			System.out.print(new String(bbuf,0,hasRead));
			
			
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

}
