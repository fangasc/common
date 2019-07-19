package com.holiday.common.easyreader.common;

/**  
 * @Description: 
 * @author: fang-pc  
 * @date: 2018年11月4日 上午10:59:28
 */
public class EasyReaderException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6941199806227542248L;

	public EasyReaderException() {}
	public EasyReaderException(String message, Throwable e) {
		super(message, e);
	}
	
	public EasyReaderException(String message) {
		super(message);
	}
	
	public EasyReaderException(Throwable e) {
		super(e);
	}
}
