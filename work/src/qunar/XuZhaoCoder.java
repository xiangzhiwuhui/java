package qunar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*请设计一个高效算法，再给定的字符串数组中，找到包含"Coder"的字符串(不区分大小写)，
 * 并将其作为一个新的数组返回。结果字符串的顺序按照"Coder"出现的次数递减排列，若
 * 两个串中"Coder"出现的次数相同，则保持他们在原数组中的位置关系。
 * 给定一个字符串数组A和它的大小n，请返回结果数组。保证原数组大小小于等于300,其中
 * 每个串的长度小于等于200。同时保证一定存在包含coder的字符串。
 * 
 * 测试样例：
 * 
 * ["i am a coder","Coder Coder","Code"],3
 * 返回：["Coder Coder","i am a coder"]
 * 
 */
public class XuZhaoCoder {

	public static void main(String[] args) {
		String[] A = {"i am a coder", "Coder Coder", "Code"};
		System.out.println(Arrays.toString(findCoder(A, 3)));
	}
	
	 public static String[] findCoder(String[] A, int n) {
		 ArrayList<Recorder> result = new ArrayList<Recorder>();
		 
		 int sum = 0; //记录不包含coder的字符串个数
		 Pattern pattern = Pattern.compile("coder", Pattern.CASE_INSENSITIVE);
		 for (int i = 0; i < A.length; i++) {
			Matcher matcher = pattern.matcher(A[i]);
			int count = 0;
			while (matcher.find()) {
				count++;
			}
			result.add(new Recorder(A[i], i, count));
			if (count == 0) {
				sum++;
			}
		 }

		 Collections.sort(result, new Comparator<Recorder>(){

			@Override
			public int compare(Recorder o1, Recorder o2) {
				if (o1.getCount() != o2.getCount()) {
					return o2.getCount() - o1.getCount();
				}else {
					return o1.getIndex() - o2.getIndex();
				}
			}
			 
		 });
		 
		 String[] sorted = new String[n-sum];
		 for (int i = 0; i < n-sum; i++) {
			sorted[i] = result.get(i).getData();
		}
		 return sorted;
	    }
	
}

class Recorder{
	
		private String data; //字符串
		private int index; //在原数组中的位置
		private int count; //含有多少个coder
		public Recorder(String data, int index, int count){
			this.data = data;
			this.index = index;
			this.count = count;
		}
		
		public String getData(){return data;}
		public int getIndex(){return index;}
		public int getCount(){return count;}
}











