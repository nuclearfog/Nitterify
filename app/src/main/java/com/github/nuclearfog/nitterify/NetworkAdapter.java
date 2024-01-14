package com.github.nuclearfog.nitterify;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Dropdown adapter used to show network options
 *
 * @author nuclearfog
 */
public class NetworkAdapter extends BaseAdapter {

	public static final int INDEX_DEFAULT = 0;
	public static final int INDEX_CUSTOM = 1;

	private int[] itemRes;

	/**
	 *
	 */
	public NetworkAdapter() {
		itemRes = new int[2];
		itemRes[INDEX_DEFAULT] = R.string.select_nitter;
		itemRes[INDEX_CUSTOM] = R.string.custom_instance;
	}


	@Override
	public int getCount() {
		return itemRes.length;
	}


	@Override
	public Integer getItem(int position) {
		return itemRes[position];
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
		text.setText(getItem(position));
		return text;
	}
}