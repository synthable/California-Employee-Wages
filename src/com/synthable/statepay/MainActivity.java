package com.synthable.statepay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

    private ArrayAdapter<CharSequence> mAdapter;
    private WebView mWebView;

    private String mContent;

    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        public String getContent() {
            return mContent;
        }
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mWebView = (WebView) findViewById(R.id.webkit);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setWebViewClient(new HelloWebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
        	public boolean onConsoleMessage(ConsoleMessage cm) {
        	    Log.d("MyApplication",
        	    	cm.message() + " -- From line "+
        	    	cm.lineNumber() + " of "+
        	    	cm.sourceId()
        	    );
        	    return true;
        	}
        });

        mWebView.loadUrl("file:///android_asset/process.html");
    }
}
