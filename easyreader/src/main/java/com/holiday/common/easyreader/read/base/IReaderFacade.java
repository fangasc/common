package com.holiday.common.easyreader.read.base;

/**
 * 
 * @ClassName:  IReaderFacade   
 * @Description:  
 * @author: fangsw
 * @date:   2018年7月1日 下午10:06:22   
 *
 */
@FunctionalInterface
public interface IReaderFacade {

	public void readLine(String line, EasyReaderContext context);
	
}
