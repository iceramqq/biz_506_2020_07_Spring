package com.biz.book.exec;

import java.util.Map;

public class EnvList {

	public static void main(String[] args) {
		
		Map<String, String> envList;
		envList = System.getenv();
		
		System.out.println("BIZ.COM : "+ envList.get("BIZ.COM"));
		System.out.println("BIZ.COM : "+ envList.get("BIZ.NET"));
	}
}
