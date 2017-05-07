package ByteAndCharStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try(
			//创建字节输入流
			FileInputStream fis = new FileInputStream("FileInputStreamtest.java");
			//创建自检输出流
			FileOutputStream fos = new FileOutputStream("newfile.txt");	
			)
			{
				byte[] bbuf = new byte[364];
				int hasRead = 0;
				//循环从输入流中取出数据
				while((hasRead = fis.read(bbuf)) > 0){
					//没读一次，即可写入文件输出流，读多少就写多少
					fos.write(bbuf, 0, hasRead);
				}
				
			}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

}
