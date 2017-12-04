/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PictureDes;

import Util.FileDetail;
import static ceertsptransport.Lanucher.logger;
import java.io.File;
import java.util.logging.Level;

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
        } else {
            logger.log(Level.INFO, "没有查找到图片:" + pathAName);
        }

    }

}
