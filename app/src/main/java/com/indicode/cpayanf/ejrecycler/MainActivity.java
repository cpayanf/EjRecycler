package com.indicode.cpayanf.ejrecycler;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.indicode.cpayanf.ejrecycler.Adapter.PageAdapter;
import com.indicode.cpayanf.ejrecycler.Fragment.PerfilFragment;
import com.indicode.cpayanf.ejrecycler.Fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar tlbActionBar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Context myContext;
    Activity myActiviy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myContext = getApplicationContext();
        myActiviy = this;
        tlbActionBar = findViewById(R.id.tlbActionBar);
        setSupportActionBar(tlbActionBar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
		setUpViewPager();
    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.menu_opciones, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
			case R.id.mnuOpcSettings:
				Toast.makeText(getBaseContext(), "Settings", Toast.LENGTH_SHORT).show();
				break;
			case R.id.mnuOpcAbout:
				Toast.makeText(getBaseContext(), "About", Toast.LENGTH_SHORT).show();
				break;
			case R.id.mnuOpcRefresh:
				Toast.makeText(getBaseContext(), "Refresh", Toast.LENGTH_SHORT).show();
				break;

		}

		return super.onOptionsItemSelected(item);
	}

	private ArrayList<Fragment> agregarFraments()
	{
		ArrayList<Fragment> loFragmentos = new ArrayList<>();
		loFragmentos.add(new RecyclerViewFragment());
		loFragmentos.add(new PerfilFragment());

		return loFragmentos;
	}

	private void setUpViewPager() {
    	viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFraments()));
    	tabLayout.setupWithViewPager(viewPager);

    	tabLayout.getTabAt(0).setIcon(R.drawable.refresh24);
		tabLayout.getTabAt(1).setIcon(R.drawable.overwolf500px);
	}

	public void habilitarBluetooth(View v)
	{
		solicitarPermiso();
		BluetoothAdapter myBluetooth = BluetoothAdapter.getDefaultAdapter();
		if(myBluetooth == null)
		{
			Toast.makeText(MainActivity.this, "No tienes bluetooth", Toast.LENGTH_SHORT).show();
			return;
		}

		if(!myBluetooth.isEnabled())
		{
			Intent habilitarBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(habilitarBluetooth, 0);
		}
	}

	public boolean checarStatusPermiso()
	{
		int liResultado = ContextCompat.checkSelfPermission(myContext, Manifest.permission.BLUETOOTH);
		if(liResultado == PackageManager.PERMISSION_GRANTED)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void solicitarPermiso(){
    	if (!ActivityCompat.shouldShowRequestPermissionRationale(myActiviy, Manifest.permission.BLUETOOTH))
		{
			ActivityCompat.requestPermissions(myActiviy, new String[] {Manifest.permission.BLUETOOTH}, 1);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
			@NonNull int[] grantResults) {
		switch (requestCode){
			case 1:
				if(checarStatusPermiso()) {
					Toast.makeText(MainActivity.this, "Ya esta activo", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(MainActivity.this, "No esta activo", Toast.LENGTH_SHORT).show();
				}
				break;
		};
	}
}
