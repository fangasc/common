package com.holiday.common.easyreader.read.base;

import java.io.Closeable;

import com.holiday.common.easyreader.common.EasyReaderException;

/**
 * 
 * @ClassName:  IReader   
 * @Description:  
 * @author: fangsw
 * @date:   2018年7月1日 下午10:06:16   
 *
 */
public interface IReader extends Closeable{

	public void read() throws EasyReaderException;
	
}
