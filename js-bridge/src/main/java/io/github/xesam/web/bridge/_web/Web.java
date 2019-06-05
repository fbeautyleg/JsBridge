package io.github.xesam.web.bridge._web;

import io.github.xesam.web.bridge.Bridge;
import io.github.xesam.web.bridge._native.NativeCallback;
import io.github.xesam.web.bridge._native.WebHost;
import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Invoker;
import io.github.xesam.web.bridge.core.Listener;
import io.github.xesam.web.bridge.core.Param;
import io.github.xesam.web.bridge.core.Protocol;
import io.github.xesam.web.bridge.core.Response;

public class Web implements Invoker {

    private Bridge mBridge;
    private WebHost mWebHost;

    public Web(Bridge bridge, WebHost host) {
        mBridge = bridge;
        mWebHost = host;
    }

    private Action getWebAction(Listener listener) {
        return new Action(Protocol.Web.TYPE_CALLBACK, listener.getName(), Protocol.VERSION);
    }

    public WebCallback pushCallback(Listener listener) {
        return new WebCallback(this, getWebAction(listener));
    }

    @Override
    public void destroy() {

    }

    void invokeCallback(Action action, Response response) {
        mWebHost.evaluateJavascript(action, response, null);
    }

    public void invokeCall(Action action, Param param, NativeCallback callback) {
        Listener nativeListener = mBridge.pushNativeCallback(callback);
        mWebHost.evaluateJavascript(action, param, nativeListener);
    }
}
