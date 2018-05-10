package com.java.fay.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class JavaMmapUtils {
	/**
	 * mmap读文件
	 * @param filePath
	 * @param BUFFER_SIZE 缓冲区大小
	 * @param num 行数
	 * @throws Exception
	 */
	public void mmapRead(String filePath, int BUFFER_SIZE, int num) throws Exception {
		File file = new File(filePath);
		MappedByteBuffer buff = null;
		long file_size = file.length();
		long rows = file_size / num;
		int total = num + (file_size % num == 0 ? 0 : 1);
		long start_pos = 0l;
		long size = 0l;
		byte[] dst = null;
		for (int i = 1; i <= total; i++) {
			start_pos = (i - 1) * rows;
			size = (i == total ? file_size - start_pos : rows);
			buff = new RandomAccessFile(filePath, "r").getChannel().map(
					FileChannel.MapMode.READ_ONLY, start_pos, size);
			dst = new byte[BUFFER_SIZE];
			// 每次读出3M的内容
			for (int offset = 0; offset < buff.capacity(); offset += BUFFER_SIZE) {
				if (buff.capacity() - offset >= BUFFER_SIZE) {
					// 剩余文件大小大于等于3M
					for (int j = 0; j < BUFFER_SIZE; j++)
						dst[j] = buff.get(offset + j);
				} else {
					// 剩余文件大小小于3M
					for (int j = 0; j < buff.capacity() - offset; j++)
						dst[j] = buff.get(offset + j);
				}
				// int length = (buff.capacity() % BUFFER_SIZE == 0) ?
				// BUFFER_SIZE
				// : buff.capacity() % BUFFER_SIZE;
				// System.out.println(new String(dst, 0, length));
			}
		}

	}

	/**
	 * mmap写文件
	 * @param PATH
	 * @param data
	 * @param COUNT
	 */
	public static void mmapWrite(String PATH, byte[] data, int COUNT) {
		MappedByteBuffer buff = null;
		RandomAccessFile randomAccessFile = null;
		int i = 0;
		try {
			FileChannel fc = null;
			randomAccessFile = new RandomAccessFile(PATH, "rw");
			fc = randomAccessFile.getChannel();
			try {
				buff = fc.map(FileChannel.MapMode.READ_WRITE, 0, data.length
						* COUNT);
				long starttime = System.currentTimeMillis();
				for (; i < COUNT; i++) {
					System.out.println("capacity:" + buff.capacity());
					buff.put(data);
				}
				// buff.force();
				System.out.println("mmap cost "
						+ (System.currentTimeMillis() - starttime));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("exception i:" + i);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
