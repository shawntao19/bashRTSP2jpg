/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PictureDes;

import Util.FileDetail;
import static ceertsptransport.Lanucher.logger;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xu Wentao
 * @date 2017-12-4 9:07:39
 */
public class GetPicture extends Thread {

    public static final Logger logger = Logger.getLogger(GetPicture.class.getName());

    @Override
    public void run() {
//        logger.log(Level.INFO, "GetPicture:" + System.getProperty("user.dir"));

        File f = new File(System.getProperty("user.dir"));
        String picPath = f.getParent() + "/pic/";
//        logger.log(Level.INFO, "picPath:" + picPath);
        int countNameStart = FileDetail.fileNameCount;
        while (true) {
            long begintime = System.currentTimeMillis();
            String fileName = FileDetail.fileNameStart + countNameStart + FileDetail.fileNameTail;
//            logger.log(Level.INFO, "1 fileName:" + picPath + fileName);
            String pathAName = picPath + fileName;
            File fPic = new File(pathAName);
            if (fPic.exists()) {
                logger.log(Level.INFO, "2 已查找到图片:" + pathAName);
                byte[] fileData = null;
                try {
                    fileData = FileDetail.toByteArray(pathAName);
                    logger.log(Level.INFO, "3 已取到的图片编号为:" + countNameStart + ",图片的大小为:" + fileData.length / 1024 + "kb");
                } catch (IOException ex) {
                    Logger.getLogger(GetPicture.class.getName()).log(Level.SEVERE, null, ex);
                }
                countNameStart++;
            } else {
//                logger.log(Level.INFO, "没有查找到图片,进行等待:" + pathAName);
                try {
                    this.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GetPicture.class.getName()).log(Level.SEVERE, null, ex);
                }
//                logger.log(Level.INFO, "没有查找到图片,等待结束");
            }
            long endtime = System.currentTimeMillis();
            long costTime = (endtime - begintime);
            logger.log(Level.INFO, "成功获取一次图片所消耗的时间costTime:" + costTime);
        }
    }

}
