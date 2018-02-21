package com.indicode.cpayanf.ejrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleContacto extends AppCompatActivity {

    ImageView imgFotoDet;
    TextView txtNombreDet;
    TextView txtTelefonoDet;
    Toolbar tlbActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        String lsNombre;
        String lsTelefono;
        int liFoto;

        tlbActionBar = findViewById(R.id.tlbActionBar);
        setSupportActionBar(tlbActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgFotoDet = findViewById(R.id.imgFotoDet);
        txtNombreDet = findViewById(R.id.txtNombreDet);
        txtTelefonoDet = findViewById(R.id.txtTelefonoDet);
        registerForContextMenu(txtNombreDet);

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnuContxEdit:
                Toast.makeText(getBaseContext(), "Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnuContxDelete:
                Toast.makeText(getBaseContext(), "Delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void LevantaMenuPopUp(View v)
	{
		ImageView loimgContacto = findViewById(R.id.imgFotoDet);
		final PopupMenu mnuPupImagen = new PopupMenu(this, loimgContacto);
		mnuPupImagen.getMenuInflater().inflate(R.menu.menu_popup, mnuPupImagen.getMenu());
		mnuPupImagen.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				switch(menuItem.getItemId())
				{
					case R.id.mnuPupView:
						Toast.makeText(getBaseContext(), "View image", Toast.LENGTH_SHORT).show();
						break;
					case R.id.mnuPupViewDet:
						Toast.makeText(getBaseContext(), "View image detail", Toast.LENGTH_SHORT).show();
						break;
				}
				return true;
			}
		});
		mnuPupImagen.show();
	}
}
