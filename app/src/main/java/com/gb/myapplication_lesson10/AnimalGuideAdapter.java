package com.gb.myapplication_lesson10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalGuideAdapter extends RecyclerView.Adapter<AnimalGuideAdapter.MyViewHolder> {
    OnItemClickListener onItemClickListener;
      private String[] data;


    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_animal_guide_recycle_item,parent,false));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(data[position]);
    }
    @Override
    public int getItemCount() {
        return data.length;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView){
          super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!= null){
                        onItemClickListener.onItemClick(getLayoutPosition());

                    }
                }
            });

        }
        public void bindContentWithLayout(String content){
            textView.setText(content);
        }
    }
}
