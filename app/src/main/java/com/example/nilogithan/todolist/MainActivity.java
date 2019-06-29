package com.example.nilogithan.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private  EditText Email;
   private EditText Password;
    private Button LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        LoginBtn = (Button) findViewById(R.id.loginBtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String getEmail = Email.getText().toString().trim();
        String getPassword = Password.getText().toString();

        if(!TextUtils.isEmpty(getEmail) && !TextUtils.isEmpty(getPassword)){
            if(getEmail.equals("user@gmail.com") && getPassword.equals("password") ){
                Intent intent = new Intent(MainActivity.this, TaskList.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Loged in", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Email or Password is Wrong", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(MainActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
        }


    }
}
