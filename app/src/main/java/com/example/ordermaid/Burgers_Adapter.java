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

public class Burgers_Adapter extends RecyclerView.Adapter<Burgers_Adapter.BurgersViewHolder> {


    private Context bContext;
    private java.util.ArrayList<Itemclass> BurgerList;
    private ArrayList<Itemclass> ArrayList;
    private DBHelper dBclassHelper;

    public static final String col_totalName ="Name";
    public static final String col_totalPrice ="Total";
    public static final String tbl_total ="tbl_total";

    public Burgers_Adapter(Context context, ArrayList<Itemclass> BurgerList)
    {
        this.bContext =context;
        this.BurgerList =BurgerList;
        this.ArrayList =BurgerList;
        dBclassHelper =new DBHelper(context);

    }

    @NonNull
    @Override
    public BurgersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =LayoutInflater.from(bContext);
        View view =inflater.inflate(R.layout.itemsl,viewGroup,false);
        return new Burgers_Adapter.BurgersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BurgersViewHolder burgersViewHolder, int i) {
        final Itemclass beverageClass =BurgerList.get(i);
        burgersViewHolder.name.setText(beverageClass.getName());
        burgersViewHolder.price.setText(beverageClass.getPrice());


        burgersViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBclassHelper = new DBHelper(bContext);
                SQLiteDatabase db = dBclassHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(col_totalName,burgersViewHolder.name.getText().toString());
                contentValues.put(col_totalPrice,burgersViewHolder.price.getText().toString());
                long result =db.insert(tbl_total,null,contentValues);
                if(result ==-1)
                {
                    return;
                }
                else {
                    Toast.makeText(bContext,"You Order: "+burgersViewHolder.name.getText().toString()+" "+burgersViewHolder.price.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return BurgerList.size();
    }

    public class BurgersViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView price;
        public BurgersViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.text_name);
            price =itemView.findViewById(R.id.text_price);

        }
    }


}
