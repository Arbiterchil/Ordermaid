package com.example.ordermaid;

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

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.OrderHolder> {


    private Context cContext;
    private java.util.ArrayList<OrderClass> orderlist;
    private ArrayList<OrderClass> ArrayList;
    private DBHelper dBclassHelper;

    public static final String tbl_total ="tbl_total";


    public Order_Adapter(Context context, ArrayList<OrderClass> orderlist)
    {
        this.cContext =context;
        this.orderlist =orderlist;
        this.ArrayList =orderlist;
        dBclassHelper =new DBHelper(context);

    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =LayoutInflater.from(cContext);
        View view =inflater.inflate(R.layout.items4,viewGroup,false);
        return new Order_Adapter.OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder orderHolder, int i) {
        final OrderClass order =orderlist.get(i);
        orderHolder.ID.setText(order.getID());
        orderHolder.Name.setText(order.getName());
        orderHolder.Total.setText(order.getTotal());


    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder {
        public TextView ID;
        public TextView Name;
        public TextView Total;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            ID =itemView.findViewById(R.id.text_id);
            Name =itemView.findViewById(R.id.text_name);
            Total =itemView.findViewById(R.id.text_total);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    String id =ID.getText().toString();
                    int position =getAdapterPosition();
                    if(position!= RecyclerView.NO_POSITION)
                    {
                        orderlist.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,orderlist.size());

                        SQLiteDatabase db = dBclassHelper.getWritableDatabase();
                        db.delete(tbl_total,"ID"+"="+ID.getText().toString(),null);
                        Toast.makeText(cContext,"Order Remove",Toast.LENGTH_SHORT).show();
                        db.close();
                    }
                    return false;
                }
            });
        }
    }
}

