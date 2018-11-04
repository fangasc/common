package com.holiday.common.easyreader.read.base;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import com.holiday.common.easyreader.common.LogUtil;

/**
 * 
 * @ClassName:  AbstractReader   
 * @Description:  
 * @author: fangsw
 * @date:   2018年7月1日 下午10:06:04   
 *
 */
public abstract class AbstractReader implements IReader{
	
	private static final Logger log = Logger.getLogger("AbstractReader");

	private InputStream inputStream;
	protected IReaderFacade facade;
	protected ILineFormatter operator;
	protected EasyReaderContext context;
	
	public AbstractReader() {}
	public AbstractReader(InputStream inputStream, IReaderFacade facade, ILineFormatter operator) {
		this.inputStream = inputStream;
		this.facade = facade;
		this.operator = operator;
		context = EasyReaderContext.get();
	}

	@Override
	public void close() {
		try {
			if(inputStream != null) {
				inputStream.close();
			}
		} catch (Exception e) {
			LogUtil.logErr(log, "close inputStream fail.", e);
		}
		LogUtil.logInfo(log, "close inputStream complete.");
	}
	
	public void filterFlush(List<String> line) {
		if(operator != null) {
			operator.formatter(line, context);
		}
		facade.readLine(line, context);
		line.clear();
	}
	
	public IReaderFacade getFacade() {
		return facade;
	}

	public void setFacade(IReaderFacade facade) {
		this.facade = facade;
	}

	public ILineFormatter getOperator() {
		return operator;
	}

	public void setOperator(ILineFormatter operator) {
		this.operator = operator;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
