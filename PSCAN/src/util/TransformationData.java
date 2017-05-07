package util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import pscan.PSCAN;

public class TransformationData {
	private static final Log LOG = LogFactory.getLog("TransformationData:");
	
	public static void makeAdjacencyListJob(String sInput, String sOutput)
			throws Exception {
		LOG.info("Enter Make Adjacency List Job ");

		Configuration conf = new Configuration();
		conf.set("hadoop.job.history.user.location", "none");
		conf.set("mapred.textoutputformat.separator", " ");
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
		job.waitForCompletion(false);

		LOG.info("Out Make Adjacency List Job");
	}

	public static void main(String[] args) throws Exception {
		makeAdjacencyListJob(args[0], args[1]);
		

	}

}

class MakeAdjacencyListMapper extends
Mapper<Object, Text, Text, Text> {
	String[] sTokens = null;
	String line=null;
	Text tKey = new Text();
	
	Text tValue = new Text();
	
	String node1, node2;
	
	public void map(Object key, Text value, Context context)
		throws IOException, InterruptedException {
		line = value.toString();
		if(!(line.trim().startsWith("#"))){
		sTokens = line.trim().split("\\s{1,}");
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
}

class MakeAdjacencyListReducer extends
Reducer<Text, Text, Text, Text> {
StringBuffer sb = new StringBuffer();

Text tValue = new Text();

public void reduce(Text key, Iterable<Text> values, Context context)
	throws IOException, InterruptedException {
sb.delete(0, sb.length());
//sb.append("#");
//sb.append(key.toString());
for (Text val : values) {
	sb.append(" ");
	sb.append(val.toString());
}
tValue.set(sb.toString());
context.write(new Text(key.toString()), tValue);

}
}


