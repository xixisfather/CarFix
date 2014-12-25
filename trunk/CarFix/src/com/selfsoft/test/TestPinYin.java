package com.selfsoft.test;

import com.selfsoft.framework.common.CommonMethod;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class TestPinYin {

	/**
	 * @param args
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		String s = CommonMethod.tranferToPinYin("--“”《》~&%陈传荣 444444asdfr入");
		
		System.out.println("transfer : "+ s);
	}

}
