package com.example.ordermaid;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Burgers extends Fragment {

    View v;
    public Burgers() {
        // Required empty public constructor
    }
    private DBHelper db;
    private RecyclerView recyclerView;
    private ArrayList<Itemclass> burgers=new ArrayList<>();
    private Burgers_Adapter Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_burgers, container, false);

        db = new DBHelper(getActivity().getApplicationContext());
        db.getWritableDatabase();


        recyclerView =v.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        burgers =db.BurgerList();

        if(burgers.size() >0)
        {
            Adapter = new Burgers_Adapter(getActivity().getApplicationContext(),burgers);
            recyclerView.setAdapter(Adapter);

        }
        else
        {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getActivity().getApplicationContext(),"No Data Yet",Toast.LENGTH_SHORT).show();

        }




    return  v;
    }

}
