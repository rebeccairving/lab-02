package com.example.listycity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button addButton ;
    Button deleteButton;

    int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        // define array
        String [] cities = {"Edmonton", "Calgary", "Toronto", "Winnepeg", "London"};
        dataList = new ArrayList<>();
        //how to extend in
        //one edit text field such that you can type in the city name and then
        // you will have an add button such that whatever text in text field that gets
        // populated to list
        // you need a way to define a button where? in the the activity main

        dataList.addAll(Arrays.asList(cities));
        // why was filename "content"?

        cityAdapter = new ArrayAdapter<>(this,R.layout.context, dataList);
        cityList.setAdapter(cityAdapter);

        addButton = findViewById(R.id.button_add);
        deleteButton = findViewById(R.id.button_delete);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selected = position;
            cityList.setItemChecked(position, true);
        });

        addButton.setOnClickListener(v -> {
            final android.widget.EditText input = new android.widget.EditText(MainActivity.this);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Add City")
                    .setView(input)
                    .setPositiveButton("CONFIRM", (dialog, which) -> {
                        dataList.add(input.getText().toString());
                        cityAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("CANCEL", null)
                    .show();
        });

        deleteButton.setOnClickListener(v -> {
            if (selected == -1) {
                return;
            }// stays untill clicked one

            dataList.remove(selected);
            cityAdapter.notifyDataSetChanged();
            selected = -1;
            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        });


        //button. presents all options
    }
}
