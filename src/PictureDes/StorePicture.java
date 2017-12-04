/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PictureDes;

import Util.CountNum;
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
public class StorePicture extends Thread {

    public static final Logger logger = Logger.getLogger(StorePicture.class.getName());

    @Override
    public void run() {
//        logger.log(Level.INFO, "StorePicture:" + System.getProperty("user.dir"));

        QueueDemo demo = new QueueDemo(50);
        CountNum.queueDemoContainer.put("dataPic", demo);
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
//                logger.log(Level.INFO, "2 已查找到图片:" + pathAName);
                byte[] fileData = null;
                try {
                    fileData = FileDetail.toByteArray(pathAName);
                    //存入到 环形队列中
                    demo.EnQueue(fileData);
                    deleteFile(pathAName);
//                    logger.log(Level.INFO, "3 已取到的图片编号为:" + countNameStart + ",图片的大小为:" + fileData.length / 1024 + "kb" + ",此时的队列大小为:" + demo.queueLength());
                } catch (IOException ex) {
                    Logger.getLogger(StorePicture.class.getName()).log(Level.SEVERE, null, ex);
                }
                countNameStart++;
            } else {
//                logger.log(Level.INFO, "没有查找到图片,进行等待:" + pathAName);
                try {
                    this.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StorePicture.class.getName()).log(Level.SEVERE, null, ex);
                }
//                logger.log(Level.INFO, "没有查找到图片,等待结束");
            }
            long endtime = System.currentTimeMillis();
            long costTime = (endtime - begintime);
//            logger.log(Level.INFO, "成功获取一次图片所消耗的时间costTime:" + costTime);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
//                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
//            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

}
