package com.java.fay.demo;

import com.java.fay.utils.FileUtils;
import com.java.fay.utils.JavaMmapUtils;

public class MmapUtilsDemo {
	public static String PATH = "test.txt";

	public static int COUNT = 1000000;
	public static String content = "this is a test content with a new line \r\n";

	public static int num = 8;
	public static int BUFFER_SIZE = 0x1f400000;// 缓冲区大小为500M

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1024 * 10; i++) {
			sb.append("a");
		}
		System.out.println(sb.toString().getBytes().length);
		JavaMmapUtils.mmapWrite(PATH, sb.toString().getBytes(), COUNT);
		FileUtils.randomWrite(PATH, sb.toString(), COUNT);
	}
}
