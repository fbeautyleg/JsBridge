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
    private NativeQueen mNativeQueen;

    public Native(Bridge bridge) {
        this.mBridge = bridge;
        this.mNativeQueen = new NativeQueen(mBridge);
        this.mNativeHandlerDispatcher = new NativeHandlerDispatcher(this.mBridge);
    }

    @Override
    public void destroy() {
        mNativeQueen.clear();
        mNativeHandlerDispatcher.clear();
    }

    public Listener pushCallback(NativeCallback callback) {
        return mNativeQueen.push(callback);
    }

    public void registerHandler(NativeHandler handler) {
        mNativeHandlerDispatcher.register(handler);
    }

    @JavascriptInterface
    public final void invoke(String actionInfo, String payloadInfo, String listenerInfo) {
        Action action = Action.parseNative(actionInfo);
        if (action.getType() == Protocol.Native.TYPE_CALLBACK) {
            Response response = Response.parseNative(payloadInfo);
            mNativeQueen.dispatch(action, response);
        } else {
            Param param = Param.parseNative(payloadInfo);
            Listener listener = Listener.parseNative(listenerInfo);
            mNativeHandlerDispatcher.dispatch(action, param, listener);
        }
    }
}
