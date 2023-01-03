package com.example.chatbot;

import static com.example.chatbot.Database.chatbot;
import static com.example.chatbot.Database.data;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Database chatbotDb;
    ListView myListview;
    EditText edittext;
    ImageButton sendbutton;
    ImageView msgView;
    JSONObject jobj;
    public static String userName;
    public static Context context;
    ArrayList<chatBotModel> myMessagesArraylist;
    chatAdapter chatAdapter;
    SensorManager sensorManager;
    Boolean notFirstTime;
    MediaPlayer myPlayer;
    Sensor linearAccelerometer;
    double previousAcceleration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatbotDb=new Database(MainActivity.this);
        sendbutton=findViewById(R.id.sendbutton);
        edittext=findViewById(R.id.edittext);
        myListview=findViewById(R.id.mylistview);
        context=this;
        msgView=findViewById(R.id.msgView);
        myPlayer=MediaPlayer.create(context,R.raw.audio);
        chatbotDb.deleteTable();
        notFirstTime=false;

        Glide.with(this).asGif().load(R.raw.msg).into(msgView);

        Database mydb = new Database(MainActivity.this);

                if(mydb.getUserdetails().getCount() != 0) {
                    Cursor curseattachtoken = mydb.getUserdetails();
                    curseattachtoken.moveToFirst();
                    userName = curseattachtoken.getString(0);
                }

        sensorManager=(SensorManager) getSystemService(Service.SENSOR_SERVICE);
        linearAccelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        try {
            InputStream iStream = getAssets().open("aadhi.json");
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();
            String myString = new String(buffer, "UTF-8");
            jobj = new JSONObject(myString);
        }catch (Exception e){
            e.printStackTrace();
        }

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

    public void getMessages(){
        myMessagesArraylist=new ArrayList<>();
        SQLiteDatabase db = chatbotDb.getReadableDatabase();
        Cursor c=db.query(chatbot,data,null,null,null,null,null);
        while (c.moveToNext()){
            myMessagesArraylist.add(new chatBotModel(c.getString(1),c.getString(0)));
        }
        chatAdapter=new chatAdapter(MainActivity.context,R.layout.customlistviewreceive,myMessagesArraylist);
        myListview.setAdapter(chatAdapter);
        myListview.setSelection(chatAdapter.getCount() - 1);
        // Smooth scroll to the bottom
        myListview.smoothScrollToPosition(chatAdapter.getCount() - 1);
    }

    private void sendMessage() {
        String message=edittext.getText().toString();
        int message_code=1;
        chatbotDb.insertMessage(message_code,message);
        edittext.setText("");
        edittext.clearFocus();
        getMessages();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                responseMessage(message);
            }
        },3000);

    }



    private void responseMessage(String message) {
        try {
            if (message.contains("Hi") || message.contains("hi") || message.contains("Hey") || message.contains("Hello") || message.contains("hello") || message.contains("hey")||message.contains("oi")||message.contains("Oii")||message.contains("Oi")||message.contains("oii")||message.contains("oie")||message.contains("Oie")) {
                String response = jobj.getString("Hi");
                chatbotDb.insertMessage(2,response+userName+"\t"+new String(Character.toChars(0x2764)));
            } else if (message.contains("enna panra")||message.contains("ena panra")||message.contains("enna pandra")||(message.contains("Enna panra"))||message.contains("Ena panra")||message.contains("Ena pandra")) {
                String response = jobj.getString("enna_panra");
                chatbotDb.insertMessage(2,response);
            } else if(message.contains("saptiya")||message.contains("saaptiya")||message.contains("Saaptiya")||message.contains("Saptiya")){
                String response = jobj.getString("saptiya");
                chatbotDb.insertMessage(2,response+userName);
            } else if(message.contains("naanum")||message.contains("nanum")||message.contains("Nanum")||message.contains("Naanum")){
                String response = jobj.getString("naanum_summa_dhan_iruken");
                chatbotDb.insertMessage(2,response);
            } else if(message.contains("bye")||message.contains("Bye")){
                String response = jobj.getString("ok_bye");
                chatbotDb.insertMessage(2,response+userName);
            }else if(message.contains("I luv u")||message.contains("love u")|| message.contains("love you")||message.contains("i luv u")){
                String response = jobj.getString("ilu");
                chatbotDb.insertMessage(2,response+userName+"\t"+new String(Character.toChars(0x1F60D)));
            }else if(message.contains("aprm")||message.contains("apro")||message.contains("then")){
                String response = jobj.getString("apro");
                chatbotDb.insertMessage(2,response);
            } else if(message.contains("epdi iruka")||message.contains("Epdi irukinga")||message.contains("Epdi iruka")){
                String response = jobj.getString("epdi_iruka");
                chatbotDb.insertMessage(2,response);
            }else if(message.contains("iruken")||message.contains("I am good")||message.contains("nalla iruken")||message.contains("Nalla iruken")){
                String response = jobj.getString("nalla_iruken");
                chatbotDb.insertMessage(2,response);
            } else if(message.contains("sapten")||message.contains("Sapten")||message.contains("Saapten")||message.contains("saapten")){
                String response = jobj.getString("sapten");
                chatbotDb.insertMessage(2,response);
            } else if(message.contains("idli")||message.contains("Idli")||message.contains("dosa")||message.contains("Dosa")||message.contains("pongal")||message.contains("Pongal")||message.contains("poori")||message.contains("Poori")||message.contains("rice")){
                String response = jobj.getString("sapten");
                chatbotDb.insertMessage(2,response);
            } else{
                String response = jobj.getString("mis_spelled");
                chatbotDb.insertMessage(2,response);
            }
            getMessages();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Float valX, valY, valZ;
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            valX = sensorEvent.values[0];
            valY = sensorEvent.values[1];
            valZ = sensorEvent.values[2];
            double acceleration =Math.ceil (Math.sqrt((valX * valX) + (valY * valY) + (valZ * valZ)));
            System.out.println("acceleration :\t"+acceleration);
            if(notFirstTime){
                if(acceleration>=50&&previousAcceleration>=40){
                    sendShakeData();
                    onPause();
                }
            }
            previousAcceleration=acceleration;
            notFirstTime=true;
        }
    }

    private void sendShakeData() {
        try {
            String response = jobj.getString("shake_detection");
            chatbotDb.insertMessage(2,userName+ response);
            getMessages();
            if(myPlayer.isPlaying()){
                myPlayer.stop();
                myPlayer.start();
            }else{
                myPlayer.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume() {
        sensorManager.registerListener(this,linearAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onResume();
            }
        },5000);
    }

}