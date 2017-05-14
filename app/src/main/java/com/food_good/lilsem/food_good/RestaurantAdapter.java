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

    public RestaurantAdapter(List<Restaurant> list) {
        mList = list;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {

        Restaurant restaurant = mList.get(position);

        holder.ivRestaurant.setImageResource(R.drawable.fg_logo);
        holder.twRestaurantName.setText(restaurant.getName());
        holder.twKitchen.setText(restaurant.getKitchen());
        holder.twSalary.setText(restaurant.getSalary());
        holder.twDelivery.setText(restaurant.getDelivery());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;
        ImageView ivRestaurant;
        TextView twRestaurantName;
        TextView twKitchen;
        TextView twSalary;
        TextView twDelivery;

    public RestaurantViewHolder(View itemView){
        super(itemView);

        mCardView = (CardView)  itemView.findViewById(R.id.card_view);
        ivRestaurant = (ImageView) itemView.findViewById(R.id.iv_restaurant_logo);
        twRestaurantName = (TextView) itemView.findViewById(R.id.tw_restaurant_name);
        twKitchen = (TextView) itemView.findViewById(R.id.tw_kitchen);
        twSalary = (TextView) itemView.findViewById(R.id.tw_salary);
        twDelivery = (TextView) itemView.findViewById(R.id.tw_delivery);
    }
}


}
