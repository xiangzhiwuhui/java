package ByteAndCharStream;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamtest {

	public static void main(String[] args)  throws IOException{
		//创建字节输入流
		FileInputStream fis = new FileInputStream("FileInputStreamTest.java");
		//创建一个长度为64的竹筒
		byte[] bbuf = new byte[16];
		//用于保存实际读取的字节数
		int hasRead = 0;
		//使用循环来重复取水的的过程
		while((hasRead = fis.read(bbuf)) > 0){
			//取出竹筒中的水滴（字节），将字节数组转换成字符串输入；
			System.out.println(new String(bbuf, 0, hasRead ));
		}
		fis.close();
	}

}
