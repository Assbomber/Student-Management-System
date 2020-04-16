package com.example.studentmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<item> aray;
    public MyAdapter(ArrayList<item> arrayList){
        aray=arrayList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView reg,name,sec,phone,fee;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reg=itemView.findViewById(R.id.sampleReg);
            name=itemView.findViewById(R.id.sampleName);
            sec=itemView.findViewById(R.id.sampleSection);
            phone=itemView.findViewById(R.id.samplePhone);
            fee=itemView.findViewById(R.id.feeinlayout);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        MyViewHolder mvh=new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        item item=aray.get(position);
        holder.reg.setText(item.getId());
        holder.name.setText(item.getName());
        holder.sec.setText(item.getSec());
        holder.phone.setText(item.getPhone());
        holder.fee.setText(item.getFee());

    }

    @Override
    public int getItemCount() {
        return aray.size();
    }


}
