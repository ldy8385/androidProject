package com.example.DcDriver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvName;
        TextView tvNum;

        MyViewHolder(View view){
            super(view);
            ivPicture = view.findViewById(R.id.iv_pic);
            tvName = view.findViewById(R.id.tv_name);
            tvNum = view.findViewById(R.id.tv_name2);
        }
    }

    private ArrayList<RowInfo> rowInfoArrayList;
    MyAdapter(ArrayList<RowInfo> rowInfoArrayList){
        this.rowInfoArrayList = rowInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(rowInfoArrayList.get(position).drawableId);
        myViewHolder.tvName.setText(rowInfoArrayList.get(position).name);
        myViewHolder.tvNum.setText(rowInfoArrayList.get(position).num);
    }

    @Override
    public int getItemCount() {
        return rowInfoArrayList.size();
    }
}
