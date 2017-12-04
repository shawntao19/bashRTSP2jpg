/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PictureDes;

import static ceertsptransport.Lanucher.logger;
import java.util.logging.Level;

/**
 *
 * @author Xu Wentao
 * @date 2017-12-4 9:07:39
 */
public class GetPicture implements Runnable {

    @Override
    public void run() {
        System.out.println("Link is **********:"+System.getProperty("user.dir"));
        logger.log(Level.INFO, "GetPicture:" + System.getProperty("user.dir"));
    }

}
