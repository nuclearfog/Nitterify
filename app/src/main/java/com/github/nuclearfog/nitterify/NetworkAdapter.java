package com.github.nuclearfog.nitterify;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NetworkAdapter extends BaseAdapter {

	private List<String[]> items = new ArrayList<>();

	public NetworkAdapter(Context context) {
		TypedArray tArray = context.getResources().obtainTypedArray(R.array.dropdown_selection);
		for (int i = 0 ; i < tArray.length() ; i++) {
			int resId = tArray.getResourceId(i, 0);
			if (resId != 0) {
				items.add(context.getResources().getStringArray(resId));
			}
		}
		tArray.recycle();
	}


	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public String[] getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dropdown, parent, false);
		}
		TextView text = convertView.findViewById(R.id.item_network);

		final String[] item = items.get(position);
		text.setText(item[0]);
		return text;
	}


	public int indexOf(String selection) {
		for (int i = 0 ; i < getCount() ; i++) {
			String[] item = items.get(i);
			if (item != null && item.length == 2 && selection.equals(item[1])) {
				return i;
			}
		}
		return 0;
	}
}