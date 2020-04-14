package com.imooc.o2o.util;

//import java.util.function.ToLongFunction;

public class PathUtil {

	// 获取文件分隔符
	private static String separator = System.getProperty("file.separator");

	/**
	 * 执行环境的不同，提供不同的根路径
	 * 
	 * @return
	 */
	public static String getImgBasePath() {

		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
//			basePath = "D:/src/image/";
			basePath = "D:/Users/baidu/work/image";
		} else {
			basePath = "/home/xiaolei/image";

		}

		basePath = basePath.replace("/",separator);
		return basePath;
		
	}

	
	/**
	 * 获取店铺存储路径
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId) {

		String imagePath = "/upload/images/item/shop/" + shopId + "/";
		return imagePath;
//		= imagePath.replace("/", separator)

	}
}
