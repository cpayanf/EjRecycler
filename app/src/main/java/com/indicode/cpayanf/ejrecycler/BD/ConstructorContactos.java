package com.indicode.cpayanf.ejrecycler.BD;

import android.content.ContentValues;
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
		BDContacto db = new BDContacto(myContext);
		//InsertaContactosDummy(db);
		return db.obtenerContactos();
	}
	public int IncrementaLikeContacto(int piCodContacto){
		BDContacto db = new BDContacto(myContext);
		return db.IncrementaLikeContacto(piCodContacto);
	}

	public void InsertaContactosDummy(BDContacto db)
	{
		ContentValues loContValues = new ContentValues();
		loContValues.put("vchNombre", "Carlos Payan");
		loContValues.put("vchTelefono", "8212312");
		loContValues.put("vchEmail", "cpayan@fsdlj.com");
		loContValues.put("iFoto", R.drawable.overwolf500px);
		db.InsertaContacto(loContValues);

		loContValues = new ContentValues();
		loContValues.put("vchNombre", "Jorge Ramirez");
		loContValues.put("vchTelefono", "721922");
		loContValues.put("vchEmail", "jorge@fsdlj.com");
		loContValues.put("iFoto", R.drawable.farmer80);
		db.InsertaContacto(loContValues);

		loContValues = new ContentValues();
		loContValues.put("vchNombre", "Leslie");
		loContValues.put("vchTelefono", "9991212");
		loContValues.put("vchEmail", "jorge@fsdlj.com");
		loContValues.put("iFoto", R.drawable.overwolf500px);
		db.InsertaContacto(loContValues);

		loContValues = new ContentValues();
		loContValues.put("vchNombre", "Jessy");
		loContValues.put("vchTelefono", "5552341");
		loContValues.put("vchEmail", "jessy@fsdlj.com");
		loContValues.put("iFoto", R.drawable.farmer80);
		db.InsertaContacto(loContValues);
	}
}
