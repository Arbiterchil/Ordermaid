package com.example.ordermaid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class Beverages_Adapter extends RecyclerView.Adapter<Beverages_Adapter.BeverageViewHolder> {

    private Context bContext;
    private Cursor bcursor;
    private java.util.ArrayList<Itemclass> BeverageList;
    private ArrayList<Itemclass> ArrayList;
    private DBHelper dBclassHelper;

    public static final String col_totalName ="Name";
    public static final String col_totalPrice ="Total";
    public static final String tbl_total ="tbl_total";

    public Beverages_Adapter(Context context, ArrayList<Itemclass> BeverageList)
    {
        this.bContext =context;
        this.BeverageList =BeverageList;
        this.ArrayList =BeverageList;
        dBclassHelper =new DBHelper(context);

    }


    @NonNull
    @Override
    public BeverageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =LayoutInflater.from(bContext);
        View view =inflater.inflate(R.layout.items2,viewGroup,false);
        return new BeverageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BeverageViewHolder beverageViewHolder, int i) {
        final Itemclass beverageClass =BeverageList.get(i);
        beverageViewHolder.name.setText(beverageClass.getName());
        beverageViewHolder.price.setText(beverageClass.getPrice());


        beverageViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBclassHelper = new DBHelper(bContext);
                SQLiteDatabase db = dBclassHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(col_totalName,beverageViewHolder.name.getText().toString());
                contentValues.put(col_totalPrice,beverageViewHolder.price.getText().toString());
                long result =db.insert(tbl_total,null,contentValues);
                if(result ==-1)
                {
                    return;
                }
                else {
                    Toast.makeText(bContext,"You Order: "+beverageViewHolder.name.getText().toString()+" "+beverageViewHolder.price.getText().toString(),Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return BeverageList.size();
    }

    public class BeverageViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView price;
        public CircleImageView profile;
        public BeverageViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.text_name);
            price =itemView.findViewById(R.id.text_price);
        }
    }
}
