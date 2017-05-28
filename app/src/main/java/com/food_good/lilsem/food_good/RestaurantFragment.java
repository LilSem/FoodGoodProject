package com.food_good.lilsem.food_good;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food_good.lilsem.food_good.model.Restaurant;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFragment extends Fragment implements RestaurantAdapter.OnRecyclerViewItemClickListener {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference;

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
        mAdapter = new RestaurantAdapter(mList, this);
        mRecyclerView.setAdapter(mAdapter);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("restaurants");

        updateList();

        return view;
    }

    private void updateList() {

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mList.add(dataSnapshot.getValue(Restaurant.class));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Restaurant model = dataSnapshot.getValue(Restaurant.class);
                int index = getItemIndex(model);
                mList.set(index, model);
                mAdapter.notifyItemChanged(index);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Restaurant model = dataSnapshot.getValue(Restaurant.class);
                int index = getItemIndex(model);

                mList.remove(index);
                mAdapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(Restaurant restaurant) {
        int index = -1;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).key.equals(restaurant.key)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void onClick(int position) {
        DishFragment dishFragment = new DishFragment();

        String id;

        id = mList.get(position).id;
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        dishFragment.setArguments(bundle);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, dishFragment)
                .addToBackStack(null)
                .commit();
    }
}
