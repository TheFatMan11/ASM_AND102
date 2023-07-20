package com.thuydev.thuyqnph35609_ass.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.thuyqnph35609_ass.DTO.DTO_task;
import com.thuydev.thuyqnph35609_ass.R;

import java.util.List;

public class Adapter_task extends RecyclerView.Adapter<Adapter_task.ViewHolder>{
Context context;
List<DTO_task> list;

    public Adapter_task(Context context, List<DTO_task> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_task,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.name.setText(list.get(position).getName());
holder.status.setText(status(list.get(position).getStatus()));

holder.menu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
TextView name,status;
ImageButton menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            status = itemView.findViewById(R.id.tv_status);
            menu = itemView.findViewById(R.id.ibtn_menu);
        }
    }
    public String status(int st){
        String status="Chưa hoàn thành";
        if(st==0){
            status = "Mới";
        }else if (st==1){
            status="Hoàn thành";
        }
        return status;
    }
}
