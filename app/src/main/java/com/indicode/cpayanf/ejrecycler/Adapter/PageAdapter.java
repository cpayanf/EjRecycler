package com.indicode.cpayanf.ejrecycler.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by cpayan on 21/02/18.
 */

public class PageAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> goFragmentos;

	public PageAdapter(FragmentManager fm, ArrayList<Fragment> poFragmentos) {
		super(fm);
		this.goFragmentos = poFragmentos;
	}

	@Override
	public Fragment getItem(int position) {
		return goFragmentos.get(position);
	}

	@Override
	public int getCount() {
		return goFragmentos.size();
	}
}
