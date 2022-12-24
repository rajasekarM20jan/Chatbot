package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText msginput;
    ImageButton sendButton;
    ListView listView;
    ArrayList sentmessages;
    public  static ArrayList receiver;
    public static String message;
    public static int sendOrReceive;
    MessageAdapter adapter;
    public static String myString;
    public static ArrayList<AllMessages> allmessages;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allmessages=new ArrayList<>();
        sentmessages=new ArrayList<>();
        receiver=new ArrayList<>();
        try{
            InputStream iStream = getAssets().open("aadhi.json");
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();
            myString = new String(buffer, "UTF-8");
            System.out.println(myString);
        }catch (Exception e){
            e.printStackTrace();
        }

        msginput=findViewById(R.id.edittext);
        sendButton=findViewById(R.id.sendbutton);
        listView=findViewById(R.id.mylistview);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOrReceive=1;
                message=msginput.getText().toString();
                sentmessages.add(message);
                allmessages.add(new AllMessages(message,0));
                System.out.println(sentmessages.size());
                msginput.setText("");

                adapter=new MessageAdapter(MainActivity.this,2,allmessages);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });



    }

    public static void pushreceivedmessage(String received_message, int i) {
        System.out.println(received_message+ i);
        allmessages.add(new AllMessages(received_message,i));
    }

}