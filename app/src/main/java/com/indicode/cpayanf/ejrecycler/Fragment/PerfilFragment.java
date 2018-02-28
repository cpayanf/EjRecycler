package com.indicode.cpayanf.ejrecycler.Fragment;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.indicode.cpayanf.ejrecycler.R;

import org.w3c.dom.Text;

import java.text.DateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

	Button btnPreferencias;
	Button btnLogLlamadas;
	TextView lblLogLlamadas;

	public PerfilFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_perfil, container, false);
		CrearBotonPreferencias(v);
		CrearBotonLogLlamadas(v);
		lblLogLlamadas = v.findViewById(R.id.lblLogLlamadas);

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
	public void CrearBotonLogLlamadas(View v)
	{
		btnLogLlamadas = v.findViewById(R.id.btnLogLlamadas);
		btnLogLlamadas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mostrarLogLlamadas(view);
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

	public void mostrarLogLlamadas(View v){
		if (checarPermiso()){
			consultarLogLlamadas();
		}
		else
		{
			solicitarPermisoLog();
		}
	}

	public boolean checarPermiso(){
		boolean lbPermisoReadCL = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) ==
				PackageManager.PERMISSION_GRANTED;
		boolean lbPermisoWriteCL = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CALL_LOG) ==
				PackageManager.PERMISSION_GRANTED;

		if(lbPermisoReadCL && lbPermisoWriteCL)
		{
			return true;
		}
		else return false;
	}
	public void solicitarPermisoLog()
	{
		boolean lbPermisoReadCL = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_CALL_LOG);
		boolean lbPermisoWriteCL = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_CALL_LOG);

		if (lbPermisoReadCL && lbPermisoWriteCL)
		{
			Toast.makeText(getActivity(), "Permisos Otorgados", Toast.LENGTH_SHORT).show();
		}
		else
		{
			ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG}, 1);
		}
	}

	public void consultarLogLlamadas(){
		Uri luDirLlamadas = CallLog.Calls.CONTENT_URI;
		String laCampos[] = {CallLog.Calls.NUMBER, CallLog.Calls.DATE,
				CallLog.Calls.TYPE, CallLog.Calls.DURATION
		};
		lblLogLlamadas.setText("");
		ContentResolver loContResolver = getActivity().getContentResolver();
		Cursor loRegistros = loContResolver.query(luDirLlamadas, laCampos, null,null, CallLog.Calls.DATE + " DESC");

		while(loRegistros.moveToNext()){
			String lsNumero = loRegistros.getString(loRegistros.getColumnIndex(laCampos[0]));
			Long liFecha  = loRegistros.getLong(loRegistros.getColumnIndex(laCampos[1]));
			int liTipo  = loRegistros.getInt(loRegistros.getColumnIndex(laCampos[2]));
			String lsDuracion = loRegistros.getString(loRegistros.getColumnIndex(laCampos[3]));
			String lsTipoLlamada;
			String lsMensaje;

			switch (liTipo){
				case CallLog.Calls.INCOMING_TYPE:
					lsTipoLlamada = "Entrante";
					break;
				case CallLog.Calls.MISSED_TYPE:
					lsTipoLlamada = "Perdida";
					break;
				case CallLog.Calls.OUTGOING_TYPE:
					lsTipoLlamada = "Saliente";
					break;
				default:
					lsTipoLlamada = "Desconocido";
			}
			lsMensaje = "numero: " + lsNumero
					+ ", Fecha: " + (android.text.format.DateFormat.format("yyyy/mm/dd k:mm", liFecha))
					+ ", Duracion: " + lsDuracion + "s."
					+ ", Tipo: " + lsTipoLlamada + "\n";
			lblLogLlamadas.append(lsMensaje);

		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
		switch	(requestCode)
		{
			case 1:
				if(checarPermiso()){
					Toast.makeText(getActivity(), "Ya esta activo el permiso", Toast.LENGTH_SHORT).show();
					consultarLogLlamadas();
				}
				else{
					Toast.makeText(getActivity(), "No esta activo el permiso", Toast.LENGTH_SHORT).show();
				}
		}
	}

}
