package com.java.fay.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 类对象工具类，提供反射调用的相关方法
 * 
 * @author xiaofei9
 * @date 2018年5月10日 下午2:24:38
 */
public class ClassUtils {

	/**
	 * 获取类的名称
	 * 
	 * @param obj
	 * @return
	 */
	public static String getClassName(Object obj) {
		// 获取类信息，首先要获取类类型
		Class<? extends Object> c = obj.getClass();
		return c.getName();
	}

	/**
	 * 打印类对象的方法信息，包括返回值类型、方法名和参数类型 getMethods()返回的是所有的public的函数，包括父类继承而来的
	 * getDeclaredMethods()返回的是所有该类自己声明的方法，不区分访问权限
	 * 
	 * @param obj
	 */
	public static void printClassMethods(Object obj) {
		// 1.获取类信息，首先要获取类类型
		Class<? extends Object> c = obj.getClass();
		System.out.println(c.getName());
		// 2.获取所有该类自己声明的方法
		Method[] ms = c.getDeclaredMethods();
		for (Method m : ms) {
			// 3.获取方法返回值类型
			Class<?> type = m.getReturnType();
			System.out.print(type.getName() + " " + m.getName() + "(");
			// 4.获取参数值类型
			Class<?>[] types = m.getParameterTypes();
			for (Class<?> t : types) {
				System.out.print(t.getName() + ",");
			}
			System.out.println(")");
		}
	}

	/**
	 * 打印类对象的成员变量，包括成员变量的类名和变量名 getSimpleName()方法获取的是不包含包名的类名
	 * 
	 * @param obj
	 */
	public static void printClassFields(Object obj) {
		// 获取类信息，首先要获取类类型
		Class<? extends Object> c = obj.getClass();
		System.out.println(c.getName());
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f.getDeclaringClass().getSimpleName() + " "
					+ f.getName());
		}
	}

	/**
	 * 通过反射访问类的静态方法
	 * @param className 包含包名的类全名
	 * @param methodName 方法名
	 * @param parameters 参数
	 */
	public static void invokeMethod(String className, String methodName,
			Object parameters) {
		try {
			// 根据类名获取类对象
			Class<?> c = Class.forName(className);
			try {
				// 构造类的实例对象
				c.newInstance();
				Method m;
				Object result;
				if (parameters != null) {
					// 获取类对象的方法，需要传入方法名和参数类对象
					m = c.getMethod(methodName, parameters.getClass());
					// invoke方法，需要传入类对象和参数
					result = m.invoke(c, parameters);
				} else {
					m = c.getMethod(methodName);
					result = m.invoke(c);
				}
				System.out.println(result.getClass().getName());
				System.out.println(result.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
