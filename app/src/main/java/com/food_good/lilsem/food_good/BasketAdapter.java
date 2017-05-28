package com.food_good.lilsem.food_good;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food_good.lilsem.food_good.model.Basket;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

private List<Basket> mList;
private OnRecyclerViewItemClickListener mClickListener;


public interface OnRecyclerViewItemClickListener {
    void onClick(int position);
}

    public BasketAdapter(List<Basket> list, OnRecyclerViewItemClickListener clickListener) {
        mList = list;
        mClickListener = clickListener;
    }


    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BasketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item,parent,false));
    }


    @Override
    public void onBindViewHolder(BasketAdapter.BasketViewHolder holder, final int position) {

        Basket basket = mList.get(position);

        holder.ivDish.setImageResource(R.drawable.fg_logo);
        holder.tvTitle.setText(basket.title);
        holder.tvComposition.setText("Состав: " + basket.composition);
        holder.tvWeight.setText("Вес: " + basket.weight + " г.");
        holder.tvPrice.setText("Цена: " + basket.price + " \u20BD");


        holder.btnDelBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



class BasketViewHolder extends RecyclerView.ViewHolder{

    ImageView ivDish;
    TextView tvTitle;
    TextView tvComposition;
    TextView tvWeight;
    TextView tvPrice;
    ImageView btnDelBasket;

    public BasketViewHolder(View itemView){
        super(itemView);

        ivDish = (ImageView) itemView.findViewById(R.id.iv_basket_dish_logo);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_basket_title);
        tvComposition = (TextView) itemView.findViewById(R.id.tv_basket_composition);
        tvWeight = (TextView) itemView.findViewById(R.id.tv_basket_weight);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_basket_price);
        btnDelBasket = (ImageView) itemView.findViewById(R.id.iv_remove);
    }
}



}
