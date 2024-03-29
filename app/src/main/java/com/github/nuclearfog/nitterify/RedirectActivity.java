package com.github.nuclearfog.nitterify;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Activity used to redirect to nitter instance
 *
 * @author nuclearfog
 */
public class RedirectActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppSettings settings = AppSettings.get(getApplicationContext());
		// fetch url to redirect
		if (getIntent().getData() != null) {
			Uri redirectUrl;
			String path = getIntent().getData().getPath();
			if (settings.getMode() == AppSettings.MODE_CUSTOM) {
				redirectUrl = Uri.parse(settings.getDomain() + path);
				if (redirectUrl.getScheme() == null)   {
					redirectUrl = Uri.parse("https://" + settings.getDomain() + path);
				}
			} else {
				redirectUrl = Uri.parse("https://nitter.net" + path);
			}
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setData(redirectUrl);
			try {
				getApplicationContext().startActivity(intent);
			} catch (ActivityNotFoundException exception) {
				Toast.makeText(getApplicationContext(), R.string.error_open_link, Toast.LENGTH_SHORT).show();
			}
		}
		finish();
	}
}