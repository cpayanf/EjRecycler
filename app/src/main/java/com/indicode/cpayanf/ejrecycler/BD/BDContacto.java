package com.indicode.cpayanf.ejrecycler.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.indicode.cpayanf.ejrecycler.POJO.Contacto;

import java.util.ArrayList;

/**
 * Created by cpayan on 27/02/18.
 */


public class BDContacto extends SQLiteOpenHelper {
	Context myContext;

	public BDContacto(Context context) {
		super(context, "CONTACTOS", null, 2);
		this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		try {


			String lsQuery = "CREATE TABLE EstContactos (";
			lsQuery += "iCodContacto integer primary key autoincrement,";
			lsQuery += "vchNombre text,";
			lsQuery += "vchTelefono text,";
			lsQuery += "vchEmail text,";
			lsQuery += "iFoto integer)";

			sqLiteDatabase.execSQL(lsQuery);

			lsQuery = "CREATE TABLE HisContactoLikes (";
			lsQuery += "iCodContactoLike integer primary key autoincrement,";
			lsQuery += "iCodContacto integer,";
			lsQuery += "iCantidadLikes integer,";
			lsQuery += "Foreign key (iCodContacto) references EstContactos(iCodContacto));";
			lsQuery += " create unique index AK_ContactoLikesContacto on HisContactoLikes(iCodContacto)";
			sqLiteDatabase.execSQL(lsQuery);
			Toast.makeText(myContext, "BD Create", Toast.LENGTH_SHORT).show();
			sqLiteDatabase.close();
		}
		catch(Exception ex) {
			Toast.makeText(myContext, "error:"  +ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		String lsQuery;
		lsQuery = " create unique index AK_ContactoLikesContacto on HisContactoLikes(iCodContacto)";
		sqLiteDatabase.execSQL(lsQuery);
		Toast.makeText(myContext, "BD Upgrade " + i + "," + i1, Toast.LENGTH_SHORT).show();
	}

	public ArrayList<Contacto> obtenerContactos() {
		ArrayList<Contacto> laContactos = new ArrayList<Contacto>();
		String lsQuery = "select * from EstContactos";
		String lsQueryLikes;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor loRegistros = db.rawQuery(lsQuery, null);
		Cursor loRegLikes;
		while (loRegistros.moveToNext()) {
			Contacto loContactoAct = new Contacto(loRegistros.getInt(0));
			loContactoAct.setNombre(loRegistros.getString(1));
			loContactoAct.setTelefono(loRegistros.getString(2));
			loContactoAct.setEmail(loRegistros.getString(3));
			loContactoAct.setFoto(loRegistros.getInt(4));

			lsQueryLikes = "select iCantidadLikes from HisContactoLikes";
			lsQueryLikes += " where iCodContacto = " + loRegistros.getInt(0);
			loRegLikes = db.rawQuery(lsQueryLikes, null);
			if(loRegLikes.moveToNext())
			{
				loContactoAct.setLikes(loRegLikes.getInt(0));
			}
			else
			{
				loContactoAct.setLikes(0);
			}
			laContactos.add(loContactoAct);
		}
		return laContactos;
	}

	public void InsertaContacto(ContentValues poContValues) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert("EstContactos", null, poContValues);
		db.close();
	}

	public int IncrementaLikeContacto(int piCodContacto)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String lsQuery;
		try {
			lsQuery =
					" insert or ignore into HisContactoLikes(iCodContacto, iCantidadLikes) values(" +
							piCodContacto + ", 0);";
			db.execSQL(lsQuery);
			lsQuery = " update HisContactoLikes set iCantidadLikes = iCantidadLikes + 1";
			lsQuery += " where iCodContacto = " + piCodContacto;
			db.execSQL(lsQuery);

			lsQuery = "select iCantidadLikes from HisContactoLikes where iCodContacto = " +
					piCodContacto;
			Cursor loRegistros = db.rawQuery(lsQuery, null);
			if (loRegistros.moveToNext()) {
				return loRegistros.getInt(0);
			} else
				return 0;
		}
		catch (Exception ex) {
			Toast.makeText(myContext, "error:"  +ex.getMessage(), Toast.LENGTH_SHORT).show();
			return -1;
		}
		finally {
			db.close();
		}
	}
}