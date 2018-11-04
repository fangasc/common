package com.holiday.common.easyreader.read;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.holiday.common.easyreader.common.LogUtil;
import com.holiday.common.easyreader.read.base.AbstractReader;
import com.holiday.common.easyreader.read.base.ILineFormatter;
import com.holiday.common.easyreader.read.base.IReaderFacade;

/**
 * 
 * @ClassName:  V03ExcelReader   
 * @Description:  
 * @author: fangsw
 * @date:   2018年7月1日 下午10:06:36   
 *
 */
public class V03ExcelReader extends AbstractReader{
	
	private static final Logger log = Logger.getLogger("V03ExcelReader");

	public V03ExcelReader(InputStream inputStream, IReaderFacade facade, ILineFormatter operator) {
		super(inputStream, facade, operator);
	}

	@Override
	public void read() {
		try (Workbook workbook = WorkbookFactory.create(getInputStream())) {
			DataFormatter df = new DataFormatter();
			for (Iterator<Sheet> it = workbook.sheetIterator(); it.hasNext();) {
				Sheet sheet = it.next();
				int rows = sheet.getLastRowNum() + 1;
				for (int i = 0; i < rows; i++) {
					Row r = sheet.getRow(i);
					if (r == null) {
						continue;
					}

					List<String> list = new ArrayList<>();
					for (int j = 0; j < r.getLastCellNum(); j++) {
						Cell cell = r.getCell(j);
						list.add(df.formatCellValue(cell));
					}

					filterFlush(list);
				}
			}
			LogUtil.logInfo(log, "parser complete.");
		} catch (Exception e) {
			LogUtil.logErr(log, "read err.", e);
		}
	}


}
