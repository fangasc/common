/**
 * 
 */
package com.holiday.common.easyreader.common;

/**  
 * @Description: 
 * @author: fang-pc  
 * @date: 2018年7月15日 上午10:12:50
 */
public class LetterUtil {

	public static final String [] LETTER = {"A", "B", "C", "D", "E", "F", "G", "H" , "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

	public static int getPos(String letter) {
		for(int i = 0; i < LETTER.length; i++) {
			if(LETTER[i].equals(letter)) {
				return i + 1;
			}
		}
		throw new IllegalArgumentException("not found letter.");
	}
}
