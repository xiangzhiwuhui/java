package com.ssl.hadoop.hdfsDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class HDFSDemo {

	FileSystem fs = null;
	
	@Before
	public void init() throws IOException, URISyntaxException{
		fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration());
	}
	
	//向HDFS上传文件测试
	@Test
	public void testUpload() throws Exception{
		//读取本地文件系统的文件
		InputStream in = new FileInputStream("d:\\REDAME.md");
		OutputStream out = fs.create(new Path("/upload.txt"));
		IOUtils.copyBytes(in, out, 4096, true);
	}
	
	public static void main(String[] args) throws Exception {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "ssl");
		
		InputStream in = fs.open(new Path("/scan-data.txt"));
		OutputStream out = new FileOutputStream("d://scan-data-copy.txt");
		IOUtils.copyBytes(in, out, 4096, true);

	}

}
