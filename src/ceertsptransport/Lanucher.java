/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceertsptransport;

import PictureDes.GetPicture;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Lanucher {

    public static final Logger logger = Logger.getLogger(Lanucher.class.getName());

    public static final int STATE_STOP = 0;
    public static final int STATE_RUNNING = 1;

    public static final String ARG_START = "start";
    public static final String ARG_STOP = "stop";
    public static final String ARG_STATE = "state";
    public static final String ARG_CLEAR = "clear";

    public static final int IPC_STOP = 1;
    public static final int IPC_STATE = 2;
    public static final int IPC_CLEAR = 3;

    public static final String logConfigPath = "/cee/picturecollect/main/logging.properties";
    public static final String launchFileName = "launchFile.lf";

    public static void main(String[] args) throws Exception {

        if (args != null && args.length > 0) {
            int appState = getAppState();
            switch (args[0]) {
                case ARG_STATE:
                    if (appState == STATE_RUNNING) {
                        if (args.length > 1) {

                        } else {
                            trigger(IPC_STATE);
                        }
                    }
                    break;
                case ARG_CLEAR:
                    if (appState == STATE_RUNNING) {
                        if (args.length > 1) {

                        } else {
                            trigger(IPC_CLEAR);
                        }
                    }
                    break;
                case ARG_START:
                    if (appState == STATE_STOP) {
//                        IPCController ipc = new IPCController();
//                        ipc.launch();
                        CeeRTSPTransport crt = new CeeRTSPTransport();
                        crt.transfer("", "");

                        GetPicture gp = new GetPicture();
                        gp.run();
                        logger.log(Level.INFO, "The app is started!\n");
                        appState = STATE_RUNNING;
                    } else {
                        logger.info("The app is running!");
                    }
                    break;
                case ARG_STOP:
                    if (appState == STATE_RUNNING) {
//                        trigger(IPC_STOP);

                        logger.info("The app is shutted!");
                        appState = STATE_STOP;
                    } else {
                        CeeRTSPTransport crt = new CeeRTSPTransport();
                        crt.killPid("", "");
                        logger.info("The app is already shut down!");
                    }
                    break;
                default:
                    break;
            }
        } else {
            logger.log(Level.SEVERE, "输入参数错误，参数1为{start|stop|state}");
        }
    }

    private static void trigger(int order) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 7000)) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.write(order);
            socket.close();
        }
    }

    public static void writeState(int state) {
        File f = new File(launchFileName);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f);
            fw.write(state);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Lanucher.class.getName()).log(Level.SEVERE, null, ex);
            f.delete();
        }
    }

    public static int getAppState() {
        int state;
        File f = new File(launchFileName);
        if (!f.exists()) {
            state = STATE_STOP;
        } else {
            FileReader fr;
            try {
                fr = new FileReader(f);
                state = fr.read();
                fr.close();
            } catch (FileNotFoundException ex) {
                state = STATE_STOP;
                Logger.getLogger(Lanucher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                state = STATE_STOP;
                Logger.getLogger(Lanucher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return state;
    }
}
