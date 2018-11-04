package com.holiday.common.easyreader.read.base;

import java.util.List;

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

	public void formatter(List<String> line, EasyReaderContext context);
}
