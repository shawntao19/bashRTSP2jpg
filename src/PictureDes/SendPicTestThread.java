/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PictureDes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xu Wentao
 * @date 2017-12-4 15:23:09
 */
public class SendPicTestThread extends Thread {

    public static final Logger logger = Logger.getLogger(SendPicTestThread.class.getName());

    public void run() {
        logger.info("进入等待，开始取出图片");
        try {
            this.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SendPicTestThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.info("等待3秒后开始取出图片");
        while (true) {
            long begintime = System.currentTimeMillis();
            SendPic gp = new SendPic();
            byte[] pic = gp.getPic();
            if (pic != null) {
                logger.info("***///:size:" + pic.length / 1024 + "kb,输出的新文件大小////////////");
                long endtime = System.currentTimeMillis();
                long costTime = (endtime - begintime);
                logger.log(Level.INFO, "循环获取的costTime:" + costTime);
            } else {
                try {
                    this.sleep(40);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SendPicTestThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
