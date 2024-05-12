package com.example.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {
    private ArrayList<About> data;
    private Context context;

    AboutAdapter(Context context, ArrayList<About> itemsData) {
        this.data = itemsData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.about_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(AboutAdapter.ViewHolder holder, int position) {
        About currentItem = data.get(position);
        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private TextView info1;
        private TextView info2;
        private TextView info3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameOfPack);
            price = itemView.findViewById(R.id.priceOfPack);
            info1 = itemView.findViewById(R.id.info1OfPack);
            info2 = itemView.findViewById(R.id.info2OfPack);
            info3 = itemView.findViewById(R.id.info3OfPack);
        }

        public void bindTo(About currentItem) {
            name.setText(currentItem.getName());
            price.setText(currentItem.getPrice());
            info1.setText(currentItem.getInfo1());
            info2.setText(currentItem.getInfo2());
            info3.setText(currentItem.getInfo3());

            itemView.findViewById(R.id.packAddToCart).setOnClickListener(v -> {
                ((FirstPageActivity) context).addToCart(currentItem);
            });
            itemView.findViewById(R.id.deleteCart).setOnClickListener(v -> {
                ((FirstPageActivity) context).delete(currentItem);
            });
        }
    }
}
