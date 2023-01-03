package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NameGetter extends AppCompatActivity {

    EditText editText;
    Button proceedButton;
    Database userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_getter);

        editText=findViewById(R.id.editText);
        proceedButton=findViewById(R.id.proceedButton);


        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.length()>=3){

                    String name=editText.getText().toString();
                    System.out.println(name);
                    userName=new Database(NameGetter.this);
                    userName.insertName(name);

                    Intent i=new Intent(NameGetter.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(NameGetter.this, "User name must be of minimum 3 characters", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}