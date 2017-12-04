/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PictureDes;

import Util.CountNum;
import java.util.logging.Logger;

/**
 * 获取图片主程序SQ
 *
 * @author Xu Wentao
 * @date 2017-12-4 14:26:57
 */
public class SendPic {
    
    public static final Logger logger = Logger.getLogger(SendPic.class.getName());
    
    public byte[] getPic() {
        QueueDemo qd = CountNum.queueDemoContainer.get("dataPic");
        byte[] picDate = (byte[]) qd.DeQueue();
//        byte[] pic = null;
        logger.info("***:size:" + picDate.length / 1024 + "kb,输出的新文件大小是");
        return picDate;
    }
    
}
