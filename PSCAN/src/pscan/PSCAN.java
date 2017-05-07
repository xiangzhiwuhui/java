package pscan;

import java.io.*;
import java.net.URI;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.DoubleWritable;

import util.*;

public class PSCAN {
	private static final Log LOG = LogFactory.getLog("shi.PSCAN");

	private static String m_confFileName;

	private static float m_epsilon;

	private static HashMap<String, String> m_options = new HashMap<String, String>();

	private static void getOptions() throws Exception {

		BufferedReader in = new BufferedReader(new FileReader(
				PSCAN.m_confFileName));
		String sLine = null;
		String[] sTokens = null;
		while ((sLine = in.readLine()) != null) {
			if (sLine.startsWith("#"))
				continue;
			sTokens = sLine.split("\\s{1,}");
			PSCAN.m_options.put(sTokens[0], sTokens[1]);
		}
		in.close();
	}

	public static class CalculateSSMapper extends
			Mapper<Object, Text, Text, Text> {
		String line=null;
		String[] sTokens = null;

		Text tKey = new Text();

		int nodeID, neighbor;

		StringBuffer sKey = new StringBuffer();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			line = value.toString();
//			sTokens = line.trim().split(
//					"\\s{0,}:\\s{0,}|\\s{0,},\\s{0,}|\\s{1,}");
			sTokens = line.trim().split("\\s{1,}"); //以一个或多个空格进行分割
			nodeID = Integer.parseInt(sTokens[0]);
			
			for (int i = 1; i < sTokens.length; i++) {
				sKey.delete(0, sKey.length());
				neighbor = Integer.parseInt(sTokens[i]);
				if (nodeID < neighbor) {
					sKey.append(nodeID);
					sKey.append(" ");
					sKey.append(neighbor);
				} else {
					sKey.append(neighbor);
					sKey.append(" ");
					sKey.append(nodeID);
				}
				tKey.set(sKey.toString());
				context.write(tKey, value);
			}
		}
	}

	public static class CalculateSSReducer extends
			Reducer<Text, Text, Text, DoubleWritable> {
		public double epsilon;

		public double ss;

	//	public ArrayList<String> edges = new ArrayList<String>();
		public Iterator<Text> it=null;
		public String node1=null,node2=null;

		public DoubleWritable fValue = new DoubleWritable();

		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			Configuration conf = context.getConfiguration();
			epsilon = Double.parseDouble(conf.get("E"));
		}

		//下面key为顶点对<id1, id2>
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			it=values.iterator();
			node1=it.next().toString(); //其实也是邻接列表
			node2=it.next().toString();
			
		/*	for (Text val : values) {
				edges.add(val.toString());
			}
			if(edges.size()<2){
				LOG.info(key.toString());
				LOG.info(edges.get(0));
			}  */
			
//			ss = Distance.getMinSimilarity(node1, node2);
			ss = Distance.getCosineSimilarity(node1, node2);
			if (!(ss < epsilon)) {
				fValue.set(ss);
				context.write(key, fValue);
			}
		}
	}

	public static void calculateSSJob(String sInput, String sOutput)
			throws Exception {
		LOG.info("Enter Calculate SS ");

//		System.out.println("calculateSSJob:sInput为：" + sInput);
//		System.out.println("calculateSSJob:sOutput 为：" + sOutput);
		Configuration conf = new Configuration();
		conf.set("hadoop.job.history.user.location", "none");

		conf.set("E", "" + PSCAN.m_epsilon);

	//	Set<String> keys = PSCAN.m_options.keySet();
		Iterator it = PSCAN.m_options.keySet().iterator();
		while (it.hasNext()) {
			String param = (String) it.next();
			String value = PSCAN.m_options.get(param);
			conf.set(param, value);
		}

//		Job job = new Job(conf, "Calculate SS");
		Job job = Job.getInstance(conf, "Calculate SS");
		job.setJarByClass(PSCAN.class);
		job.setMapperClass(CalculateSSMapper.class);
		job.setReducerClass(CalculateSSReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(sInput));

		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), conf);
		Path output = new Path(sOutput);
		if (fs.exists(output))
			fs.delete(output, true);

		FileOutputFormat.setOutputPath(job, output);
		job.waitForCompletion(true);

		LOG.info("Out Calculate SS");
	}

	public static class MakeAdjacencyListMapper extends
			Mapper<Object, Text, Text, Text> {
		String[] sTokens = null;
		String line=null;
		Text tKey = new Text();

		Text tValue = new Text();

		String node1, node2;

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			line = value.toString();
			sTokens = line.split("\\s{1,}");
			node1 = sTokens[0];
			node2 = sTokens[1];

			tKey.set(node1);
			tValue.set(node2);
			context.write(tKey, tValue);

			tKey.set(node2);
			tValue.set(node1);
			context.write(tKey, tValue);
		}
	}

	public static class MakeAdjacencyListReducer extends
			Reducer<Text, Text, Text, Text> {
		StringBuffer sb = new StringBuffer();

		Text tValue = new Text();

		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			sb.delete(0, sb.length());
			sb.append("#");
			sb.append(key.toString());
			for (Text val : values) {
				sb.append(",");
				sb.append(val.toString());
			}
			tValue.set(sb.toString());
			context.write(key, tValue);
		}
	}

	public static void makeAdjacencyListJob(String sInput, String sOutput)
			throws Exception {
		LOG.info("Enter Make Adjacency List Job ");

		Configuration conf = new Configuration();
		conf.set("hadoop.job.history.user.location", "none");

	//	Set<String> keys = PSCAN.m_options.keySet();
		Iterator it = PSCAN.m_options.keySet().iterator();
		while (it.hasNext()) {
			String param = (String) it.next();
			String value = PSCAN.m_options.get(param);
			conf.set(param, value);
		}

//		Job job = new Job(conf, "Make Adjacency List");
		Job job = Job.getInstance(conf, "Make Adjacency List");
		job.setJarByClass(PSCAN.class);
		job.setMapperClass(MakeAdjacencyListMapper.class);
		job.setReducerClass(MakeAdjacencyListReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(sInput));

		FileSystem fs = FileSystem.get(conf);
		Path output = new Path(sOutput);
		if (fs.exists(output))
			fs.delete(output, true);

		FileOutputFormat.setOutputPath(job, output);
		job.waitForCompletion(true);

		LOG.info("Out Make Adjacency List Job");
	}

	public static class CC_OneHopMapper extends
			Mapper<Object, Text, Text, Text> {
		String[] sTokens = null;
		String line=null;
		Text tKey = new Text();

		Text tValue = new Text();

		String sNode, sNeighborList;

		int nodeID, neighborID, label;

		String[] nn = null;

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			line = value.toString();
			sTokens = line.split("\\s{1,}");
			sNode = sTokens[0];
			sNeighborList = sTokens[1];

			if (sNeighborList.startsWith("@")) { // the node is disactivated,
				// emit directly
				tKey.set(sNode);
				tValue.set(sNeighborList);
				context.write(tKey, tValue);
			} else { // startwith "#", the node is activated
				nodeID = Integer.parseInt(sNode);
				nn = sNeighborList.split(",");
				label = Integer.parseInt(nn[0].substring(1));
				for (int i = 1; i < nn.length; i++) {
					neighborID = Integer.parseInt(nn[i]);
					if (neighborID > label) {
						tKey.set("" + neighborID);
						tValue.set("" + label);
						context.write(tKey, tValue);
					}
				}
				tKey.set(sNode);
				tValue.set(sNeighborList);
				context.write(tKey, tValue);
			}
		}
	}

	public static class CC_OneHopCombiner extends
			Reducer<Text, Text, Text, Text> {

		Text tValue = new Text();

		String ss = null;

		int smallestID = Integer.MAX_VALUE;

		int iTmp;

		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			smallestID = Integer.MAX_VALUE;
			for (Text val : values) {
				ss = val.toString();
				if (ss.startsWith("#") || ss.startsWith("@")) // is the graph
					// structure
					// information
					context.write(key, val);
				else {
					iTmp = Integer.parseInt(ss);
					if (iTmp < smallestID)
						smallestID = iTmp;
				}
			}
			tValue.set("" + smallestID);
			context.write(key, tValue);
		}
	}

	public static class CC_OneHopReducer extends
			Reducer<Text, Text, Text, Text> {
		Text tValue = new Text();

		String ss = null;

		String structureInfo = null;

		int smallestID = Integer.MAX_VALUE;

		int iTmp;
		int index;
		int curLabel; 

		StringBuffer sValue = new StringBuffer();

		boolean needIterate = false;

		String sFileName = null;

		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			smallestID = Integer.MAX_VALUE;
			sValue.delete(0, sValue.length());
			for (Text val : values) {
				ss = val.toString();
				if (ss.startsWith("#") || ss.startsWith("@")) // is the graph
					// structure
					// information
					structureInfo = ss;
				else {
					iTmp = Integer.parseInt(ss);
					if (iTmp < smallestID)
						smallestID = iTmp;
				}
			}
			index = structureInfo.indexOf(",");
			curLabel = Integer.parseInt(structureInfo.substring(1, index));
			if (smallestID < curLabel) { // update the label, and activate
											// the node
				sValue.append("#");
				sValue.append(smallestID);
				sValue.append(structureInfo.substring(index));
				if (!needIterate) { // flag to indicate if it needs iteration
					needIterate = true;
					sFileName = "file" + key.toString();
				}
			} else { // do not need to update the label, and disactivate the
						// node
				sValue.append("@");
				sValue.append(structureInfo.substring(1));
			}
			tValue.set(sValue.toString());
			context.write(key, tValue);
		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			if (needIterate) {
				Configuration conf = context.getConfiguration();
				FileSystem fs = FileSystem.get(conf);
				String sFlagPath = conf.get("FlagPath");
				Path flagFilePath = new Path(sFlagPath + "/" + sFileName);
				PrintWriter flagFile = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(fs.create(flagFilePath))));
				flagFile.close();
			}
		}
	}

	public static void CC_OneHopJob(String sInput, String sOutput,
			String sFlagPath, String jobName) throws Exception {
		LOG.info("Enter CC One Hop Job "+jobName);

		Configuration conf = new Configuration();
		conf.set("hadoop.job.history.user.location", "none");

		conf.set("FlagPath", sFlagPath);

	//	Set<String> keys = PSCAN.m_options.keySet();
		Iterator it = PSCAN.m_options.keySet().iterator();
		while (it.hasNext()) {
			String param = (String) it.next();
			String value = PSCAN.m_options.get(param);
			conf.set(param, value);
		}

//		Job job = new Job(conf, jobName);
		Job job = Job.getInstance(conf, jobName);
		job.setJarByClass(PSCAN.class);
		job.setMapperClass(CC_OneHopMapper.class);
		job.setCombinerClass(CC_OneHopCombiner.class);
		job.setReducerClass(CC_OneHopReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(sInput));

		FileSystem fs = FileSystem.get(conf);
		Path output = new Path(sOutput);
		if (fs.exists(output))
			fs.delete(output, true);

		FileOutputFormat.setOutputPath(job, output);
		job.waitForCompletion(true);

		LOG.info("Out CC One Hop Job "+jobName);
	}

	public static class LabelMapper extends
			Mapper<Object, Text, Text, Text> {
		String[] sTokens = null;
		String line=null;
		String nodeID;
		String label;
		int index;
		
		Text tKey=new Text();
		Text tValue=new Text();
		
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			line = value.toString();
			sTokens = line.split("\\s{1,}");
			nodeID = sTokens[0];
			
			index=sTokens[1].indexOf(",");
			label=sTokens[1].substring(1, index);
			
			tKey.set(label);
			tValue.set(nodeID);			
			context.write(tKey, tValue);
		}
	}

	public static class LabelReducer extends
			Reducer<Text, Text, Text, Text> {
		StringBuffer sb = new StringBuffer();
		Text tValue = new Text();

		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			sb.delete(0, sb.length());			
			for (Text val : values) {				
				sb.append(val.toString());
				sb.append(",");
			}
			sb.substring(0, sb.length()-1);
			tValue.set(sb.toString());
			context.write(key, tValue);
		}
	}

	public static void labelJob(String sInput, String sOutput)
			throws Exception {
		LOG.info("Enter SCAN Label Job ");

		Configuration conf = new Configuration();
		conf.set("hadoop.job.history.user.location", "none");

////		Set<String> keys = PSCAN.m_options.keySet();
		Iterator it = PSCAN.m_options.keySet().iterator();
		while (it.hasNext()) {
			String param = (String) it.next();
			String value = PSCAN.m_options.get(param);
			conf.set(param, value);
		}

//		Job job = new Job(conf, "SCAN Label Job");
		Job job = Job.getInstance(conf, "SCAN Label Job");
		job.setJarByClass(PSCAN.class);
		job.setMapperClass(LabelMapper.class);
		job.setReducerClass(LabelReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(sInput));

		FileSystem fs = FileSystem.get(conf);
		Path output = new Path(sOutput);
		if (fs.exists(output))
			fs.delete(output, true);

		FileOutputFormat.setOutputPath(job, output);
		job.waitForCompletion(true);

		LOG.info("Out SCAN Label Job");
	}

	public static void main(String[] args) throws Exception {

		//System.out.println("参数个数为：" + args.length);
		if (args.length != 5) {
			System.out
			.println("PSCAN Usage: <邻接列表表示的图的路径> <path prefix> <epsilon> <hadoopconf> <max iter num>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), conf);

		PSCAN.m_confFileName = args[3];
		PSCAN.getOptions();

		String sInputGraph = args[0];
		String sPathPrefix = args[1];
		PSCAN.m_epsilon = Float.parseFloat(args[2]);

		long start = System.currentTimeMillis();

		String sEdgeListPath = sPathPrefix + "/edgeList-" + PSCAN.m_epsilon;
//		System.out.println("sEdgeListPath为：" + sEdgeListPath);
//		System.out.println("sInputGraph为：" + sInputGraph);
		calculateSSJob(sInputGraph, sEdgeListPath); //计算两个顶点之间的 相似性

		String sAdjacencyListPath = sPathPrefix + "/adjacencyList-"
				+ PSCAN.m_epsilon;
		makeAdjacencyListJob(sEdgeListPath, sAdjacencyListPath);//求删去相似度小于E的边后的顶点的邻接列表

		String sTmpOutput = sPathPrefix + "/output-a";
		String sFlagPath = sPathPrefix + "/flagPath";
		fs.mkdirs(new Path(sFlagPath));

		boolean needIterate = true;
		Path oneFile = null;
		
		String sTmpInput=sAdjacencyListPath;
		int maxIterNum=Integer.parseInt(args[4]);
		int iter=0;	
		long startIter = System.currentTimeMillis();
		while (needIterate) {	
			iter++;
			CC_OneHopJob(sTmpInput, sTmpOutput, sFlagPath,"Iter["+iter+"]");
			

			Path flagPath = new Path(sFlagPath);
			FileStatus[] files = fs.listStatus(flagPath);
			if (files.length > 0) { // need iterate
				for (int i = 0; i < files.length; i++) {
					oneFile = files[i].getPath();
					fs.delete(oneFile, true);
				}
				if(iter%2==1){
					sTmpInput=sPathPrefix + "/output-a";
					sTmpOutput=sPathPrefix + "/output-b";
				}else{
					sTmpInput=sPathPrefix + "/output-b";
					sTmpOutput=sPathPrefix + "/output-a";
				}
				
			} else { // need not iterate
				needIterate = false;
			}
			if(iter>=maxIterNum)
				needIterate=false;
		}
		long endIter = System.currentTimeMillis();
		
		String sLabelPath= sPathPrefix+"/labelPath";
		labelJob(sTmpOutput, sLabelPath);

		long end = System.currentTimeMillis();

		String sResultsFile = sPathPrefix + "/evaluations-"+ PSCAN.m_epsilon;
		PrintWriter evaluationFile = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(fs.create(new Path(sResultsFile)))));

	/*	double ari = Evaluation.getAdjustedRandIndex(fs,
				sLabelPath, args[4]);

		double nmi = Evaluation.getNMI(fs, sLabelPath, args[4]);   
		
		evaluationFile.println("IterNum:"+iter+" >> ARI:" + ari + "; NMI:" + nmi + "; runtime="
				+ (end - start));
		*/
		
		evaluationFile.println("IterNum:"+iter+"; iter time:" + (endIter-startIter)*1.0/1000 + "s; total runtime:"
				+ (end - start)*1.0/1000 + "s;");
		
		evaluationFile.close();

		System.out.println("Job Completer PSCAN 999");
	}

}
