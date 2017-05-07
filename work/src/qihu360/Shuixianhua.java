package qihu360;
/*编程试题：水仙花  
 * 
题目描述：
春天是鲜花的季节，水仙花就是其中最迷人的代表，数学上有个水仙花数，他是这样定义的：
“水仙花数”是指一个三位数，它的各位数字的立方和等于其本身，比如：153=1^3+5^3+3^3。
现在要求输出所有在m和n范围内的水仙花数。

输入
输入数据有多组，每组占一行，包括两个整数m和n（100<=m<=n<=999）。
输出
对于每个测试实例，要求输出所有在给定范围内的水仙花数，就是说，输出的水仙花数必须大于等于m,并且小于等于n，如果有多个，则要求从小到大排列在一行内输出，之间用一个空格隔开;
如果给定的范围内不存在水仙花数，则输出no;
每个测试实例的输出占一行。

样例输入
100 120
300 380
样例输出
no
370 371

使用语言：JAVA 赛码网
参考正解代码如下：*/

import java.util.Scanner;
public class Shuixianhua{
    public static void main(String args[]){
    	
    	System.out.printf("%10.4f", Math.sqrt(10));
          Scanner reader=new Scanner(System.in);
          while(reader.hasNextInt()){
              int m=reader.nextInt();
              int n=reader.nextInt();
              if(100<=m&&m<=n&&n<=999){
                  int j=0;
                  for(int i=m;i<=n;i++)
                  {
                      int geWei,shiWei,baiWei;
                       baiWei=i/100;
                       shiWei=(i-baiWei*100)/10;
                       geWei=i-baiWei*100-shiWei*10;
                   if(i==geWei*geWei*geWei+shiWei*shiWei*shiWei+baiWei*baiWei*baiWei)
                   {j=j+1;
                   if(j>1){
                       System.out.print(" "+i);
                   }
                   else{
                       System.out.print(i);
                   }
                    
                   }
                               }
                  if(j==0){
                      System.out.print("no");
                  }
                  System.out.println();
              }
          }
    }
}

