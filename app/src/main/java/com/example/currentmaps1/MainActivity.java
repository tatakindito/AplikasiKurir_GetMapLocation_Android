package com.example.currentmaps1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.button1);
        username = findViewById(R.id.username);
        password = findViewById(R.id.pswrd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginValidasi();
            }
        });
    }

    private void LoginValidasi(){

        String ambilUsername = username.getText().toString().trim();
        String ambilPassword = password.getText().toString().trim();

        if (!ambilUsername.equals("tatak")){
            Toast.makeText(this,"Username Salah",Toast.LENGTH_SHORT).show();
        }else if (!ambilPassword.equals("indito")){
            Toast.makeText(this,"Password Salah",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }

    }

}