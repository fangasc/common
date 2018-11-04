package com.holiday.common.easyreader.read.base;

/**
 * 
 * @Description: 
 * @author: fang-pc  
 * @date: 2018年7月14日 下午10:56:33
 */
public class EasyReaderContext {
	
	
	public static EasyReaderContext get() {
		return threadLocal.get();
	}

	private static ThreadLocal<EasyReaderContext> threadLocal = ThreadLocal.withInitial(() -> new EasyReaderContext());
}
