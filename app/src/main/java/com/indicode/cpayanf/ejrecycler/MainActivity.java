package com.indicode.cpayanf.ejrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvwContactos;
    ArrayList<Contacto> laContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        laContactos = new ArrayList<Contacto>();

        laContactos.add(new Contacto("Carlos Payan", "817272", "cp@dioash", R.drawable.overwolf500px));
        laContactos.add(new Contacto("Roberto", "234124", "este@gmail", R.drawable.farmer80));
        laContactos.add(new Contacto("ale garza", "441231", "correo@dioash", R.drawable.overwolf500px));
        laContactos.add(new Contacto("luisa enra", "866342", "algoes@gmail", R.drawable.farmer80));

        rvwContactos = findViewById(R.id.rvwContactos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvwContactos.setLayoutManager(llm);

    }

    protected void InicializarAdaptador()
    {
        ContactoAdapter cadContactos = new ContactoAdapter(laContactos);
        rvwContactos.setAdapter(cadContactos);
    }
}
