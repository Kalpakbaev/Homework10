package com.gb.myapplication_lesson10.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gb.myapplication_lesson10.R;
import com.gb.myapplication_lesson10.repository.CardData;
import com.gb.myapplication_lesson10.repository.CardSource;

public class AnimalGuideAdapter extends RecyclerView.Adapter<AnimalGuideAdapter.MyViewHolder> {

      private CardSource cardSource;

    OnItemClickListener onItemClickListener;


    public void setData(CardSource cardSource) {
        this.cardSource = cardSource;
        notifyDataSetChanged();
    }

    AnimalGuideAdapter(CardSource cardSource){
        this.cardSource = cardSource;
    }
    AnimalGuideAdapter(){
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_animal_guide_cardview_item,parent,false));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(cardSource.getCardData(position));
    }
    @Override
    public int getItemCount() {
        return cardSource.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageView imageViewPicture;
        private ToggleButton toggleButton;
        public MyViewHolder(@NonNull View itemView){
          super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
            imageViewPicture = (ImageView) itemView.findViewById(R.id.imageView);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.like);
            /*textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!= null){
                        onItemClickListener.onItemClick(getLayoutPosition());

                    }
                }
            });*/

        }
        public void bindContentWithLayout(CardData content){
            textViewTitle.setText(content.getTitle());
            textViewDescription.setText(content.getDescription());
            imageViewPicture.setImageResource(content.getPicture());
            toggleButton.setChecked(content.isLike());

        }
    }
}
