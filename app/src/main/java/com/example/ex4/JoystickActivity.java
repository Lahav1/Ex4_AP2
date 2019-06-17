package com.example.ex4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class JoystickActivity extends Activity implements View.OnClickListener {
    String ip;
    int port;
    Button sayHelloButton;
    Socket sock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        Intent intent = getIntent();
        ip = intent.getStringExtra("IP");
        port = Integer.parseInt(intent.getStringExtra("Port"));
        sayHelloButton = findViewById(R.id.sayHelloButton);
        sayHelloButton.setOnClickListener(JoystickActivity.this);
    }

    public void onClick(View view) {
        new Thread(new ClientThread()).start();
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {
            try {
                InetAddress addr = InetAddress.getByName(ip);
                if (sock == null) {
                    sock = new Socket(addr, port);
                }
                try {
                    DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
                    outputStream.writeUTF("Hello.\r\n");
                    outputStream.flush();
                } catch (Exception e) {
                    Log.e("TCP", "S: Error", e);
                }
            } catch (Exception e) {
                Log.e("TCP", "C: Error", e);
            }
        }
    }
}
