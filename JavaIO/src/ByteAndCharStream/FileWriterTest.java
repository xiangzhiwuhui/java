package ByteAndCharStream;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try( FileWriter fw = new FileWriter("xiyouji.txt"))
		{
			fw.write("西游记新主题曲----牛逼网友\r\n");
			fw.write("白龙马蹄儿朝着bi,躲在唐三藏身后有仨傻逼。\r\n");
			fw.write("西天取经去唱戏啊，一场就是几万里。\r\n");
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
	}

}
