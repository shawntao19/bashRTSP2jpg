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
public class GetPicture implements Runnable {

    @Override
    public void run() {
        logger.log(Level.INFO, "GetPicture:" + System.getProperty("user.dir"));

        File f = new File(System.getProperty("user.dir"));
        String picPath = f.getParent() + "/pic/";
        logger.log(Level.INFO, "picPath:" + picPath);
        String fileName = FileDetail.fileNameStart + FileDetail.fileNameCount + FileDetail.fileNameTail;
        logger.log(Level.INFO, "fileName:" + picPath + fileName);
        String pathAName = picPath + fileName;
        File fPic = new File(pathAName);
        if (fPic.exists()) {
            logger.log(Level.INFO, "已查找到图片:" + pathAName);
            byte[] fileData = null;
            try {
                fileData = FileDetail.toByteArray(pathAName);
                logger.log(Level.INFO, "图片的大小为:" + fileData.length / 1024 + "kb");
            } catch (IOException ex) {
                Logger.getLogger(GetPicture.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logger.log(Level.INFO, "没有查找到图片:" + pathAName);
        }

    }

}
