package com.java.fay.demo;

import com.java.fay.helper.AESHelper;

public class AESDemo {

	public static void main(String[] args) {
		String key = "1234567890123456";
		String content = "Y9Bb+Myk0zqBjiTu6c4DD9CipFJVChk+962IlvUA8CzzFVWO4woUFw0VXkO8UeFayk8SifRjXK4YJE9XMKHUwA==";
		try {
//			String encode = encrypt(content, key);
//			System.out.println("加密后的内容：" + encode);
			System.out.println("解密后的内容：" + AESHelper.decrypt(content, key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
