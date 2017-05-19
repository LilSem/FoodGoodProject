package com.food_good.lilsem.food_good;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food_good.lilsem.food_good.model.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{


    private List<Event> mList;

    public EventAdapter(List<Event> list) {
        mList = list;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item,parent,false));
    }


    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder holder, int position) {

        Event event = mList.get(position);

        holder.ivEvent.setImageResource(R.drawable.fg_logo);
        holder.tvTitle.setText(event.title);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;
        ImageView ivEvent;
        TextView tvTitle;

        public EventViewHolder(View itemView){
            super(itemView);

            mCardView = (CardView)  itemView.findViewById(R.id.cv_event);
            ivEvent = (ImageView) itemView.findViewById(R.id.iv_event_logo);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_event_title);

        }
    }


}
