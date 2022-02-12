package com.example.lab_1_2_ashishmula_0837185_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Model.Products;

public class ProductsActivity extends AppCompatActivity {
    DBHelper sqLiteDatabase;

    List<Products> productsList;
    ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
       productListView = findViewById(R.id.lvList);
        productsList=new ArrayList<>();
        sqLiteDatabase = new DBHelper(this);
        loadProducts();

    }

    private void loadProducts() {
        Cursor cursor =  sqLiteDatabase.getAllProducts();
        sqLiteDatabase.addProduct("Dairy milk","Chocolate",30,43.64,79.38);
        sqLiteDatabase.addProduct("Ballon","Party Decoration",20,43.08,79.08);
        sqLiteDatabase.addProduct("Table","Furniture",50,38.06,48.06);
        sqLiteDatabase.addProduct("Chair","furniture",25,56.34,36.24);
        sqLiteDatabase.addProduct("airPods","Earphones",120,78.23,45.23);
        sqLiteDatabase.addProduct("axe","Perfume",10,68.45,82.45);
        sqLiteDatabase.addProduct("bag","size cabin",34,23.56,56.34);
        sqLiteDatabase.addProduct("winter Boots","foot wear",100,56.35,78.34);
        sqLiteDatabase.addProduct("coke","Soda Drink",4,37.78,45.23);
        sqLiteDatabase.addProduct("kettle","Water Heater",23,84.34,74.33);



        if (cursor.moveToFirst()) {
            do {
                productsList.add(new Products(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        ProductAdaptor productAdaptor=new ProductAdaptor(this,R.layout.list_layout_products,productsList,sqLiteDatabase);
        productListView.setAdapter(productAdaptor);
    }
}