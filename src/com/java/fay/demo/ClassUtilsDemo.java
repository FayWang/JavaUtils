package com.java.fay.demo;

import com.java.fay.utils.ClassUtils;

public class ClassUtilsDemo {

	public static void main(String[] args) {
//		ClassUtils.printClassMethods(new String("test"));
//		ClassUtils.printClassFields(new String("test"));
		String url = "https://api.weibo.cn/2/remind/unread_count?";
		ClassUtils.invokeMethod("com.java.fay.test.TestLogLimit", "getIP",url );
	}

}
