package com.gb.myapplication_lesson10.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gb.myapplication_lesson10.R;
import com.gb.myapplication_lesson10.repository.CardData;
import com.gb.myapplication_lesson10.repository.CardsSource;

public class AnimalGuideAdapter extends RecyclerView.Adapter<AnimalGuideAdapter.MyViewHolder> {

      private CardsSource cardsSource;

    OnItemClickListener onItemClickListener;

    Fragment fragment;


    public void setData(CardsSource cardsSource) {
        this.cardsSource = cardsSource;
        notifyDataSetChanged();
    }

    AnimalGuideAdapter(CardsSource cardsSource){
        this.cardsSource = cardsSource;
    }
    AnimalGuideAdapter(){
    }
    AnimalGuideAdapter(Fragment fragment){
        this.fragment = fragment;
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
        holder.bindContentWithLayout(cardsSource.getCardData(position));
    }
    @Override
    public int getItemCount() {
        return cardsSource.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final ImageView imageViewPicture;
        private final ToggleButton toggleButton;
        public MyViewHolder(@NonNull View itemView){
          super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
            imageViewPicture = (ImageView) itemView.findViewById(R.id.imageView);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.like);
            fragment.registerForContextMenu(itemView);
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
