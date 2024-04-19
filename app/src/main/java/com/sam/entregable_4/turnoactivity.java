package com.sam.entregable_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class turnoactivity extends AppCompatActivity {
    TextView Autor, Quote;
    String Autor_str, Quote_str;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Crear el xml de quote activity
        setContentView(R.layout.activity_turnoactivity);

        Autor = findViewById(R.id.cliente_text);
        Quote = findViewById(R.id.cita_text);

        Intent get_quote = this.getIntent();
        if (get_quote != null){
            Autor_str = get_quote.getStringExtra("quote_author");
            Quote_str = get_quote.getStringExtra("quote");

            Autor.setText(Autor_str);
            Quote.setText(Quote_str);
        }
    }
}
