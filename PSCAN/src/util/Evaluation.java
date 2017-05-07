package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Evaluation {
	public static double getRandIndex(FileSystem fs, String sLabelsPathName,
			String sTrueClassFileName) throws Exception {
		Path labelsPath = new Path(sLabelsPathName);
		FileStatus[] files = fs.listStatus(labelsPath);

		Path oneFile = null;
		String sLine = null;
		int counter = 0;

		String[] sTokens = null;
		BufferedReader in = null;

		HashMap<Integer, Integer> hmLabel = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> hmTrueClass = new HashMap<Integer, Integer>();
		for (int i = 0; i < files.length; i++) {
			oneFile = files[i].getPath();
			if ((oneFile.toString()).indexOf("part") == -1) // is log file
				continue;

			in = new BufferedReader(new InputStreamReader(fs.open(oneFile)));
			while ((sLine = in.readLine()) != null) {
				sTokens = sLine.trim().split("\\s{0,},\\s{0,}|\\s{1,}");
				Integer label = new Integer(sTokens[0]);
				for (int j = 1; j < sTokens.length; j++) {
					hmLabel.put(new Integer(sTokens[j]), label);
				}
			}
			in.close();
		}

		Path trueClassPath = new Path(sTrueClassFileName);
		in = new BufferedReader(new InputStreamReader(fs.open(trueClassPath)));
		while ((sLine = in.readLine()) != null) {
			sTokens = sLine.trim().split("\\s{0,},\\s{0,}|\\s{0,}:\\s{0,}");
			Integer label = new Integer(sTokens[0]);
			for (int j = 1; j < sTokens.length; j++) {
				hmTrueClass.put(new Integer(sTokens[j]), label);
			}
		}
		in.close();
		// Compute Rand Index
		Iterator itTrueClass = hmTrueClass.keySet().iterator();
		Iterator itLabel = null;
		Integer key1 = null, key2 = null;

		while (itTrueClass.hasNext()) {
			key1 = (Integer) itTrueClass.next();
			itLabel = hmLabel.keySet().iterator();
			while (itLabel.hasNext()) {
				key2 = (Integer) itLabel.next();
				if (key1.equals(key2))
					continue;
				if (hmTrueClass.get(key1).equals(hmTrueClass.get(key2))
						&& hmLabel.get(key1).equals(hmLabel.get(key2)))
					counter++;
				else if (!hmTrueClass.get(key1).equals(hmTrueClass.get(key2))
						&& !hmLabel.get(key1).equals(hmLabel.get(key2)))
					counter++;
			}
		}
		System.out.println("hmTrueClass.size():" + hmTrueClass.size());
		System.out.println("hmLabel.size():" + hmLabel.size());
		double RI = (double) counter
				/ ((double)hmTrueClass.size() * (hmTrueClass.size() - 1));
		return RI;
	}

	public static double getAdjustedRandIndex(FileSystem fs,
			String sLabelsPathName, String sTrueClassFileName) throws Exception {
		
		Path labelsPath = new Path(sLabelsPathName);
		FileStatus[] files = fs.listStatus(labelsPath);

		Path oneFile = null;
		String sLine = null;
		int counter = 0;

		String[] sTokens = null;
		BufferedReader in = null;

		// calculate the nubmer of clusters
		for (int i = 0; i < files.length; i++) {
			oneFile = files[i].getPath();
			if ((oneFile.toString()).indexOf("part") == -1) // is log file
				continue;

			in = new BufferedReader(new InputStreamReader(fs.open(oneFile)));
			while ((sLine = in.readLine()) != null) {
				counter++;
			}
			in.close();
		}
		int clusterNum = counter;
		System.out.println("Cluster Number:" + clusterNum);

		HashSet<Integer>[] clusters = new HashSet[clusterNum];

		counter = 0;
		int nodeNumInCluster = 0;
		for (int i = 0; i < files.length; i++) {
			oneFile = files[i].getPath();
			if ((oneFile.toString()).indexOf("part") == -1) // is log file
				continue;

			in = new BufferedReader(new InputStreamReader(fs.open(oneFile)));
			while ((sLine = in.readLine()) != null) {
				clusters[counter] = new HashSet<Integer>();
				sTokens = sLine.trim().split("\\s{0,},\\s{0,}|\\s{1,}");
				for (int j = 1; j < sTokens.length; j++) {
					clusters[counter].add(new Integer(sTokens[j]));
					nodeNumInCluster++;
				}
				counter++;
			}
			in.close();
		}
		System.out.println("Node number in clusters:" + nodeNumInCluster);

		// calculate the number of true classes
		counter = 0;
		Path trueClassPath = new Path(sTrueClassFileName);
		in = new BufferedReader(new InputStreamReader(fs.open(trueClassPath)));
		while ((sLine = in.readLine()) != null) {
			counter++;
		}
		in.close();
		int trueClassNum = counter;

		System.out.println("True class Number:" + trueClassNum);

		HashSet<Integer>[] trueClasses = new HashSet[trueClassNum];

		counter = 0;
		int nodeNumInTrueclass = 0;
		in = new BufferedReader(new InputStreamReader(fs.open(trueClassPath)));
		while ((sLine = in.readLine()) != null) {
			trueClasses[counter] = new HashSet<Integer>();
			sTokens = sLine.trim().split("\\s{0,},\\s{0,}|\\s{0,}:\\s{0,}");
			for (int j = 1; j < sTokens.length; j++) {
				trueClasses[counter].add(new Integer(sTokens[j]));
				nodeNumInTrueclass++;
			}
			counter++;
		}
		in.close();
		System.out.println("Node number in true classes:" + nodeNumInTrueclass);
		
		int outlierNum=nodeNumInTrueclass-nodeNumInCluster;
		System.out.println("Outlier number:" + outlierNum);

		// Compute Adjusted Rand Index
		int[][] freq_x_y = new int[clusterNum+outlierNum][];
		for (int i = 0; i < clusterNum+outlierNum; i++)
			freq_x_y[i] = new int[trueClassNum];

	/*	for (int i = 0; i < clusterNum; i++) {
			Iterator it = clusters[i].iterator();
			while (it.hasNext()) {
				Integer node = (Integer) it.next();
				for (int j = 0; j < trueClassNum; j++) {
					if (trueClasses[j].contains(node)) {
						freq_x_y[i][j]++;
						break;
					}
				}
			}
		}   */
		
		counter=0;
		for (int j = 0; j < trueClassNum; j++) {
			Iterator it = trueClasses[j].iterator();
			while (it.hasNext()) {
				Integer node = (Integer) it.next();
				int i = 0;
				for (; i < clusterNum; i++) {
					if (clusters[i].contains(node)) {
						freq_x_y[i][j]++;
						break;
					}
				}
				if(i>=clusterNum){
					freq_x_y[clusterNum+counter][j]++;
					counter++;
				}					
			}
		}
		
		

	//	nodeNum = 0;

		int[] freq_x = new int[clusterNum+outlierNum];
		int[] freq_y = new int[trueClassNum];
		for (int i = 0; i < clusterNum+outlierNum; i++) {
			for (int j = 0; j < trueClassNum; j++)
				freq_x[i] += freq_x_y[i][j];
	//		nodeNum += freq_x[i];
		}
//		System.out.println("Node number in true freq_x:" + nodeNum);

	//	nodeNum = 0;
		for (int j = 0; j < trueClassNum; j++) {
			for (int i = 0; i < clusterNum+outlierNum; i++)
				freq_y[j] += freq_x_y[i][j];
	//		nodeNum += freq_y[j];
		}
//		System.out.println("Node number in true freq_y:" + nodeNum);
		/*
		 * From Wikipedia:
		 * AdjustedRandIndex=(Index-ExpectedIndex)/(MaxIndex-ExpectedIndex)
		 * 
		 */
		long Index = 0;
		for (int i = 0; i < clusterNum+outlierNum; i++) {
			for (int j = 0; j < trueClassNum; j++) {
				Index += freq_x_y[i][j] * (freq_x_y[i][j] - 1) / 2;
			}
		}

		long tmp_x = 0;
		for (int i = 0; i < clusterNum+outlierNum; i++) {
			tmp_x += freq_x[i] * (freq_x[i] - 1) / 2;
		}
		long tmp_y = 0;
		for (int j = 0; j < trueClassNum; j++) {
			tmp_y += freq_y[j] * (freq_y[j] - 1) / 2;
		}

		double ExpectedIndex = (double) tmp_x * (double)tmp_y
				/ ((double)nodeNumInTrueclass * (double)(nodeNumInTrueclass - 1) / 2);

		double MaxIndex = (double) (tmp_x + tmp_y) / 2;

		// test codes
/*		for (int i = 0; i < clusterNum; i++) {
			for (int j = 0; j < trueClassNum; j++) {
				System.out.println("freq_x_y[" + i + "][" + j + "]"
						+ freq_x_y[i][j]);
			}
		}    */
		System.out.println("Index:" + Index);

/*		for (int i = 0; i < clusterNum; i++) {
			System.out.println("freq_x[" + i + "]" + freq_x[i]);
		}
		*/
//		System.out.println("tmp_x:" + tmp_x);

/*		for (int j = 0; j < trueClassNum; j++) {
			System.out.println("freq_y[" + j + "]" + freq_y[j]);
		}
		*/
//		System.out.println("tmp_y:" + tmp_y);

		System.out.println("ExpectedIndex:" + ExpectedIndex);
		System.out.println("MaxIndex:" + MaxIndex);

		double ARI = (Index - ExpectedIndex) / (MaxIndex - ExpectedIndex);

		return ARI;
	}

	public static double getNMI(FileSystem fs, String sLabelsPathName,
			String sTrueClassFileName) throws Exception {
		Path labelsPath = new Path(sLabelsPathName);
		FileStatus[] files = fs.listStatus(labelsPath);

		Path oneFile = null;
		String sLine = null;
		int counter = 0;

		String[] sTokens = null;
		BufferedReader in = null;

		// calculate the nubmer of clusters
		for (int i = 0; i < files.length; i++) {
			oneFile = files[i].getPath();
			if ((oneFile.toString()).indexOf("part") == -1) // is log file
				continue;

			in = new BufferedReader(new InputStreamReader(fs.open(oneFile)));
			while ((sLine = in.readLine()) != null) {
				counter++;
			}
			in.close();
		}
		int clusterNum = counter;
		System.out.println("Cluster Number:" + clusterNum);

		HashSet<Integer>[] clusters = new HashSet[clusterNum];

		counter = 0;
		int nodeNumInCluster=0;
		for (int i = 0; i < files.length; i++) {
			oneFile = files[i].getPath();
			if ((oneFile.toString()).indexOf("part") == -1) // is log file
				continue;

			in = new BufferedReader(new InputStreamReader(fs.open(oneFile)));
			while ((sLine = in.readLine()) != null) {
				clusters[counter] = new HashSet<Integer>();
				sTokens = sLine.trim().split("\\s{0,},\\s{0,}|\\s{1,}");
				for (int j = 1; j < sTokens.length; j++) {
					clusters[counter].add(new Integer(sTokens[j]));
					nodeNumInCluster++;
				}
				counter++;
			}
			in.close();
		}
		System.out.println("Node number in clusters:" + nodeNumInCluster);

		// calculate the number of true classes
		counter = 0;
		Path trueClassPath = new Path(sTrueClassFileName);
		in = new BufferedReader(new InputStreamReader(fs.open(trueClassPath)));
		while ((sLine = in.readLine()) != null) {
			counter++;
		}
		in.close();
		int trueClassNum = counter;

		System.out.println("True class Number:" + trueClassNum);

		HashSet<Integer>[] trueClasses = new HashSet[trueClassNum];

		counter = 0;
		int nodeNumInTrueclass = 0;
		in = new BufferedReader(new InputStreamReader(fs.open(trueClassPath)));
		while ((sLine = in.readLine()) != null) {
			trueClasses[counter] = new HashSet<Integer>();
			sTokens = sLine.trim().split("\\s{0,},\\s{0,}|\\s{0,}:\\s{0,}");
			for (int j = 1; j < sTokens.length; j++) {
				trueClasses[counter].add(new Integer(sTokens[j]));
				nodeNumInTrueclass++;
			}
			counter++;
		}
		in.close();
		System.out.println("Node number in true classes:" + nodeNumInTrueclass);
		
		int outlierNum=nodeNumInTrueclass-nodeNumInCluster;
		System.out.println("Outlier number:" + outlierNum);

		// Compute NMI
		double[][] prob_x_y = new double[clusterNum+outlierNum][];
		for (int i = 0; i < clusterNum+outlierNum; i++)
			prob_x_y[i] = new double[trueClassNum];

	/*	for (int i = 0; i < clusterNum; i++) {
			Iterator it = clusters[i].iterator();
			while (it.hasNext()) {
				Integer node = (Integer) it.next();
				for (int j = 0; j < trueClassNum; j++) {
					if (trueClasses[j].contains(node)) {
						prob_x_y[i][j] += 1;
						break;
					}
				}
			}
		}   */		
		
		counter=0;
		for (int j = 0; j < trueClassNum; j++) {
			Iterator it = trueClasses[j].iterator();
			while (it.hasNext()) {
				Integer node = (Integer) it.next();
				int i = 0;
				for (; i < clusterNum; i++) {
					if (clusters[i].contains(node)) {
						prob_x_y[i][j] += 1;
						break;
					}
				}
				if(i>=clusterNum){
					prob_x_y[clusterNum+counter][j]+=1;
					counter++;
				}					
			}
		}
		

		for (int i = 0; i < clusterNum+outlierNum; i++) {
			for (int j = 0; j < trueClassNum; j++) {
				prob_x_y[i][j] = prob_x_y[i][j] / nodeNumInTrueclass;
			}
		}

		double[] prob_x = new double[clusterNum+outlierNum];
		double[] prob_y = new double[trueClassNum];
		for (int i = 0; i < clusterNum+outlierNum; i++)
			for (int j = 0; j < trueClassNum; j++)
				prob_x[i] += prob_x_y[i][j];

		for (int j = 0; j < trueClassNum; j++)
			for (int i = 0; i < clusterNum+outlierNum; i++)
				prob_y[j] += prob_x_y[i][j];

		// test codes
/*		for (int i = 0; i < clusterNum; i++) {
			for (int j = 0; j < trueClassNum; j++) {
				System.out.println("prob_x_y[" + i + "][" + j + "]"
						+ prob_x_y[i][j]);
			}
		}   */

		/*
		 * From Wikipedia: NMI=2*I(X;Y)/(H(X)+H(Y))
		 * 
		 */
		double MI_x_y = 0d;
		for (int i = 0; i < clusterNum+outlierNum; i++) {
			for (int j = 0; j < trueClassNum; j++) {
				if (prob_x_y[i][j] < 1e-10) { // is zero
					continue;
				}
				MI_x_y += prob_x_y[i][j]
						* Math.log(prob_x_y[i][j] / (prob_x[i] * prob_y[j]))
						/ Math.log(2);
		/*		System.out.println("prob_x_y[i][j] :" + prob_x_y[i][j]);
				System.out
						.println("Math.log(prob_x_y[i][j]/(prob_x[i]*prob_y[j])) :"
								+ Math.log(prob_x_y[i][j]
										/ (prob_x[i] * prob_y[j])));
				System.out.println("(prob_x[i] :" + prob_x[i]);
				System.out.println("prob_y[j]:" + prob_y[j]);
				System.out.println("Math.log(2):" + Math.log(2));
				System.out.println(i + " " + j + ":" + prob_x_y[i][j]
						* Math.log(prob_x_y[i][j] / (prob_x[i] * prob_y[j]))
						/ Math.log(2));  */
			}
		}

		double H_x = 0d;
		for (int i = 0; i < clusterNum+outlierNum; i++) {
			if (prob_x[i] < 1e-10) { // is zero
				continue;
			}
			H_x += -prob_x[i] * Math.log(prob_x[i]) / Math.log(2);
		}
		double H_y = 0d;
		for (int j = 0; j < trueClassNum; j++) {
			if (prob_y[j] < 1e-10) {
				continue;
			}
			H_y += -prob_y[j] * Math.log(prob_y[j]) / Math.log(2);
		}

		// test codes
	/*	for (int i = 0; i < clusterNum; i++) {
			for (int j = 0; j < trueClassNum; j++) {
				System.out.println("prob_x_y[" + i + "][" + j + "]"
						+ prob_x_y[i][j]);
			}
		}
		System.out.println("MI_x_y:" + MI_x_y);

		for (int i = 0; i < clusterNum; i++) {
			System.out.println("prob_x[" + i + "]" + prob_x[i]);
		}
		System.out.println("prob_x:" + prob_x);

		for (int j = 0; j < trueClassNum; j++) {
			System.out.println("prob_y[" + j + "]" + prob_y[j]);
		}
		System.out.println("prob_y:" + prob_y);

		System.out.println("H_x:" + H_x);
		System.out.println("H_y:" + H_y);
		*/
		double NMI = 2 * (MI_x_y) / (H_x + H_y);

		return NMI;
	}

}
