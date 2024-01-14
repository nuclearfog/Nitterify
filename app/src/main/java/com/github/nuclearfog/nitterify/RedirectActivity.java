package com.github.nuclearfog.nitterify;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Activity used to redirect Twitter/X links
 *
 * @author nuclearfog
 */
public class RedirectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppSettings settings = AppSettings.get(getApplicationContext());

		if (getIntent().getData() != null) {
			String url;
			if (settings.getMode() == AppSettings.MODE_CUSTOM) {
				url = settings.getDomain() + getIntent().getData().getPath();
			} else {
				url = "https://nitter.net" + getIntent().getData().getPath();
			}
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setData(Uri.parse(url));
			try {
				getApplicationContext().startActivity(intent);
			} catch (ActivityNotFoundException exception) {
				Toast.makeText(getApplicationContext(), R.string.error_open_link, Toast.LENGTH_SHORT).show();
			}
		}
		finish();
	}
}