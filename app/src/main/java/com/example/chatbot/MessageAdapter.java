package com.example.chatbot;

import static com.example.chatbot.MainActivity.allmessages;
import static com.example.chatbot.MainActivity.receiver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList objects;
    View view;

    String received_message;




    public MessageAdapter(@NonNull Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        if(MainActivity.sendOrReceive==1){
            LayoutInflater inflater= LayoutInflater.from(context);
            view=inflater.inflate(R.layout.customlistviewreceive,parent,false);
            TextView send_message=view.findViewById(R.id.textviewsendmsg);
            TextView receive_message=view.findViewById(R.id.textviewreceivedmsg);

            try {
                JSONObject jobj = new JSONObject(MainActivity.myString);
                if(MainActivity.message.contains("Hi")||MainActivity.message.contains("Hii")||MainActivity.message.contains("Hey")||MainActivity.message.contains("hi")||MainActivity.message.contains("hey")||MainActivity.message.contains("hii")){
                    received_message=jobj.getString("Hi");

                    receiver.add(received_message);

                    MainActivity.sendOrReceive=2;

                }else if(MainActivity.message.contains("ena panra")||MainActivity.message.contains("inna panra")||MainActivity.message.contains("Ena panra")||MainActivity.message.contains("enna panura")|| MainActivity.message.contains("enna panra")||MainActivity.message.contains("Enna panra")){

                    received_message=jobj.getString("enna_panra");
                    receiver.add(received_message);
                    MainActivity.sendOrReceive=2;

                }else if(MainActivity.message.contains("saptiya")||MainActivity.message.contains("Saptiya")){

                    received_message=jobj.getString("saptiya");
                    receiver.add(received_message);
                    MainActivity.sendOrReceive=2;

                }else if(MainActivity.message.contains("summa dhan")){

                    received_message=jobj.getString("naanum_summa_dhan_iruken");
                    receiver.add(received_message);
                    MainActivity.sendOrReceive=2;

                }else if(MainActivity.message.contains("bye")){

                    received_message=jobj.getString("ok_bye");
                    receiver.add(received_message);
                    MainActivity.sendOrReceive=2;
                }

                MainActivity.pushreceivedmessage(received_message,0);

            }catch (Exception e){
                e.printStackTrace();
            }

            if(allmessages.get(position).code==0){
                send_message.setText(allmessages.get(position).message);
            }
            else {
                receive_message.setText(allmessages.get(position).message);
            }
        }
        return view;
    }
}
