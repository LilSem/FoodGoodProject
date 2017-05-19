package com.food_good.lilsem.food_good;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food_good.lilsem.food_good.model.Dish;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DishFragment extends Fragment{

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference;

    private RecyclerView mRecyclerView;
    private List<Dish> mList;
    private DishAdapter mAdapter;

    String id = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dish_list, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
        }

        Context context = getActivity();
        mList = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dish_list);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DishAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mReference = mFirebaseDatabase.getReference("dishes");

        updateList();
        return view;
    }
//.orderByChild("restaurantId").equalTo(key)
//    private void createView(){
//
//        for (int i = 0; i < 20; i++) {
//            mList.add(new Restaurant(R.drawable.fg_logo,"Уни пицца", "пиццерия/японская кухня","250","354",""));
//        }
//    }

    private void updateList() {
        if (id.isEmpty()) {
            mReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mList.add(dataSnapshot.getValue(Dish.class));
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Dish model = dataSnapshot.getValue(Dish.class);
                    int index = getItemIndex(model);
                    mList.set(index, model);
                    mAdapter.notifyItemChanged(index);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Dish model = dataSnapshot.getValue(Dish.class);
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

        } else {
            mReference.orderByChild("restaurantId").equalTo(id).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mList.add(dataSnapshot.getValue(Dish.class));
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Dish model = dataSnapshot.getValue(Dish.class);
                    int index = getItemIndex(model);
                    mList.set(index, model);
                    mAdapter.notifyItemChanged(index);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Dish model = dataSnapshot.getValue(Dish.class);
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
    }


    private int getItemIndex(Dish dish){
        int index = -1;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).key.equals(dish.key)){
                index = i;
                break;
            }
        }
        return index;
    }

}
