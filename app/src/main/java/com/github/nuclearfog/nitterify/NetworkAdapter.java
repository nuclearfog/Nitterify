package com.github.nuclearfog.nitterify;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Dropdown adapter used to show network instances
 *
 * @author nuclearfog
 */
public class NetworkAdapter extends BaseAdapter {

	private List<String[]> items = new ArrayList<>();

	/**
	 *
	 */
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
		TextView text;
		if (convertView instanceof TextView) {
			text = (TextView) convertView;
		} else {
			text = new TextView(parent.getContext());
			text.setTextSize(TypedValue.COMPLEX_UNIT_PX, parent.getContext().getResources().getDimensionPixelSize(R.dimen.text_size_dropdown));
			text.setSingleLine(true);
		}
		final String[] item = items.get(position);
		text.setText(item[0]);
		return text;
	}

	/**
	 * get index of a string
	 *
	 * @param selection selection string to search
	 * @return index of the item or 0 if not found
	 */
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