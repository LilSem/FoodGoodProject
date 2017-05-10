package com.food_good.lilsem.food_good;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.food_good.lilsem.food_good.dummy.DummyContent.DummyItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class RestarauntFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    FirebaseUser user = mAuth.getInstance().getCurrentUser();

    FirebaseRecyclerAdapter mAdapter;


    ImageView iv_restaurant_logo;
    TextView tw_restaurant_name;
    TextView tw_kitchen;
    TextView tw_salary;
    TextView tw_delivery;



    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    public RestarauntFragment() {
    }

    public static RestarauntFragment newInstance(int columnCount) {
        RestarauntFragment fragment = new RestarauntFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

//    private  static class TaskViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView iv_restaurant_logo;
//        TextView tw_restaurant_name;
//        TextView tw_kitchen;
//        TextView tw_salary;
//        TextView tw_delivery;
//
//        public TaskViewHolder(View itemView) {
//            super(itemView);
//            iv_restaurant_logo = (ImageView) itemView.findViewById(R.id.iv_restaurant_logo);
//            tw_restaurant_name = (TextView) itemView.findViewById(R.id.tw_restaurant_name);
//            tw_kitchen = (TextView) itemView.findViewById(R.id.tw_kitchen);
//            tw_salary = (TextView) itemView.findViewById(R.id.tw_salary);
//            tw_delivery = (TextView) itemView.findViewById(R.id.tw_delivery);
//        }
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaraunt_list, container, false);
//
//        myRef = FirebaseDatabase.getInstance().getReference();
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_recycler_view);
//
//        final FirebaseRecyclerAdapter <String,TaskViewHolder> adapter;
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//
//        adapter = new FirebaseRecyclerAdapter<String, TaskViewHolder>(
//                String.class,
//                R.layout.fragment_restaraunt,
//                TaskViewHolder.class,
//                myRef.child("restaurants")
//        ) {
//            @Override
//            protected void populateViewHolder(TaskViewHolder viewHolder, String name,final int position) {
//                viewHolder.tw_restaurant_name.setText(name);
//            }
//        };

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}
