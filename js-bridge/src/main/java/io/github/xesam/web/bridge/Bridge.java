package io.github.xesam.web.bridge;

import android.annotation.SuppressLint;
import android.util.Log;
import android.webkit.WebView;

import io.github.xesam.web.bridge._native.DefaultWebHost;
import io.github.xesam.web.bridge._native.Native;
import io.github.xesam.web.bridge._native.NativeCallback;
import io.github.xesam.web.bridge._native.NativeHandler;
import io.github.xesam.web.bridge._web.Web;
import io.github.xesam.web.bridge._web.WebCall;
import io.github.xesam.web.bridge._web.WebCallback;
import io.github.xesam.web.bridge.core.Listener;
import io.github.xesam.web.bridge.core.Param;
import io.github.xesam.web.bridge.core.Protocol;

public class Bridge {
    private WebView mWebView;
    private final String mNativeBridgeName;
    private final Native mNative;
    private final Web mWeb;

    public Bridge(WebView webView) {
        this(webView, Protocol.Native.NATIVE_BRIDGE_NAME, Protocol.Web.WEB_BRIDGE_NAME);
    }

    public Bridge(WebView webView, String nativeBridgeName, String webBridgeName) {
        this.mWebView = webView;
        mNativeBridgeName = nativeBridgeName;
        mNative = new Native(this);
        mWeb = new Web(this, new DefaultWebHost(mWebView, webBridgeName));
        this.injectNativeBridge(mWebView);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void injectNativeBridge(WebView webView) {
//        if (!webView.getSettings().getJavaScriptEnabled()) {
//            Log.e("JsBridge", "javascript is disabled");
//            return;
//        }
        webView.addJavascriptInterface(mNative, mNativeBridgeName);
    }

    public Listener pushLocalCallback(NativeCallback listener) {
        return mNative.pushCallback(listener);
    }

    public WebCallback pushRemoteCallback(Listener listener) {
        return mWeb.pushCallback(listener);
    }

    public void registerHandler(NativeHandler handler) {
        mNative.registerHandler(handler);
    }

    public void invoke(WebCall call, Param param, NativeCallback callback) {
        this.mWeb.invokeCall(call, param, callback);
    }

    public void destroy() {
        this.mWeb.destroy();
        this.mNative.destroy();
    }
}
