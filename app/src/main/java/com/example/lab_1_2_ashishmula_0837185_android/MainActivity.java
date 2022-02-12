package com.example.lab_1_2_ashishmula_0837185_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Model.Products;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper sqLiteDatabase;
    EditText edtName, edtDesc, edtPrice, edtLat, edtLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtDesc = findViewById(R.id.edtDesc);
        edtPrice = findViewById(R.id.edtPrice);
        edtLat = findViewById(R.id.edtLat);
        edtLong = findViewById(R.id.edtLong);

        findViewById(R.id.btnAddProduct).setOnClickListener(this);
        findViewById(R.id.btnProductsList).setOnClickListener(this);
        sqLiteDatabase = new DBHelper(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
//            case R.id.btnAddProduct:
//               addProducts();
//               break;
            case R.id.btnProductsList:
                startActivity(new Intent(this, ProductsActivity.class));


        }
    }



    private void addProducts() {
        String name = edtName.getText().toString().trim();
        String description=edtDesc.getText().toString().trim();
        String price=edtPrice.getText().toString().trim();
        String latitude=edtLat.getText().toString().trim();
        String longitude=edtLong.getText().toString().trim();

        if(name.isEmpty()){
            edtName.setError("Product name should not be empty");
            edtName.requestFocus();
            return;
        }
        if(description.isEmpty()){
            edtDesc.setError("Desc field cannot be empty");
            edtDesc.requestFocus();
            return;
        }
        if (price.isEmpty()){
            edtPrice.setError("Price field cannot be empty");
            edtPrice.requestFocus();
            return;
        }
        if (latitude.isEmpty()){
            edtLat.setError("Latitude filed cannot be empty");
            edtLat.requestFocus();
            return;
        }
        if (longitude.isEmpty()){
            edtLong.setError("Longitude filed cannot be empty");
            edtLong.requestFocus();
            return;
        }

        if (sqLiteDatabase.addProduct(name,description,Double.valueOf(price),Double.valueOf(latitude),Double.valueOf(longitude)))
            Toast.makeText(this,"Product Added",Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(this,"Product not added",Toast.LENGTH_SHORT).show();


    }
    @Override
    protected void onRestart(){
        super.onRestart();
        clearFields();
    }
    private  void clearFields(){
        edtName.setText("");
        edtDesc.setText("");
        edtPrice.setText("");
        edtLat.setText("");
        edtLong.setText("");
        edtName.clearFocus();
        edtDesc.clearFocus();
        edtPrice.clearFocus();
        edtLat.clearFocus();
        edtLong.clearFocus();
    }
}