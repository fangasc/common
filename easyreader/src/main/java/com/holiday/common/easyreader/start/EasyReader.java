package com.holiday.common.easyreader.start;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import com.holiday.common.easyreader.common.FileTypeEnum;
import com.holiday.common.easyreader.common.LogUtil;
import com.holiday.common.easyreader.read.base.ILineFormatter;
import com.holiday.common.easyreader.read.base.IReader;
import com.holiday.common.easyreader.read.base.IReaderFacade;

/**
 * 
 * @ClassName: EasyReader
 * @Description:
 * @author: fangsw
 * @date: 2018年7月1日 下午3:10:52
 *
 */
public class EasyReader {

	private static final Logger log = Logger.getLogger("EasyReader");

	private static String PACKAGE_PATH = "com.daily.tool.read.%sReader";
	
	public static void reader(InputStream is, FileTypeEnum ft, IReaderFacade rf) {
		reader(is, ft, rf, null);
	}

	public static void reader(InputStream is, FileTypeEnum ft, IReaderFacade rf, ILineFormatter lf) {
		try (IReader reader = createReader(ft, is, rf, lf)) {
			long start = System.currentTimeMillis();

			reader.read();

			LogUtil.logInfo(log, "reader spend time.====>" + +(System.currentTimeMillis() - start) + "ms");
		} catch (FileNotFoundException e) {
			LogUtil.logErr(log, "file not found.", e);
		} catch (IOException e) {
			LogUtil.logErr(log, "file read err.", e);
		} catch(Exception e) {
			LogUtil.logErr(log, "reader err.", e);
		}
	}

	public static IReader createReader(FileTypeEnum ft, InputStream is, IReaderFacade rf, ILineFormatter op) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(String.format(PACKAGE_PATH, ft.getDesc()));
		Constructor<?> c = clazz.getConstructor(InputStream.class, IReaderFacade.class, ILineFormatter.class);
		return (IReader) c.newInstance(is, rf, op);
	}

}
