package com.example.benzrst.testapp;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageSender extends AsyncTask<String,Void,Void> {
    ServerSocket ss;
    PrintWriter printWriter;

    @Override
    protected Void doInBackground(String... voids) {
        String message = voids[0];
        try {
//            ss = new ServerSocket(8005);
            Socket s = new Socket("127.0.0.1",8005);
//            s = ss.accept();
            printWriter = new PrintWriter(s.getOutputStream());
            printWriter.write(message);
            printWriter.flush();
            printWriter.close();
            s.close();
//            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
