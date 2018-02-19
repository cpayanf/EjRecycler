package com.indicode.cpayanf.ejrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvwContactos;
    ArrayList<Contacto> gaContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gaContactos = new ArrayList<Contacto>();

        gaContactos.add(new Contacto("Carlos Payan", "817272", "cp@dioash", R.drawable.overwolf500px));
        gaContactos.add(new Contacto("Roberto", "234124", "este@gmail", R.drawable.farmer80));
        gaContactos.add(new Contacto("ale garza", "441231", "correo@dioash", R.drawable.overwolf500px));
        gaContactos.add(new Contacto("luisa enra", "866342", "algoes@gmail", R.drawable.farmer80));

        rvwContactos = findViewById(R.id.rvwContactos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvwContactos.setLayoutManager(llm);
        InicializarAdaptador(gaContactos);
    }

    protected void InicializarAdaptador(ArrayList<Contacto> paContactos)
    {
        ContactoAdapter cadContactos = new ContactoAdapter(paContactos);
        rvwContactos.setAdapter(cadContactos);
    }
}
