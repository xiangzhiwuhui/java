package ByteAndCharStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try(
				FileOutputStream fos = new FileOutputStream("test.txt");
				PrintStream ps = new PrintStream(fos)
			)
			{
				//使用PrintStream执行输出
				ps.println("普通字符串");
				//直接使用PrintStream输出对象
				ps.print(new PrintStreamTest());
			}
		catch(IOException ioe){
			ioe.printStackTrace();
		}

	}

}
