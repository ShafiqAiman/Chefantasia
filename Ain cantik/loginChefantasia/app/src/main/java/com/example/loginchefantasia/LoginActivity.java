package com.example.loginchefantasia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mTextUsername.getText().toString();
                String pwd = mTextPassword.getText().toString();
                Boolean checkUserLogin = db.checkUserLogin(username,pwd);
                Boolean checkUser=db.checkUser(username);
                if(checkUserLogin == true)
                {   Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                    Intent homepageIntent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(homepageIntent);
                }
                else if (username.equals("")||pwd.equals("")){
                    Toast.makeText(getApplicationContext(),"Field(s) cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if(checkUser==true){
                    Toast.makeText(getApplicationContext(),"Username doesn't exist",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

