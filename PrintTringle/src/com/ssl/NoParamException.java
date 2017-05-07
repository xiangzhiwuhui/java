package com.ssl;
/**
 * 当没有输入参数的抛出的异常
 */
public class NoParamException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public NoParamException(String msg){
		super(msg);
	}
}
