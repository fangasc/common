package com.holiday.common.easyreader.common;

/**
 * 
 * @ClassName:  FileTypeEnum   
 * @Description:  
 * @author: fangsw
 * @date:   2018年7月1日 下午9:56:32   
 *
 */
public enum FileTypeEnum {

	XLS("V03Excel", ".xls"), XLSX("V07Excel2Xml", ".xlsx"), TXT("Text", ".txt");

	FileTypeEnum(String desc, String suffix) {
		this.desc = desc;
		this.suffix = suffix;
	}

	private String desc;
	private String suffix;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public static FileTypeEnum get(String file) {
		for (FileTypeEnum ft : FileTypeEnum.values()) {
			if (file.endsWith(ft.suffix)) {
				return ft;
			}
		}
		return null;
	}

}
