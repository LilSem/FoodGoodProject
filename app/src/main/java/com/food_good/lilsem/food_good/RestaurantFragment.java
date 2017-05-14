package com.food_good.lilsem.food_good;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food_good.lilsem.food_good.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFragment extends Fragment{


    private RecyclerView mRecyclerView;
    private List<Restaurant> mList;
    private RestaurantAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.restaurant_list, container, false);


        Context context = getActivity();
        mList = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.restaurant_list);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        createView();

        mAdapter = new RestaurantAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void createView(){

        for (int i = 0; i < 20; i++) {
            mList.add(new Restaurant(R.drawable.fg_logo,"Уни пицца", "пиццерия/японская кухня","250","354",""));
        }
    }

}
