package com.ssl;
/**
 * 根据输入的高度打印由星号组成的三角形
 *@author 时生乐
 */
public class PrintTriangle {

	public static Integer getHeight(String[] args) throws NoParamException{
		Integer height = null;//要打印的三角形的高度
		
		if(args.length == 0) throw new NoParamException("错误：没有输入要打印的三角形的高度！");//抛出异常
		
		try {
			height = Integer.parseInt(args[0]);//获取输入的参数
		} catch (NumberFormatException e) {
			e.printStackTrace();//打印异常
		}
		return height;//返回输入的高度值
	}
	
	public static void main(String[] args) {
		 
		try {
			Integer height = getHeight(args);
			printTriangle(height);
		} catch (NoParamException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据输入的高度打印三角形
	 * 
	 * @param height 要打印的三角形的高度
	 * @return void
	 * 
	 * @author 时生乐
	 */
	public static void printTriangle(Integer height) {
		int i = 1;
		int j = height - 1;
		for(; i <= height; i++, j--){
			int k = j;
			while(k > 0){//打印每行开始的空格
				System.out.print(" ");
				k--;
			}
			
			k = 2*i - 1;
			while(k > 0){//打印每行的*
				System.out.print("*");
				k--;
			}
			System.out.println();
			
		}
	}

}
