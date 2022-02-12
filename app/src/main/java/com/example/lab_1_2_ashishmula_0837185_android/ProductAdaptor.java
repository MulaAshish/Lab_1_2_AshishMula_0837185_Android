package com.example.lab_1_2_ashishmula_0837185_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import Model.Products;

public class ProductAdaptor extends ArrayAdapter {
    private static final String TAG = "ProductAdapter";
    Context context;
    int layoutRes;
    List<Products> productsList;
    DBHelper sqLiteDatabase;

    public ProductAdaptor(@NonNull Context context, int resource, List<Products> productsList, DBHelper sqLiteDatabase) {
        super(context, resource, productsList);
        this.productsList=productsList;
        this.sqLiteDatabase=sqLiteDatabase;
        this.context = context;
        this.layoutRes = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) view = inflater.inflate(layoutRes, null);
        TextView Name = view.findViewById(R.id.txtName);
        TextView Description = view.findViewById(R.id.txtDesc);
        TextView Price = view.findViewById(R.id.txtPrice);
        TextView latitude = view.findViewById(R.id.txtLat);
        TextView longitude = view.findViewById(R.id.txtLong);

        final Products products = productsList.get(position);
        Name.setText(products.getpName());
        Description.setText(products.getpDescription());
        Price.setText(String.valueOf(products.getpPrice()));
        latitude.setText(String.valueOf(products.getProviderLatitude()));
        longitude.setText(String.valueOf(products.getProviderLongitude()));

        view.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                updateProducts(products);
            }

            private void updateProducts(final Products products) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view1 = layoutInflater.inflate(R.layout.dialog_update_products, null);
                builder.setView(view1);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText Name = view1.findViewById(R.id.edt_name);
                final EditText Description = view1.findViewById(R.id.edt_desc);
                final EditText Price = view1.findViewById(R.id.edt_price);
                final EditText latitude = view1.findViewById(R.id.edt_lat);
                final EditText longitude = view1.findViewById(R.id.edt_long);

                Name.setText(products.getpName());
                Description.setText(products.getpDescription());
                Price.setText(String.valueOf(products.getpPrice()));
                latitude.setText(String.valueOf( products.getProviderLatitude()));
                longitude.setText(String.valueOf( products.getProviderLongitude()));
                view1.findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = Name.getText().toString().trim();
                        String description = Description.getText().toString().trim();
                        String price = Price.getText().toString().trim();
                        String Latitude = latitude.getText().toString().trim();
                        String Longitude = longitude.getText().toString().trim();

                        if (name.isEmpty()) {
                            Name.setError("product name cannot be empty");
                            Name.requestFocus();
                            return;
                        }
                        if (description.isEmpty()) {
                            Description.setError("Desc field cannot be empty");
                            Description.requestFocus();
                            return;
                        }
                        if (price.isEmpty()) {
                            Price.setError("price field cannot be empty");
                            Price.requestFocus();
                            return;
                        }
                        if (Latitude.isEmpty()) {
                            latitude.setError("lat field cannot be empty");
                            latitude.requestFocus();
                            return;
                        }
                        if (Longitude.isEmpty()) {
                            longitude.setError("long field cannot be empty");
                            longitude.requestFocus();
                            return;
                        }
                        if (sqLiteDatabase.updateProducts(products.getId(), name, description, Double.parseDouble(price), Double.parseDouble(Latitude), Double.parseDouble(Longitude)))

                        loadProducts();
                        alertDialog.dismiss();
                    }

                });
            }
        });
        view.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deleteProducts(products);
            }
            private void deleteProducts(final  Products products) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are You Sure");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (sqLiteDatabase.deleteProducts(products.getId()))
                            loadProducts();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "The Product (" + products.getpName() + ") is not deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        Log.d(TAG,"getView" + getCount());
        return view;

    }
    @Override
    public int getCount() {
        return productsList.size();

    }
    private void loadProducts(){
        Cursor cursor= sqLiteDatabase.getAllProducts();
        productsList.clear();
        if (cursor.moveToFirst()){
            do{
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
        notifyDataSetChanged();
    }

}

