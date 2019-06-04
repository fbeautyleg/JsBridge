package io.github.xesam.web.bridge._native;

import android.webkit.JavascriptInterface;

import io.github.xesam.web.bridge.Bridge;
import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Invoker;
import io.github.xesam.web.bridge.core.Listener;
import io.github.xesam.web.bridge.core.Param;
import io.github.xesam.web.bridge.core.Protocol;
import io.github.xesam.web.bridge.core.Response;

public class Native implements Invoker {
    private Bridge mBridge;

    private NativeHandlerDispatcher mNativeHandlerDispatcher;

    public Native(Bridge bridge) {
        this.mBridge = bridge;
        this.mNativeHandlerDispatcher = new NativeHandlerDispatcher(this.mBridge);
    }

    @Override
    public void destroy() {

    }

    public Listener pushCallback(NativeCallback listener) {
        return mNativeHandlerDispatcher.registerCallback(listener);
    }

    public void registerHandler(NativeHandler handler) {
        mNativeHandlerDispatcher.register(handler);
    }

    @JavascriptInterface
    public final void invoke(String actionInfo, String payloadInfo, String listenerInfo) {
        Action action = Action.parse(actionInfo);
        Listener listener = Listener.parse(listenerInfo);
        if (action.getType() == Protocol.Web.TYPE_CALLBACK) {
            Response response = Response.parse(payloadInfo);
            mNativeHandlerDispatcher.dispatchCallback(action, response);
        } else {
            Param param = Param.parse(payloadInfo);
            mNativeHandlerDispatcher.dispatchCall(action, param, listener);
        }
    }
}
