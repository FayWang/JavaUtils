package com.java.fay.utils;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * URL处理工具类
 * 
 * @author xiaofei9
 * @date 2018年5月10日 下午4:45:28
 */
public class UrlUtils {
	/**
	 * 获取IP地址
	 * @param url
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getIP(String url) throws UnknownHostException {
		URI uri = URI.create(url);
		String host = uri.getHost();
		String address = InetAddress.getByName(host).toString();
		int b = address.indexOf("/");
		return address.substring(b + 1);
	}

	/**
	 * 获取Address
	 * 
	 * @param url
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getAddress(String url) throws UnknownHostException {
		URI uri = URI.create(url);
		String host = uri.getHost();
		return InetAddress.getByName(host).toString();
	}

	/**
	 * 获取端口
	 * 
	 * @param url
	 * @return
	 */
	public static int getPort(String url) {
		URI uri = URI.create(url);
		return uri.getPort();
	}

	public static String getHost(String url) {
		URI uri = URI.create(url);
		return uri.getHost();
	}
}
