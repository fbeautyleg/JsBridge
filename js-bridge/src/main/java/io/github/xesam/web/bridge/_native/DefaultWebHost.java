package io.github.xesam.web.bridge._native;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import java.util.Locale;

import io.github.xesam.web.bridge.core.Marshallable;

public final class DefaultWebHost implements WebHost {

    private WebView mWebView;
    private String mBridgeName;

    public DefaultWebHost(WebView Webview, String bridgeName) {
        this.mWebView = Webview;
        this.mBridgeName = bridgeName;
    }

    private void evaluateJavascript(String action, String payload, String callback) {
        String script = String.format(Locale.getDefault(), "window.%s(%s, %s, %s)", this.mBridgeName, action, payload, callback);
        evaluateJavascript(script);
    }

    private void evaluateJavascript(String script) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.evaluateJavascript(script, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {

                }
            });
        } else {
            mWebView.loadUrl("javascript:" + script);
        }
    }

    @Override
    public void evaluateJavascript(Marshallable action, Marshallable payload, Marshallable listener) {
        this.evaluateJavascript(action.marshalling(), payload.marshalling(), listener == null ? null : listener.marshalling());
    }
}
