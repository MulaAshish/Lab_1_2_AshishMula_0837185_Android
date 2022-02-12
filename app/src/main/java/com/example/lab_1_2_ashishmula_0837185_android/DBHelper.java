package com.example.lab_1_2_ashishmula_0837185_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Products;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DB_NAME = "Products_DataBase";
    private static final String TABLE_NAME = "Products";
    private static final String PRODUCT_NAME = "Name";
    private static final String PRODUCT_DESC = "Description";
    private static final String PRODUCT_PRICE = "Price";
    private static final String PROVIDER_LAT = "Latitude";
    private static final String PROVIDER_LONG = "Longitude";
    private static final String PRODUCT_ID = "id";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String sql=" CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT," + PRODUCT_DESC + " TEXT," + PRODUCT_PRICE + " DOUBLE," + PROVIDER_LAT + " DOUBLE," + PROVIDER_LONG + " DOUBLE);";
       sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME+";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public boolean addProduct(String name,String description,double price,double latitude,double longitude) {
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, name);
        values.put(PRODUCT_DESC, description);
        values.put(PRODUCT_PRICE, String.valueOf(price));
        values.put(PROVIDER_LAT, String.valueOf(latitude));
        values.put(PROVIDER_LONG, String.valueOf(longitude));

        return sqLiteDatabase.insert(TABLE_NAME,null,values)!= -1;
    }
    public Cursor getAllProducts(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME,null);
    }

    public boolean updateProducts(int id, String name, String description, double price, double latitude, double longitude){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, name);
        values.put(PRODUCT_DESC, description);
        values.put(PRODUCT_PRICE, String.valueOf(price));
        values.put(PROVIDER_LAT, String.valueOf(latitude));
        values.put(PROVIDER_LONG, String.valueOf(longitude));
        return sqLiteDatabase.update(TABLE_NAME, values,PRODUCT_ID + "=?",new String[]{String.valueOf(id)}) >0;
    }

    public boolean deleteProducts(int id) {
      SQLiteDatabase sqLiteDatabase = getWritableDatabase();
      return sqLiteDatabase.delete(TABLE_NAME,PRODUCT_ID + "=?",new String[]{String.valueOf(id)}) >0;
        //db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});

    }

//    public void updateProductName(int id, String name) {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(PRODUCT_NAME, name);
//        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(id)});
//
//    }
//
//    public void updateProductDescription(int id, String desc) {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(PRODUCT_DESC, desc);
//        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(id)});
//
//    }
//
//    public void updateProductPrice(int id, Double price1) {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(PRODUCT_PRICE, price1);
//        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(id)});
//
//
//    }
//
//    public void updateProviderLocation(int id, double latitude, double longitude) {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(PROVIDER_LAT, latitude);
//        values.put(PROVIDER_LONG, longitude);
//        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(id)});
//
//    }
//
//    public List<Products> getALLProducts() {
//        db = this.getWritableDatabase();
//        Cursor cursor = null;
//        List<Products> products = new ArrayList<>();
//        db.beginTransaction();
//        try {
//            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    do {
//                        Products products1 = new Products();
//                        products1.setId(cursor.getColumnIndex(PRODUCT_ID));
//                        products1.setpName(cursor.getString(cursor.getColumnIndexOrThrow(PRODUCT_NAME)));
//                        products1.setpDescription(cursor.getString(cursor.getColumnIndexOrThrow(PRODUCT_DESC)));
//                        products1.setpPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(PRODUCT_PRICE)));
//                        products1.setProviderLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(PROVIDER_LAT)));
//                        products1.setProviderLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(PROVIDER_LONG)));
//                        products.add(products1);
//
//
//                    } while (cursor.moveToNext());
//                }
//            }
//        } finally {
//            db.endTransaction();
//           cursor.close();
//        }
//        return products;
//    }
}
