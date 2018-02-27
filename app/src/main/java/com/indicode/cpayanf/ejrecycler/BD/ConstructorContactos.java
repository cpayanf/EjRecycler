package com.indicode.cpayanf.ejrecycler.BD;

import android.content.Context;

import com.indicode.cpayanf.ejrecycler.POJO.Contacto;
import com.indicode.cpayanf.ejrecycler.R;

import java.util.ArrayList;

/**
 * Created by cpayan on 27/02/18.
 */

public class ConstructorContactos {
	private Context myContext;

	public ConstructorContactos(Context poContext)
	{
		this.myContext = poContext;
	}

	public ArrayList<Contacto> obtenerContactos(){
		ArrayList<Contacto>	laContactos = new ArrayList<Contacto>();
		laContactos.add(new Contacto("Carlos Payan", "817272", "cp@dioash", R.drawable.overwolf500px));
		laContactos.add(new Contacto("Roberto", "234124", "este@gmail", R.drawable.farmer80));
		laContactos.add(new Contacto("ale garza", "441231", "correo@dioash", R.drawable.overwolf500px));
		laContactos.add(new Contacto("luisa enra", "866342", "algoes@gmail", R.drawable.farmer80));

		return laContactos;
	}
}
