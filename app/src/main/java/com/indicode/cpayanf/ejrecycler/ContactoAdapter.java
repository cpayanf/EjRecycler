package com.indicode.cpayanf.ejrecycler;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gmrlenovopayan on 2018-02-18.
 */

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {
    ArrayList<Contacto> goContactos;

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoto;
        private TextView txtNombreCV;
        private TextView txtTelefonoCV;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFotoCont);
            txtNombreCV = itemView.findViewById(R.id.txtNombreCont);
            txtTelefonoCV = itemView.findViewById(R.id.txtTelefonoCont);
        }
    }

    public ContactoAdapter(ArrayList<Contacto> paContactos)
    {
        this.goContactos = paContactos;
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        Contacto loContacto = goContactos.get(position);
        holder.txtNombreCV.setText(loContacto.getNombre());
        holder.txtTelefonoCV.setText(loContacto.getTelefono());
        holder.imgFoto.setImageResource(loContacto.getFoto());
    }

    @Override
    public int getItemCount() {
        return goContactos.size();
    }
}
