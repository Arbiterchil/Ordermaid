package com.example.ordermaid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Combo_Adapter extends RecyclerView.Adapter<Combo_Adapter.ComboViewHolder> {

    private Context cContext;
    private java.util.ArrayList<Itemclass> ComboList;
    private ArrayList<Itemclass> ArrayList;
    private DBHelper dBclassHelper;

    public static final String col_totalName ="Name";
    public static final String col_totalPrice ="Total";
    public static final String tbl_total ="tbl_total";

    public Combo_Adapter(Context context, ArrayList<Itemclass> ComboList)
    {
        this.cContext =context;
        this.ComboList =ComboList;
        this.ArrayList =ComboList;
        dBclassHelper =new DBHelper(context);

    }
    @NonNull
    @Override
    public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =LayoutInflater.from(cContext);
        View view =inflater.inflate(R.layout.items3,viewGroup,false);
        return new Combo_Adapter.ComboViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComboViewHolder comboViewHolder, int i) {
        final Itemclass beverageClass =ComboList.get(i);
        comboViewHolder.name.setText(beverageClass.getName());
        comboViewHolder.price.setText(beverageClass.getPrice());


        comboViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBclassHelper = new DBHelper(cContext);
                SQLiteDatabase db = dBclassHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(col_totalName,comboViewHolder.name.getText().toString());
                contentValues.put(col_totalPrice,comboViewHolder.price.getText().toString());
                long result =db.insert(tbl_total,null,contentValues);
                if(result ==-1)
                {
                    return;
                }
                else {
                    Toast.makeText(cContext,"You Order: "+comboViewHolder.name.getText().toString()+" "+comboViewHolder.price.getText().toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return ComboList.size();
    }

    public class ComboViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView price;
        public ComboViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.text_name);
            price =itemView.findViewById(R.id.text_price);

        }
    }
}

