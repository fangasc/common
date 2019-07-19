package com.holiday.common.easyreader.read;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.holiday.common.easyreader.common.EasyReaderException;
import com.holiday.common.easyreader.read.base.AbstractReader;
import com.holiday.common.easyreader.read.base.ILineFormatter;
import com.holiday.common.easyreader.read.base.IReaderFacade;

public class V07Excel2XmlReader extends AbstractReader{
	
	private static final Logger log = Logger.getLogger("V07Excel2XmlReader");
	
	public V07Excel2XmlReader() {
	}
	public V07Excel2XmlReader(InputStream inputStream, IReaderFacade facade, ILineFormatter operator) {
		super(inputStream, facade, operator);
	}
	
	@Override
	public void read() throws EasyReaderException {
		log.info("[easy-reader] excel2xml reader start.");
		try {
			OPCPackage pkg = OPCPackage.open(getInputStream());
			XSSFReader r = new XSSFReader(pkg);
			SharedStringsTable sst = r.getSharedStringsTable();
			XMLReader parser = fetchSheetParser(sst, facade, operator);
			
			for (Iterator<InputStream> iterator = r.getSheetsData(); iterator.hasNext();) {
				InputStream sheetStream = iterator.next();
				InputSource sheetSource = new InputSource(sheetStream);
				parser.parse(sheetSource);
				close(sheetStream);
			}
		} catch (IOException | OpenXML4JException | SAXException e) {
			throw new EasyReaderException(e);
		}
		log.info("[easy-reader] excel2xml reader end.");
	}

	public void close(InputStream is) throws IOException {
		try {
			if (is != null) {
				is.close();
			}
		} catch (IOException e) {
			throw e;
		}
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst, IReaderFacade facade, ILineFormatter operator) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		ContentHandler handler = new RowHandler(sst, this);
		parser.setContentHandler(handler);
		return parser;
	}
}
