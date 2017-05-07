package util;

import java.util.*;

public class Distance {
	static HashSet<String> hsNode1=new HashSet<String>();
	static String[] sTokens=null;
	public static double getCosineSimilarity(String node1, String node2){
		double sim=0;
		
		hsNode1.clear();
		sTokens=node1.trim().split("\\s{0,}:\\s{0,}|\\s{0,},\\s{0,}|\\s{1,}");
		for(int i=0;i<sTokens.length;i++)
			hsNode1.add(sTokens[i]);		
		sTokens=node2.trim().split("\\s{0,}:\\s{0,}|\\s{0,},\\s{0,}|\\s{1,}");
		for(int i=0;i<sTokens.length;i++){
			if(hsNode1.contains(sTokens[i]))
				sim+=1;
		}
		sim=sim/(Math.sqrt(hsNode1.size())*Math.sqrt(sTokens.length));		
		return sim;
	}
	
	public static double getMinSimilarity(String node1, String node2){
		double sim=0;
		
		hsNode1.clear();
		sTokens=node1.trim().split("\\s{0,}:\\s{0,}|\\s{0,},\\s{0,}|\\s{1,}");
		for(int i=0;i<sTokens.length;i++)
			hsNode1.add(sTokens[i]);		
		sTokens=node2.trim().split("\\s{0,}:\\s{0,}|\\s{0,},\\s{0,}|\\s{1,}");
		for(int i=0;i<sTokens.length;i++){
			if(hsNode1.contains(sTokens[i]))
				sim+=1;
		}
		if(hsNode1.size()<sTokens.length)
			sim=sim/hsNode1.size();
		else
			sim=sim/sTokens.length;			
		return sim;
	}

}
