package com.indicode.cpayanf.ejrecycler.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.indicode.cpayanf.ejrecycler.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

	Button btnPreferencias;

	public PerfilFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_perfil, container, false);
		CrearBotonPreferencias(v);

		return v;
	}

	public void CrearBotonPreferencias(View v)
	{
		btnPreferencias = v.findViewById(R.id.btnPreferencias);
		btnPreferencias.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				verPreferencias(view);
			}
		});
	}

	public void verPreferencias(View v)
	{
		String lsNombrePref;
		try {
			SharedPreferences myPrefs =
					getActivity().getSharedPreferences("DatosPref", Context.MODE_PRIVATE);
			lsNombrePref = myPrefs.getString("Nombre", "");
			if (lsNombrePref == "") {
				Toast.makeText(getActivity(), "No se ha registrado un nombre", Toast.LENGTH_SHORT).show();
				SharedPreferences.Editor prefEditor = myPrefs.edit();
				prefEditor.putString("Nombre", "Carlos");
				prefEditor.commit();
			} else {
				Toast.makeText(getActivity(), "Nombre" + lsNombrePref, Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception ex)
		{
			Toast.makeText(getActivity(), ex.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
