package com.example.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class chatAdapter extends ArrayAdapter<chatBotModel> {

    Context context;
    int resource;
    ArrayList<chatBotModel> objects;

    public class ViewHolder{
        LinearLayout sendLayout,receiveLayout;
        TextView textviewreceivedmsg,textviewsendmsg;
    }


    public chatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<chatBotModel> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(resource,parent,false);

        ViewHolder viewHolder=new ViewHolder();
        viewHolder.receiveLayout=view.findViewById(R.id.receivelayout);
        viewHolder.sendLayout=view.findViewById(R.id.sendlayout);
        viewHolder.textviewreceivedmsg=view.findViewById(R.id.textviewreceivedmsg);
        viewHolder.textviewsendmsg=view.findViewById(R.id.textviewsendmsg);

        chatBotModel item=objects.get(position);

        if(item.getMessage_code().equals("1")){
            viewHolder.sendLayout.setVisibility(View.VISIBLE);
            viewHolder.receiveLayout.setVisibility(View.GONE);
            viewHolder.textviewsendmsg.setText(item.getMessage());
        }
        else if(item.getMessage_code().equals("2")){
            viewHolder.sendLayout.setVisibility(View.GONE);
            viewHolder.receiveLayout.setVisibility(View.VISIBLE);
            viewHolder.textviewreceivedmsg.setText(item.getMessage());
        }
        else{
            viewHolder.sendLayout.setVisibility(View.GONE);
            viewHolder.receiveLayout.setVisibility(View.GONE);
        }

        return view;
    }
}
