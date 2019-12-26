package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    EditText edtUsername,edtPassword,edtDPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtDPassword = (EditText) findViewById(R.id.edtDPassword);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        //init Firebase

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String pwd = edtPassword.getText().toString();
                String cnf_pwd = edtDPassword.getText().toString();
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);


                if (username.equals("")||pwd.equals("")||cnf_pwd.equals("")){
                    Toast.makeText(getApplicationContext(),"Field(s) cannot be empty",Toast.LENGTH_SHORT).show();
                }else{

                    if (pwd.equals(cnf_pwd)){
                        table_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                //Check if already user phone

                                if(dataSnapshot.child(edtUsername.getText().toString()).exists()){
                                    mDialog.dismiss();
                                    Toast.makeText(SignUp.this,"Username is already registered",Toast.LENGTH_SHORT).show();
                                }else{
                                    mDialog.dismiss();
                                    User user = new User(edtUsername.getText().toString(),edtPassword.getText().toString());
                                    table_user.child(edtUsername.getText().toString()).setValue(user);
                                    Toast.makeText(SignUp.this,"Sign up successfully",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
                    }



                }

        }
        });

    }
}
