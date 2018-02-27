package com.indicode.cpayanf.ejrecycler.Interfases;

import com.indicode.cpayanf.ejrecycler.Adapter.ContactoAdapter;
import com.indicode.cpayanf.ejrecycler.POJO.Contacto;

import java.util.ArrayList;

/**
 * Created by cpayan on 27/02/18.
 */

public interface IRecyclerViewFragmentView {
	void generarRecyclerLayout();

	ContactoAdapter crearAdaptador(ArrayList<Contacto> paContactos);

	void InicializarAdapter(ContactoAdapter poAdaptador);

}
