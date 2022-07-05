package com.example.mainshitimu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<HuanKuan> mData;

    public MyAdapter(Context context, ArrayList<HuanKuan> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_huankuan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_yuefen.setText("第"+(position + 1));
        holder.textView_benjin.setText(String.format("%.5f",mData.get(position).getBenjin()));
        holder.textView_lixi.setText(String.format("%.5f",mData.get(position).getLixi()));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_yuefen;
        private TextView textView_benjin;
        private TextView textView_lixi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_yuefen = itemView.findViewById(R.id.huankuan_yuefen);
            textView_benjin = itemView.findViewById(R.id.huankuan_benjin);
            textView_lixi = itemView.findViewById(R.id.huankuan_lixi);
        }
    }
}
