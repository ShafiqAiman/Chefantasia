package com.example.loginchefantasia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_c_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mTextUsername.getText().toString();
                String pwd = mTextPassword.getText().toString();
                String cnf_pwd = mTextCnfPassword.getText().toString();

                if (username.equals("")||pwd.equals("")||cnf_pwd.equals("")){
                    Toast.makeText(getApplicationContext(),"Field(s) cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
                    if (pwd.equals(cnf_pwd)){
                        Boolean checkUser=db.checkUser(username);
                        if(checkUser==true){
                            Boolean addUser = db.addUser(username,pwd);
                            if(addUser==true){
                                Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_SHORT).show();
                                Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(loginIntent);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Username already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}