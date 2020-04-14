package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import com.imooc.o2o.dto.ImageHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {

    //按照当前线程获取路径
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    //获取时间
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    //获取随机数
    private static final Random RANDOM = new Random();

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMultipartFile装换成File类
     *
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    //处理缩略图       CommonsMultipartFile使用Spring自带的文件处理对象，图片储存的路径
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        //生成随机文件名
        String realFileName = getRandomFileName();
        //获取图片的扩展名   jsp  png
        String extension = getFileExtension(thumbnail.getImageName());
        //随机名+扩展名就是文件的新名字存储在targetAddr下
        makeDirpath(targetAddr);
        //获取相对路径
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current reativeAddr is:" + realFileName);
        //新的文件路径=根路径+相对路径
        File desc = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(1000, 1900)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "hongse.jpg")),
                            0.25f).outputQuality(0.8f).toFile(desc);
        } catch (IOException e) {
            // TODO: handle exception
            logger.error(e.toString());
            e.printStackTrace();

        }

        return relativeAddr;


    }

    /**
     * 创建目标路径所涉及到的目录，即/home/work/xiaolei/xxx.jpg,
     * 那么 home work xiaolei 这三个文件夹都得自动创建
     *
     * @param targetAddr
     */
    private static void makeDirpath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取文件输入流的扩展名
     *
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //生成随机文件名,当前年月日小时分钟秒钟+五位随机数
    public static String getRandomFileName() {
        //获取随机五位数
        int rannum = RANDOM.nextInt(89999) + 10000;
        String nowTimeStr = SIMPLE_DATE_FORMAT.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 删除旧图片
     * @param storePath
     */
    public static void deleteOrFilePath(String storePath) {
        File file = new File(PathUtil.getImgBasePath() + storePath);
        //判断文件是否存在
        if (file.exists()) {
//        	判断是文件路径还是目录路径
            if (file.isDirectory()) {
                File fileOr[] = file.listFiles();
                for (int i = 0; i < fileOr.length; i++) {
                	//删除目录下的文件
                    fileOr[i].delete();
                }
            }
            //删除目录
            file.delete();
        }
    }

    //处理详情图片
    public static String generateNormalImg(ImageHolder productImgHolder, String shopImagePath) {
        //生成随机文件名
        String realFileName = getRandomFileName();
        //获取图片的扩展名   jsp  png
        String extension = getFileExtension(productImgHolder.getImageName());
        //随机名+扩展名就是文件的新名字存储在targetAddr下
        makeDirpath(shopImagePath);
        //获取相对路径
        String relativeAddr = shopImagePath + realFileName + extension;
        logger.debug("current reativeAddr is:" + realFileName);
        //新的文件路径=根路径+相对路径
        File desc = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(productImgHolder.getImage()).size(2000, 3900)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "hongse.jpg")),
                            0.25f).outputQuality(0.9f).toFile(desc);
        } catch (IOException e) {
            // TODO: handle exception
            logger.error(e.toString());
            e.printStackTrace();

        }

        return relativeAddr;
    }
	
	
	
	
/*
	public static void main(String[] args) throws IOException {
		
		Thumbnails.of(new File("D:\\src\\meinv.jpg"))
		.size(1000,1900).watermark(Positions.BOTTOM_RIGHT,
				ImageIO.read(new File((URLDecoder.decode(basePath, "UTF-8"))+"hongse.jpg")),
				0.25f).outputQuality(0.8f).toFile("D:\\src\\meinvnew.jpg");
	}*/

}
