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
public class BCombomeal extends Fragment {


    public BCombomeal() {
        // Required empty public constructor
    }
    private DBHelper db;
    private RecyclerView recyclerView;
    private ArrayList<Itemclass> Combo=new ArrayList<>();
    private Combo_Adapter Adapter;
        View vie;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vie = inflater.inflate(R.layout.fragment_bcombomeal, container, false);


        db = new DBHelper(getActivity().getApplicationContext());
        db.getWritableDatabase();


        recyclerView =vie.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Combo =db.ComboList();

        if(Combo.size() >0)
        {
            Adapter = new Combo_Adapter(getActivity().getApplicationContext(),Combo);
            recyclerView.setAdapter(Adapter);

        }
        else
        {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getActivity().getApplicationContext(),"No Data Yet",Toast.LENGTH_SHORT).show();

        }


    return vie;
    }

}
