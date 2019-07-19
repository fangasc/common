package com.holiday.common.easyreader.read.base;

/**
 * 
 * @ClassName:  LineFormatter   
 * @Description:  
 * @author: fangsw
 * @date:   2018年7月1日 下午10:06:28   
 *
 */
@FunctionalInterface
public interface ILineFormatter { 

	public void formatter(String line, EasyReaderContext context);
}
