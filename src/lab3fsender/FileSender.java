/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3fsender;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;

/**
 *
 * @author User
 */
public class FileSender {

    public static final int BUFFER_SIZE = 10240;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        System.out.println("File sender started...");
        new FileSender().run(HOST);
        System.out.println("File sender finished.");
    }

    private void run(String host) {
        try (Socket sc = new Socket(host, FileReceiver.SERVER_PORT);
                OutputStream out = sc.getOutputStream();
                InputStream in = sc.getInputStream();) {
            File file = new File("gimnChM.mp3");//
            byte[] bufFile = file.getName().getBytes();
            byte[] buf = new byte[BUFFER_SIZE];
            out.write(bufFile);

            int n = 0;
            try (FileInputStream fis = new FileInputStream(file)) {

                System.out.println("SENDING FILE...");

                while (n != -1) {
                    out.write(buf, 0, n);
                    n = fis.read(buf);
                }
            }

            System.out.println("COMPLETED");

        } catch (IOException e) {
            System.err.println("Error #1: " + e.getMessage());
        }
    }

}
