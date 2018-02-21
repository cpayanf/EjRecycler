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
import com.indicode.cpayanf.ejrecycler.POJO.Contacto;
import com.indicode.cpayanf.ejrecycler.R;

import java.util.ArrayList;

/**
 * Created by cpayan on 21/02/18.
 */

public class RecyclerViewFragment extends Fragment {
	RecyclerView rvwContactos;
	ArrayList<Contacto> gaContactos;

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
		//para grid
		GridLayoutManager lmgContactos = new GridLayoutManager(getActivity(), 2);

		gaContactos = new ArrayList<Contacto>();
		gaContactos.add(new Contacto("Carlos Payan", "817272", "cp@dioash", R.drawable.overwolf500px));
		gaContactos.add(new Contacto("Roberto", "234124", "este@gmail", R.drawable.farmer80));
		gaContactos.add(new Contacto("ale garza", "441231", "correo@dioash", R.drawable.overwolf500px));
		gaContactos.add(new Contacto("luisa enra", "866342", "algoes@gmail", R.drawable.farmer80));

		rvwContactos.setLayoutManager(lmgContactos);
		InicializarAdaptador(gaContactos);
		return v;
	}

	protected void InicializarAdaptador(ArrayList<Contacto> paContactos)
	{
		ContactoAdapter cadContactos = new ContactoAdapter(paContactos, getActivity());
		rvwContactos.setAdapter(cadContactos);
	}
}
