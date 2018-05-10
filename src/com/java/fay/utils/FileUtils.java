package com.java.fay.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * 文件操作工具类
 * 
 * @author xiaofei9
 * @date 2018年5月10日 下午4:04:37
 */
public class FileUtils {
	/**
	 * 获取类对象文件所在的绝对路径(bin目录)
	 * @param className 类文件名
	 */
	public static String getCurrentFilePath(Class<?> c) {
		File file = null;
		file = new File(c.getClass().getResource("/").getPath());
		System.out.println("current path:" + file.getAbsolutePath());
		return file.getAbsolutePath();
	}

	/**
	 * 创建目录
	 * @param directory
	 */
	public static void createDirectory(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			file.mkdir();
		}
		System.out.println("directory path:" + file.getAbsolutePath());
	}

	/**
	 * 使用FileOutputStream写文件
	 * @param fileName
	 * @param content
	 */
	public static void writeFile(String fileName,String content) {
		File file = checkFile(fileName);
		FileOutputStream out = null;
		long start_time = System.currentTimeMillis();
		try {
			out = new FileOutputStream(file, true);
			out.write(content.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("cost time:"
					+ (System.currentTimeMillis() - start_time));
		}
	}
	
	/**
	 * 写文件之前先检查文件目录是否存在，如果不存在，则创建文件目录
	 * @param fileName
	 * @return
	 */
	private static File checkFile(String fileName){
		File file = new File(fileName.toString());
		System.out.println("parent path:" + file.getParent().trim());
		if (null == file.getParent()) {
			file.mkdirs();
		}
		return file;
	}
	
	/**
	 * 使用FileWrite写文件
	 * @param file
	 * @param content
	 */
	public static void writeLog(File file, String content) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static String readFile(String fileName) {
		StringBuilder content = new StringBuilder();
		BufferedReader reader = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(new File(fileName)));
			reader = new BufferedReader(in);
			String line = "";
			while ((line = reader.readLine()) != null) {
				content.append(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}
	
	/**
	 * 随机方式读文件
	 * @param filePath
	 * @throws IOException
	 */
	public static void randomRead(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		RandomAccessFile raf = new RandomAccessFile(new File(filePath), "r");
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = raf.readLine()) != null) {
			sb.append(s);
			// System.out.println(s);
		}
		raf.close();
		fis.close();
	}
	
	/**
	 * 压力测试，随机方式写文件
	 * @param filePath 写入文件路径
	 * @param data 单次写入内容
	 * @param count 循环写入次数
	 */
	public static void randomWrite(String filePath, String data, int count) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File(filePath), true);
			long starttime = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				fileWriter.write(data);	
				fileWriter.flush();
			}
			System.out.println("filewriter cost "
					+ (System.currentTimeMillis() - starttime));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
}
