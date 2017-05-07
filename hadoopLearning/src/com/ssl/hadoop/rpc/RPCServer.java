package com.ssl.hadoop.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCServer {

	public String sayHi(String name){
		return "Hi ~" + name;
	}
	
	public static void main(String[] args) {
		Configuration conf = new Configuration();
//		new RPC.Builder(conf).setProtocol(protocol);
	}
	
}
