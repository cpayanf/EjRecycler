package com.indicode.cpayanf.ejrecycler.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.indicode.cpayanf.ejrecycler.Adapter.ContactoAdapter;
import com.indicode.cpayanf.ejrecycler.BD.BDContacto;
import com.indicode.cpayanf.ejrecycler.Interfases.IRecyclerViewFragmentPresenter;
import com.indicode.cpayanf.ejrecycler.Interfases.IRecyclerViewFragmentView;
import com.indicode.cpayanf.ejrecycler.Interfases.RecyclerViewFragmentPresenter;
import com.indicode.cpayanf.ejrecycler.POJO.Contacto;
import com.indicode.cpayanf.ejrecycler.R;

import java.util.ArrayList;

/**
 * Created by cpayan on 21/02/18.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {
	RecyclerView rvwContactos;
	IRecyclerViewFragmentPresenter myPresenter;
	BDContacto myBD;

	public RecyclerViewFragment()
	{

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

		rvwContactos = v.findViewById(R.id.rvwContactos);
		myPresenter = new RecyclerViewFragmentPresenter(this, getContext());
		myPresenter.obtenerContactosBaseDatos();
		//para grid

		generarRecyclerLayout();
		return v;
	}

	@Override
	public void generarRecyclerLayout() {
		GridLayoutManager lmgContactos = new GridLayoutManager(getActivity(), 2);
		rvwContactos.setLayoutManager(lmgContactos);
	}

	@Override
	public ContactoAdapter crearAdaptador(ArrayList<Contacto> paContactos) {
		ContactoAdapter cadContactos = new ContactoAdapter(paContactos, getActivity());
		return cadContactos;
	}

	@Override
	public void InicializarAdapter(ContactoAdapter poAdaptador) {
		rvwContactos.setAdapter(poAdaptador);
	}
}
