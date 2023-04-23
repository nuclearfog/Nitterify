package com.github.nuclearfog.nitterify;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

	private SharedPreferences settings;
	private NetworkAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences("settings", MODE_PRIVATE);
		String domain = settings.getString("domain", "https://nitter.net");

		if (getIntent().getData() != null) {
			String url = domain + getIntent().getData().getPath();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
			try {
				startActivity(intent);
			} catch (ActivityNotFoundException err) {
				Toast.makeText(getApplicationContext(), R.string.error_open_link, Toast.LENGTH_SHORT).show();
			}
			finish();
		} else {
			setContentView(R.layout.layout_main);
			Spinner selector = findViewById(R.id.network_selector);
			adapter = new NetworkAdapter(getApplicationContext());
			selector.setAdapter(adapter);
			selector.setSelection(adapter.indexOf(domain), false);
			selector.setOnItemSelectedListener(this);
		}
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		String[] item = adapter.getItem(position);
		if (item != null && item.length == 2) {
			settings.edit().putString("domain", item[1]).apply();
		}
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}