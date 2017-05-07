package File;

import java.io.File;

public class FilenameFilterTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		File file = new File(".");
		//使用Lamda表达式(目标类型为FilenameFilter)实现文件过滤器
		//如果文件名以.java为结尾，或者文件对应一个路径，则返回True
		String[] nameList = file.list((dir,name) -> name.endsWith(".java") 
				|| new File(name).isDirectory());
		for(String name : nameList){
			System.out.println(name);
		}

	}

}
