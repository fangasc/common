package com.holiday.common.easyreader.read;

import static com.holiday.common.easyreader.read.RowHandlerTag.CELL_TAG;
import static com.holiday.common.easyreader.read.RowHandlerTag.ROW_TAG;
import static com.holiday.common.easyreader.read.RowHandlerTag.TYPE_TAG;
import static com.holiday.common.easyreader.read.RowHandlerTag.VALUE_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.holiday.common.easyreader.common.LetterUtil;
import com.holiday.common.easyreader.read.base.EasyReaderContext;

/**
 * 
 * @ClassName:  RowHandler   
 * @Description:  
 * @author: fangsw
 * @date:   2018年7月15日 下午1:56:21   
 *
 */
public class RowHandler extends DefaultHandler{
	private SharedStringsTable sst;
	private V07Excel2XmlReader reader;
	private String lastContents;
	private boolean nextIsString;
	private TreeMap<Integer, String> map = new TreeMap<>();
	private String key = "";

	RowHandler(SharedStringsTable sst, V07Excel2XmlReader reader) {
		this.sst = sst;
		this.reader = reader;
	}

	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if (name.equals(CELL_TAG)) {
			key = attributes.getValue("r");
			String cellType = attributes.getValue(TYPE_TAG);
			if (cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		lastContents = "";
	}
	

	public void endElement(String uri, String localName, String name) throws SAXException {
		if (nextIsString) {
			int idx = Integer.parseInt(lastContents);
			lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
			nextIsString = false;
		}

		if (name.equals(ROW_TAG)) {
			reader.filterFlush(encapList(map));
			map.clear();
		}

		if (name.equals(VALUE_TAG)) {
			map.put(getCol(key), lastContents);
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		lastContents += new String(ch, start, length);
	}
	
	private String encapList(TreeMap<Integer, String> lineMap) {
		if(lineMap == null || lineMap.isEmpty()) {
			return "";
		}
		
		Map<Integer, String> newMap = new TreeMap<>();
		for(int i = 1; i<= lineMap.lastKey(); i++) {
			String val = lineMap.get(i);
			newMap.put(i, val == null || val.length() == 0 ? "" : val);
		}
		List<String> list = newMap.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
		return String.join(EasyReaderContext.get().split, list);
	}
	
	/**
	 * 获取当前解析 列 下标
	 * colName 英文单词（列下标）+行号组成，我只需要列下标
	 * @param colKey
	 * @return
	 */
	private static Integer getCol(String colName) {
		String regExp = "[A-Z]";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(colName);
		
		int num = 0;
		List<Integer> list = new ArrayList<>();
		while (matcher.find()) {
			list.add(LetterUtil.getPos(matcher.group(0)));
		}
		
		for(int i = 0, s = list.size(); i < s; i++) {
			num += list.get(i) * Math.pow(26, s - i -1);
		}
		return num;
	}
	
}
