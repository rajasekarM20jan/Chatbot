package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myListview;
    EditText edittext;
    ImageButton sendbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendbutton=findViewById(R.id.sendbutton);
        edittext=findViewById(R.id.edittext);
        myListview=findViewById(R.id.mylistview);

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittext.length()!=0){
                    sendMessage();
                }
                else{
                    Toast.makeText(MainActivity.this, "Enter the Message first", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendMessage() {

    }

}