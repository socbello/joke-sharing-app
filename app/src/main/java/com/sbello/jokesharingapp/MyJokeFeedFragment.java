package com.sbello.jokesharingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by socbe on 11/11/2017.
 */

public class MyJokeFeedFragment extends Fragment {

    private static final String TAG =  "MyJokeFeedFragment";

    private OnFragmentInteractionListener mListener;
    private ItemAdapter mItemAdapter;

    public MyJokeFeedFragment() {
        // Required empty public constructor
    }

    public static MyJokeFeedFragment newInstance() {
        MyJokeFeedFragment fragment = new MyJokeFeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.jokes_recycler_viewer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mItemAdapter = new ItemAdapter(getActivity());
        recyclerView.setAdapter(mItemAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            loadItems();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    private void loadItems(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("jokes");
        myRef.limitToLast(100).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item value = dataSnapshot.getValue(Item.class);
                value.key = dataSnapshot.getKey();
                mItemAdapter.addItem(value);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Item value = dataSnapshot.getValue(Item.class);
                value.key = dataSnapshot.getKey();
                mItemAdapter.updateItem(value);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Item value = dataSnapshot.getValue(Item.class);
                value.key = dataSnapshot.getKey();
                mItemAdapter.removeItem(value);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onItemSelected();
    }
}
