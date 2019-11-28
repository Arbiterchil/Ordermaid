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
public class Beverages extends Fragment {

View vi;
    public Beverages() {
        // Required empty public constructor
    }
    private DBHelper db;
    private RecyclerView recyclerView;
    private ArrayList<Itemclass> beverages=new ArrayList<>();
    private Beverages_Adapter Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi =  inflater.inflate(R.layout.fragment_beverages, container, false);

        db = new DBHelper(getActivity().getApplicationContext());
        db.getWritableDatabase();


        recyclerView =vi.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        beverages =db.BeverageList();

        if(beverages.size() >0)
        {
            Adapter = new Beverages_Adapter(getActivity().getApplicationContext(),beverages);
            recyclerView.setAdapter(Adapter);

        }
        else
        {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getActivity().getApplicationContext(),"No Data Yet",Toast.LENGTH_SHORT).show();

        }








   return vi;
    }

}
