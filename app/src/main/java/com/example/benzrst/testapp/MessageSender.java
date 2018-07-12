package com.example.benzrst.testapp;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends AsyncTask<String,Void,Void> {
    PrintWriter printWriter;
    Socket s;

    @Override
    protected Void doInBackground(String... voids) {
        String message = voids[0];
        try {
            Socket s = new Socket("127.0.0.1",8005);
            printWriter = new PrintWriter(s.getOutputStream());
            printWriter.write(message);
            printWriter.flush();
            printWriter.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
