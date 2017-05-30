package com.food_good.lilsem.food_good;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.food_good.lilsem.food_good.model.Dish;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>{


    private List<Dish> mList;
    private OnRecyclerViewItemClickListener mClickListener;


    public interface OnRecyclerViewItemClickListener {
        void onClick(int position);
    }

    public DishAdapter(List<Dish> list, OnRecyclerViewItemClickListener clickListener) {
        mList = list;
        mClickListener = clickListener;
    }


    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DishViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item,parent,false));
    }


    @Override
    public void onBindViewHolder(DishAdapter.DishViewHolder holder, final int position) {
        Dish dish = mList.get(position);

        loadImage(dish.photoLink, holder.ivDish);
        holder.tvTitle.setText(dish.title);
        holder.tvComposition.setText("Состав: " + dish.composition);
        holder.tvWeight.setText("Вес: " + dish.weight + " г.");
        holder.tvPrice.setText("Цена: " + dish.price + " \u20BD");

        holder.btnAddBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClick(position);
                }
            }
        });

    }

    public static void loadImage(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DishViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;
        ImageView ivDish;
        TextView tvTitle;
        TextView tvComposition;
        TextView tvWeight;
        TextView tvPrice;
        Button btnAddBasket;

        public DishViewHolder(View itemView){
            super(itemView);

            mCardView = (CardView)  itemView.findViewById(R.id.cv_dish);
            ivDish = (ImageView) itemView.findViewById(R.id.iv_dish_logo);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvComposition = (TextView) itemView.findViewById(R.id.tv_composition);
            tvWeight = (TextView) itemView.findViewById(R.id.tv_weight);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            btnAddBasket = (Button) itemView.findViewById(R.id.btnAddBasket);
        }
    }


}
