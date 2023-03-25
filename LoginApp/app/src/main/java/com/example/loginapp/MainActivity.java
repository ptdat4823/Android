package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    class User{
        public String username;
        public String password;

        public User(String username, String password)
        {
            this.username = username;
            this.password = password;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User[] listUser = new User[2];
        listUser[0] = new User("User","12345");
        listUser[1] = new User("ABC","12345");

        TextView username = (TextView) findViewById(R.id.input_username);
        TextView password = (TextView) findViewById(R.id.input_password);
        Button loginbtn = (Button) findViewById(R.id.login_btn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textUsername = username.getText().toString();
                String textPassword = password.getText().toString();
                boolean rightLogin = false;
                for(int i = 0; i < 2; i++)
                {
                    if(listUser[i].username.equals(textUsername) && listUser[i].password.equals(textPassword))
                    {
                        String text = "Login success!";
                        Snackbar snack = Snackbar.make(findViewById(R.id.root),text,Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        snack.show();
                        rightLogin = true;
                        return;
                    }
                }
                String text = "Login fail!";
                Snackbar snack = Snackbar.make(findViewById(R.id.root),text,Snackbar.LENGTH_LONG);
                View view = snack.getView();
                FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                params.gravity = Gravity.TOP;
                view.setLayoutParams(params);
                snack.show();
            }
        });

    }
}