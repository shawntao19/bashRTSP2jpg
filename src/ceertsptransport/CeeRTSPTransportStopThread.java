/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceertsptransport;

import static ceertsptransport.CeeRTSPTransportThread.logger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 *
 * @author Xu Wentao
 * @date 2017-12-4 10:41:13
 */
public class CeeRTSPTransportStopThread extends Thread {

    public static final Logger logger = Logger.getLogger(CeeRTSPTransportStopThread.class.getName());

    @Override
    public void run() {
        String dodoer = "";

        String rtsp2jpg = " pkill -f CeeRTSPTransport.jar   ";

        dodoer = rtsp2jpg;

        logger.info("停止视频进程中，此时的KILL的链接为******" + dodoer + "\n type is ");

        try {
            Runtime rt = Runtime.getRuntime();
//            Process proc = rt.exec(dodoer);   //2014年12月5日 13:56:26，暂时
            Process proc = rt.exec(new String[]{"sh", "-c", dodoer});//正式
            //既然 terminal 可以成功执行，启动 shell，然后自定义命令行作为参数传递给 shell 解释器。shell 知道如何将程序员的意图转达给底层。使用 sh -c，将自定义 CMD 行作为其参数，最后使用 java.lang.Runtimeexec(String[] cmdarray)：
            //上一句测试通过，成为正式函数
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = proc.waitFor();

            System.out.println("Process exitValue: " + exitVal);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
