/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceertsptransport;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AppController {
    private final static Logger logger=Logger.getLogger(AppController.class.getName());

//    private static MonitorServer server;

    public void start() {
//        server = new MonitorServer();
          logger.log(Level.INFO, "启动程序成功\n");
//        if (server.startService()) {
//            logger.log(Level.INFO, "启动程序成功\n");
//        } else {
//            logger.log(Level.INFO, "启动程序失败\n");
//        }
    }

    public void stop() {
//        if (server != null) {
//            server.shutDownServer();
            logger.log(Level.INFO, "停止服务\n");
//        }
    }
}
