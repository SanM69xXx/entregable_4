package com.sam.entregable_4;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ListView listaQuotes;
    private List<turno> citaList;
    private adapters adapter;

    private FloatingActionButton floatingActionButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("citas");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addturnoactivity.class);
                startActivity(intent);
            }
        });

        citaList = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("citas");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                citaList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    turno quote = snapshot.getValue(turno.class);
                    citaList.add(quote);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Error al cargar los datos", error.toException());
            }
        });

        adapter = new adapters(this, citaList);
        listaQuotes = findViewById(R.id.listaCitas);
        listaQuotes.setAdapter(adapter);

        listaQuotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                turno quoteToSend = citaList.get(position);
                Intent SendInfo = new Intent(MainActivity.this, turnoactivity.class);

                SendInfo.putExtra("quote_author", quoteToSend.getAuthor());
                SendInfo.putExtra("quote", quoteToSend.getQuote());
                startActivity(SendInfo);
            }
        });
    }

}