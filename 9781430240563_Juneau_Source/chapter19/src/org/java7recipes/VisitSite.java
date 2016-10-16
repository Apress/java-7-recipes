package org.java7recipes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class VisitSite extends Activity {

	private WebView webview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		webview = new WebView(this);
		// Add progress bar
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		// Enable Javascript
		webview.getSettings().setJavaScriptEnabled(true);

		final Activity activity = this;
		webview.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				activity.setProgress(progress * 1000);
			}
		});
		webview.setWebViewClient(new WebViewClient() {
			// Handle errors accordingly
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(activity, "Error Received:" + description,
						Toast.LENGTH_SHORT).show();
			}
		});

		webview.loadUrl("http://www.java.net");
		setContentView(webview);
	}

}
