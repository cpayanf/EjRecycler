package com.indicode.cpayanf.ejrecycler.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.indicode.cpayanf.ejrecycler.POJO.Contacto;
import com.indicode.cpayanf.ejrecycler.DetalleContacto;
import com.indicode.cpayanf.ejrecycler.R;

import java.util.ArrayList;

/**
 * Created by gmrlenovopayan on 2018-02-18.
 */

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {
    ArrayList<Contacto> goContactos;
    Activity ActRecycler;
    int piPosicion;

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFotoCont;
        private TextView txtNombreCont;
        private TextView txtTelefonoCont;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            imgFotoCont = itemView.findViewById(R.id.imgFotoCont);
            txtNombreCont = itemView.findViewById(R.id.txtNombreCont);
            txtTelefonoCont = itemView.findViewById(R.id.txtTelefonoCont);
        }

    }

    public ContactoAdapter(ArrayList<Contacto> paContactos, Activity paActivity)
    {
        this.goContactos = paContactos;
        this.ActRecycler = paActivity;
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        final Contacto loContacto = goContactos.get(position);
        holder.txtNombreCont.setText(loContacto.getNombre());
        holder.txtTelefonoCont.setText(loContacto.getTelefono());
        holder.imgFotoCont.setImageResource(loContacto.getFoto());
		piPosicion = position;

        holder.imgFotoCont.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
				Intent loIntent = new Intent(ActRecycler, DetalleContacto.class);
				loIntent.putExtra("psImagenContacto", loContacto.getFoto());
				loIntent.putExtra("psNombreContacto", loContacto.getNombre());
				loIntent.putExtra("psTelefonoContacto", loContacto.getTelefono());
				ActRecycler.startActivity(loIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goContactos.size();
    }
}
