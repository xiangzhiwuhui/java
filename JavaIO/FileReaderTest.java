package ByteAndCharStream;

import java.io.*;
public class FileReaderTest {

	public static void main(String[] args) throws IOException{
		
		try(
			//创建字符输入流
			FileReader fr = new FileReader("FileReaderTest.java"))
		{
			//创建一个程度为64的竹筒
			char[] cbuf = new char[64];
			//用于保存实际读取的字符数
			int hasRead = 0;
			//使用循环重复取水的过程
			while((hasRead = fr.read(cbuf)) > 0){
				//取出竹筒中的水滴（字符），将字符转换字符串输出
				System.out.print(new String(cbuf, 0, hasRead));
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
