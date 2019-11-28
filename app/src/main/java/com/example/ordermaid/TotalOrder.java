package com.example.ordermaid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TotalOrder extends AppCompatActivity {
    private DBHelper db;
    private RecyclerView recyclerView;
    private ArrayList<OrderClass> order=new ArrayList<>();
    private Order_Adapter Adapter;
    Button btnDiscount;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_order);


//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int width = dm.widthPixels;
//        int hiegth = dm.heightPixels;
//
//        getWindow().setLayout(width*3,hiegth*4);


        btnDiscount =findViewById(R.id.discount);
        total =findViewById(R.id.total);
        db = new DBHelper(this.getApplicationContext());
        db.getWritableDatabase();


        recyclerView =findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        order =db.OrderList();

        if(order.size() >0)
        {
            Adapter = new Order_Adapter(this.getApplicationContext(),order);
            recyclerView.setAdapter(Adapter);

        }
        else
        {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this.getApplicationContext(),"No Data Yet",Toast.LENGTH_SHORT).show();

        }
        String query ="SELECT SUM(total) AS 'Total' FROM 'tbl_total'";
        SQLiteDatabase DB =db.getReadableDatabase();
        Cursor cursor =DB.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do
            {
                total.setText(cursor.getString(0));

            }while (cursor.moveToNext());

        }
        cursor.close();

        btnDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float Total =Float.parseFloat(total.getText().toString());
                Total*=0.30;
                total.setText(" " + Total);



            }
        });




    }

}
