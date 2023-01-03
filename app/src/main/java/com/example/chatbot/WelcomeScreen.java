package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class WelcomeScreen extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        imageView =findViewById(R.id.imageView);
        Glide.with(this).asGif().load(R.raw.gif).into(imageView);


        Database mydb = new Database(WelcomeScreen.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mydb.getUserdetails().getCount() != 0) {
                    Cursor curseattachtoken = mydb.getUserdetails();
                    int counttoken = curseattachtoken.getCount();
                    if (counttoken >= 1) {
                        startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(WelcomeScreen.this, NameGetter.class));
                    }
                }else{
                    startActivity(new Intent(WelcomeScreen.this, NameGetter.class));
                }
            }
        },6000);

    }
}