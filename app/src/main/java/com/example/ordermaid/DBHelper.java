package com.example.ordermaid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DatabaseName ="OrderMenu.db";
    public static final String tbl_name1 ="tbl_beverages";
    public static final String tbl_name2 ="tbl_burgers";
    public static final String tbl_name3 ="tbl_comboMeal";
    public static final String tbl_price ="tbl_price";
    public static final String tbl_total ="tbl_total";

    public static final String col_bev1 ="Coke";
    public static final String col_bev2 ="Sprite";
    public static final String col_bev3 ="Orange_Juice";

    public static final String col_burger1 ="Burger_King";
    public static final String col_burger2 ="Mcdo_Burger";
    public static final String col_burger3 ="Cheese_Burger";

    public static final String col_Meal1 ="Chicken_Meal";
    public static final String col_Meal2 ="Combo_Meal1";
    public static final String col_Meal3 ="Combo_Meal2";

    public static final String col_price ="Price";
    public static final String col_name ="name";

    public static final String col_totalName ="Name";
    public static final String col_totalPrice ="Total";


    public String create1 = "create table " + tbl_name1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, '" + col_bev1 + "' INTEGER, '" + col_bev2 + "' INTEGER, '" + col_bev3 + "' INTEGER)";
    public String create2 = "create table " + tbl_name2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, '" + col_burger1 + "' INTEGER, '" + col_burger2 + "' INTEGER, '" + col_burger3 + "' INTEGER)" ;
    public String create3 ="create table " + tbl_name3 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, '" + col_Meal1 + "' INTEGER, '" + col_Meal2 + "' INTEGER, '" + col_Meal3 + "' INTEGER)";
    public String create4 = "create table " + tbl_price + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, '" + col_price + "' FLOAT,  '"+col_name+"' VARCHAR)";
    public String create5 = "create table " + tbl_total + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, '" + col_totalName + "' VARCHAR,  '"+col_totalPrice+"' FLOAT)";

    public DBHelper(Context context) {
        super(context, DatabaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(create1);
            db.execSQL(create2);
            db.execSQL(create3);
            db.execSQL(create4);
            db.execSQL(create5);
        }catch (Exception ex)
        {
            Log.d("TAG",ex.toString());
        }
        try {
            db.execSQL("INSERT or replace INTO " + tbl_name1 + "('" + col_bev1 + "','" + col_bev2 + "','" + col_bev3 + "') VALUES(1,2,3)");
            db.execSQL("INSERT or replace INTO " + tbl_name2 + "('" + col_burger1 + "','" + col_burger2 + "','" + col_burger3 + "') VALUES(4,5,6)");
            db.execSQL("INSERT or replace INTO " + tbl_name3 + "('" + col_Meal1 + "','" + col_Meal2 + "','" + col_Meal3 + "') VALUES(7,8,9)");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(25,'Coke')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(28,'Sprite')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(18,'OrangeJuice')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(65,'BurgerKing')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(55,'McDoBurger')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(45,'CheeseBurger')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(68,'ChickenMeal')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(78,'ComboMeal1')");
            db.execSQL("INSERT or replace INTO " + tbl_price + "('" + col_price + "','" + col_name + "') VALUES(90,'ComboMeal2')");
        }catch (Exception ex)
        {
            Log.d("TAG",ex.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + tbl_name1);
            db.execSQL("DROP TABLE IF EXISTS " + tbl_name2);
            db.execSQL("DROP TABLE IF EXISTS " + tbl_name3);
            db.execSQL("DROP TABLE IF EXISTS " + tbl_price);
            db.execSQL("DROP TABLE IF EXISTS " + tbl_total);
            onCreate(db);
        }catch (Exception ex)
        {
            Log.d("CREATION",ex.toString());
        }
    }
    public ArrayList<Itemclass> BeverageList()
    {
        String query ="SELECT a.name, a.`Price`FROM tbl_beverages AS b INNER JOIN tbl_price AS a ON b.`Coke`=a.`ID` UNION\n" +
                "SELECT a.name, a.`Price`FROM tbl_beverages AS b INNER JOIN tbl_price AS a ON b.`Orange_Juice`=a.`ID` UNION\n" +
                "SELECT a.name, a.`Price`FROM tbl_beverages AS b INNER JOIN tbl_price AS a ON b.`Sprite`=a.`ID`";
        SQLiteDatabase DB =this.getReadableDatabase();
        ArrayList<Itemclass> beverages  = new ArrayList<>();
        Cursor cursor =DB.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do
            {
                String name =cursor.getString(0);
                String Price =cursor.getString(1);
                beverages.add(new Itemclass(name,Price));

            }while (cursor.moveToNext());

        }
        cursor.close();
        return beverages;
    }

    public ArrayList<Itemclass> BurgerList()
    {
        String query ="SELECT a.name, a.`Price`FROM tbl_burgers AS b INNER JOIN tbl_price AS a ON b.`Burger_King`=a.`ID` UNION\n" +
                "SELECT a.name, a.`Price`FROM tbl_burgers AS b INNER JOIN tbl_price AS a ON b.`Mcdo_Burger`=a.`ID` UNION\n" +
                "SELECT a.name, a.`Price`FROM tbl_burgers AS b INNER JOIN tbl_price AS a ON b.`Cheese_Burger`=a.`ID`";
        SQLiteDatabase DB =this.getReadableDatabase();
        ArrayList<Itemclass> beverages  = new ArrayList<>();
        Cursor cursor =DB.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do
            {
                String name =cursor.getString(0);
                String Price =cursor.getString(1);
                beverages.add(new Itemclass(name,Price));

            }while (cursor.moveToNext());

        }
        cursor.close();
        return beverages;
    }

    public ArrayList<Itemclass> ComboList()
    {
        String query ="SELECT a.name, a.`Price`FROM tbl_combomeal AS b INNER JOIN tbl_price AS a ON b.`Chicken_Meal`=a.`ID` UNION\n" +
                "SELECT a.name, a.`Price`FROM tbl_combomeal AS b INNER JOIN tbl_price AS a ON b.`Combo_Meal1`=a.`ID` UNION\n" +
                "SELECT a.name, a.`Price`FROM tbl_combomeal AS b INNER JOIN tbl_price AS a ON b.`Combo_Meal2`=a.`ID`";
        SQLiteDatabase DB =this.getReadableDatabase();
        ArrayList<Itemclass> beverages  = new ArrayList<>();
        Cursor cursor =DB.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do
            {
                String name =cursor.getString(0);
                String Price =cursor.getString(1);
                beverages.add(new Itemclass(name,Price));

            }while (cursor.moveToNext());

        }
        cursor.close();
        return beverages;
    }
    public ArrayList<OrderClass> OrderList()
    {
        String query ="SELECT * FROM 'tbl_total'";
        SQLiteDatabase DB =this.getReadableDatabase();
        ArrayList<OrderClass> order  = new ArrayList<>();
        Cursor cursor =DB.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do
            {
                String ID =cursor.getString(0);
                String Name =cursor.getString(1);
                String Total =cursor.getString(2);
                order.add(new OrderClass(ID,Name,Total));

            }while (cursor.moveToNext());

        }
        cursor.close();
        return order;
    }


}
