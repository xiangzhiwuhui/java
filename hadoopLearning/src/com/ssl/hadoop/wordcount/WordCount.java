package com.ssl.hadoop.wordcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		if (args.length != 2) {
			System.out.println("程序输入参数为两个，第一个为输入文件或文件夹，第二个为输出文件夹.");
			System.out.println("程序在集群上运行命令例子为：hadoop jar wordcount.jar com.ssl.hadoop.WordCount 输入文件夹 输出文件夹");
			System.exit(1);
		}
		
		//构建一个Job对象
		Job job = Job.getInstance(new Configuration());
		//注意：main方法所在类
		job.setJarByClass(WordCount.class);
		
		//组装Mapper
		job.setMapperClass(WCMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		//组装Reducer
		job.setReducerClass(WCReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true); //true 打印进度详情, false不打印进度详情
		
		//eclipse中打包过程为：在工程上右键->Export->java->JAR file ->...

	}

}
