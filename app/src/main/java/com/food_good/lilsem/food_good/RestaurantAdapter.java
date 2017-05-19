package com.food_good.lilsem.food_good;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food_good.lilsem.food_good.model.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> mList;
    private OnRecyclerViewItemClickListener mClickListener;


    public interface OnRecyclerViewItemClickListener {
        void onClick(int position);
    }

    public RestaurantAdapter() {

    }

    public RestaurantAdapter(List<Restaurant> list,OnRecyclerViewItemClickListener clickListener) {
        mList = list;
        mClickListener = clickListener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RestaurantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, final int position) {

        Restaurant restaurant = mList.get(position);
        holder.ivRestaurant.setImageResource(R.drawable.fg_logo);
        holder.twRestaurantName.setText(restaurant.title);
        holder.twKitchen.setText(restaurant.kitchen + " кухня");
        holder.twSalary.setText("Мин. сумма покупки: " + restaurant.salaryPrice + " \u20BD");
        holder.twDelivery.setText("Стоимость доставки: " + restaurant.deliveryPrice + " \u20BD");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
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


    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        ImageView ivRestaurant;
        TextView twRestaurantName;
        TextView twKitchen;
        TextView twSalary;
        TextView twDelivery;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.cv_restaraunt);
            ivRestaurant = (ImageView) itemView.findViewById(R.id.iv_restaurant_logo);
            twRestaurantName = (TextView) itemView.findViewById(R.id.tw_restaurant_name);
            twKitchen = (TextView) itemView.findViewById(R.id.tw_kitchen);
            twSalary = (TextView) itemView.findViewById(R.id.tw_salary);
            twDelivery = (TextView) itemView.findViewById(R.id.tw_delivery);

        }
    }
}