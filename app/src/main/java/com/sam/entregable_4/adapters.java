package com.sam.entregable_4;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class adapters extends ArrayAdapter<turno> {
    Context Contexto;
    private List<turno> citaList;
    LayoutInflater inflater;

    public adapters(Context contexto, List<turno> citaList){
        super(contexto, R.layout.activity_turno, citaList);
        this.Contexto = contexto;
        this.citaList = citaList;
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    public View getView(int i, View convertView, ViewGroup parent){
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(Contexto).inflate(R.layout.activity_turno, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.authorTextView = convertView.findViewById(R.id.cliente);
            viewHolder.quoteTextView = convertView.findViewById(R.id.cita);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        turno quote = citaList.get(i);
        viewHolder.authorTextView.setText(quote.getAuthor());
        viewHolder.quoteTextView.setText(quote.getQuote());

        return convertView;
    }

    static class ViewHolder{
        TextView authorTextView;
        TextView quoteTextView;
    }

}
