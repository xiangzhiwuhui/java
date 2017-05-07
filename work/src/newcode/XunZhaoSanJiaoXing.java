package newcode;

import java.util.ArrayList;
import java.util.Scanner;

/***牛客：寻找三角形***/
public class XunZhaoSanJiaoXing {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Point> rlist = new ArrayList<Point>();
		ArrayList<Point> glist = new ArrayList<Point>();
		ArrayList<Point> blist = new ArrayList<Point>();
		for (int i = 0; i < n; i++) {
			String color = sc.next();
			if (color.equals("R")) {
				rlist.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
			
			if (color.equals("G")) {
				glist.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
			
			if (color.equals("B")) {
				blist.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
		}
		
		double result = ans(rlist, glist, blist);
		System.out.printf("%.5f",result);

	}

	static double ans(ArrayList<Point> rlist, ArrayList<Point> glist,
			ArrayList<Point> blist) {
		
		double rmax = 0;
		if (rlist.size() >= 3) {
			area(rlist);
		}
		double gmax = 0;
		if (glist.size() >= 3) {
			area(glist);
		}
		double bmax = 0;
		if (blist.size() >= 3) {
			area(blist);
		}
		
		double mmax = 0;
		if (rlist.size() > 0 && glist.size() > 0 && blist.size() > 0) {
			mmax = areaMix(rlist, glist, blist);
		}
		
		double temp = Math.max(rmax, gmax);
		double max = Math.max(temp, mmax);
		
		return max;
	}

	static double areaMix(ArrayList<Point> rlist,
			ArrayList<Point> glist, ArrayList<Point> blist) {
		// TODO Auto-generated method stub
		return 0;
	}

	static double area(ArrayList<Point> rArrayList) {
		double max = 0;
		 if (rArrayList.size()>2) {
             for (int i = 0; i < rArrayList.size()-2; i++) {
                 Point aPoint = rArrayList.get(i);
                 for (int j = i+1; j < rArrayList.size()-1; j++) {
                     Point bPoint = rArrayList.get(j);
                     for (int k = j+1; k < rArrayList.size(); k++) {
                         Point cPoint = rArrayList.get(k);
                         double temp = fun(aPoint,bPoint,cPoint);
//                       System.out.println(temp);
                         max = temp>max?temp:max;
                     }
                 }
             }
         }
		
		
		return max;
	}

	static double fun(Point a, Point b, Point c) {
		double area = 0; 

        double[] side = new double[3];//存储三条边的长度; 

        side[0] = Math.sqrt(Math.pow(a.x - b.x,2)+Math.pow(a.y - b.y,2) + Math.pow(a.z - b.z,2));  
        side[1] = Math.sqrt(Math.pow(a.x - c.x,2)+Math.pow(a.y - c.y,2) + Math.pow(a.z - c.z,2)); 
        side[2] = Math.sqrt(Math.pow(c.x - b.x,2)+Math.pow(c.y - b.y,2) + Math.pow(c.z - b.z,2));  

        //不能构成三角形; 
        if(side[0]+side[1]<=side[2] || side[0]+side[2]<=side[1] || side[1]+side[2]<=side[0]) return area;  

        //利用海伦公式。s=sqr(p*(p-a)(p-b)(p-c));  
        double p = (side[0]+side[1]+side[2])/2; //半周长; 
        area = p*(p-side[0])*(p-side[1])*(p-side[2]);  

        return area; 
	}

}

class Point {
	int x;
	int y;
	int z;
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
}
