package com.ssl.hadoop.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		//接收数据V1
		String line = value.toString();
		//切分数据
		String[] words = line.split(" ");
		//循环
		for(String w : words){
			//出现一次，记做一个一，输出
			context.write(new Text(w), new LongWritable(1));
		}
	}
	

}
