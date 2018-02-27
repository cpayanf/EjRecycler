package com.indicode.cpayanf.ejrecycler.Interfases;

import android.content.Context;

import com.indicode.cpayanf.ejrecycler.BD.ConstructorContactos;
import com.indicode.cpayanf.ejrecycler.POJO.Contacto;

import java.util.ArrayList;

/**
 * Created by cpayan on 27/02/18.
 */

public class RecyclerViewFragmentPresenter implements  IRecyclerViewFragmentPresenter{
	private IRecyclerViewFragmentView myView;
	private Context myContext;
	private ConstructorContactos myConstContactos;
	private ArrayList<Contacto> myContactos;

	public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView poView, Context poContext)
	{
		this.myView = poView;
		this.myContext = poContext;
	}

	@Override
	public void obtenerContactosBaseDatos() {
		myConstContactos = new ConstructorContactos(myContext);
		myContactos = myConstContactos.obtenerContactos();
		mostrarContactos();
	}

	@Override
	public void mostrarContactos() {
		myView.InicializarAdapter(myView.crearAdaptador(myContactos));
	}
}
