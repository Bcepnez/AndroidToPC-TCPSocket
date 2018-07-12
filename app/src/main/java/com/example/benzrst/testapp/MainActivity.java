package com.example.benzrst.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    ServerSocket ss;
    Button send ;
    TextView message,reply;
    EditText editText;
    String text="";
    boolean status=true;

    Thread myThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView)findViewById(R.id.OreNoMessage);
        reply = (TextView)findViewById(R.id.Chat);
        editText = (EditText)findViewById(R.id.editText);
        send = (Button)findViewById(R.id.sender);
        myThread = new Thread(new MyServerThread());
        myThread.start();
    }

//    Android Sender
    public void sender(View v){
//        myThread.interrupt();
        MessageSender messageSender = new MessageSender();
        text=editText.getText().toString();
        messageSender.execute(text);
        message.setText(text);
        editText.setText("");
//        myThread.run();
    }
//  Android Reciever
    class MyServerThread implements Runnable {
        Socket s;
        InputStreamReader isr;
        BufferedReader br;
        String message;
        Handler h = new Handler();
        @Override
        public void run() {
            try {
                ss = new ServerSocket(8000);
                while (true){
                    s = ss.accept();
                    isr = new InputStreamReader(s.getInputStream());
                    br = new BufferedReader(isr);
                    message = br.readLine();
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            reply.setText(message);
                        }
                    });
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
