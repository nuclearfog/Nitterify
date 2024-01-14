package com.github.nuclearfog.nitterify;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Main activity of this application, used to show network selector and handle link conversion
 *
 * @author nuclearfog
 */
public class MainActivity extends Activity implements OnClickListener, OnItemSelectedListener {

	private AppSettings settings;

	private EditText urlInput;
	private View urlContainer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		Spinner selector = findViewById(R.id.network_selector);
		View confirmButton = findViewById(R.id.network_confirm);
		urlContainer = findViewById(R.id.network_input_container);
		urlInput = findViewById(R.id.network_input);

		settings = AppSettings.get(getApplicationContext());
		NetworkAdapter adapter = new NetworkAdapter();

		selector.setAdapter(adapter);
		if (settings.getMode() == AppSettings.MODE_DEFAULT) {
			selector.setSelection(NetworkAdapter.INDEX_DEFAULT, false);
		} else if (settings.getMode() == AppSettings.MODE_CUSTOM) {
			selector.setSelection(NetworkAdapter.INDEX_CUSTOM, false);
			urlContainer.setVisibility(View.VISIBLE);
			urlInput.setText(settings.getDomain());
		}

		selector.setOnItemSelectedListener(this);
		confirmButton.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.network_confirm) {
			String url = urlInput.getText().toString();
			if (Patterns.WEB_URL.matcher(url).matches()) {
				if (url.endsWith("/"))
					url = url.substring(0, url.length() - 1);
				if (url.startsWith("http://"))
					url = "https://" + url.substring(7);
				else if (!url.startsWith("https://"))
					url = "https://" + url;
				settings.setDomain(url);
				settings.setMode(AppSettings.MODE_CUSTOM);
			} else {
				urlInput.setError(getString(R.string.error_wrong_url));
			}
		}
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (parent.getId() == R.id.network_selector) {
			if (position == NetworkAdapter.INDEX_DEFAULT) {
				urlContainer.setVisibility(View.INVISIBLE);
				settings.setMode(AppSettings.MODE_DEFAULT);
				settings.setDomain("");
			} else if (position == NetworkAdapter.INDEX_CUSTOM) {
				urlContainer.setVisibility(View.VISIBLE);
				urlInput.setText(settings.getDomain());
			}
		}
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}