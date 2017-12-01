/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceertsptransport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class IPCController {

    public static final Logger logger = Logger.getLogger(IPCController.class.getName());
    private AppController app;
    int order = 0;

    public void launch() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(7000);
            startServer();
            while (true) {
                socket = serverSocket.accept();
                if (socket.getInetAddress().getHostAddress().equals("127.0.0.1")) {
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    order = dis.read();

                    if (order == Lanucher.IPC_STOP) {
                        stopServer();
                        break;
                    } else if (order == Lanucher.IPC_STATE) {
                    } else if (order == Lanucher.IPC_CLEAR) {
                    }
                }
                socket.close();
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    private void startServer() {
        app = new AppController();
        app.start();
        Lanucher.writeState(Lanucher.STATE_RUNNING);
    }

    private void stopServer() {
        app = new AppController();
        app.stop();
        Lanucher.writeState(Lanucher.STATE_STOP);
    }
}
