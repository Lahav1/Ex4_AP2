package com.example.ex4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements View.OnClickListener {
    Button connectButton;
    EditText portTextBox;
    EditText ipTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        portTextBox = findViewById(R.id.portTextBox);
        ipTextBox = findViewById(R.id.ipTextBox);
        connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(LoginActivity.this);
    }

    public void onClick(View view) {
        // after button is clicked, extract the text from both fields.
        String ip = ipTextBox.getText().toString();
        String port = portTextBox.getText().toString();
        // create a joystick activity, pass ip and port and view it.
        Intent js = new Intent(LoginActivity.this, JoystickActivity.class);
        js.putExtra("IP", ip);
        js.putExtra("Port", port);
        startActivity(js);
    }
}
