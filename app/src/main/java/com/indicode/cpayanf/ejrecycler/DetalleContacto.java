package com.indicode.cpayanf.ejrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    ImageView imgFotoDet;
    TextView txtNombreDet;
    TextView txtTelefonoDet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        String lsNombre;
        String lsTelefono;
        int liFoto;

        imgFotoDet = findViewById(R.id.imgFotoDet);
        txtNombreDet = findViewById(R.id.txtNombreDet);
        txtTelefonoDet = findViewById(R.id.txtTelefonoDet);
        Bundle loParams = getIntent().getExtras();
        if(loParams != null)
        {
            lsNombre = loParams.getString("psNombreContacto");
            lsTelefono = loParams.getString("psTelefonoContacto");
            liFoto = loParams.getInt("psImagenContacto");

            imgFotoDet.setImageResource(liFoto);
            txtNombreDet.setText(lsNombre);
            txtTelefonoDet.setText(lsTelefono);
        }
    }
}
