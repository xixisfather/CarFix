package com.selfsoft.common.exception;

public class MinusException extends Exception {

	public MinusException(){
		super("在不允许负出库的情况下，库存数量不能小于0");
	}

	public MinusException(String exceptionDesc){
		super(exceptionDesc);
	}
}
