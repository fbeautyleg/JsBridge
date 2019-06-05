package io.github.xesam.web.bridge._native;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.github.xesam.web.bridge.Bridge;
import io.github.xesam.web.bridge._web.WebCallback;
import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Listener;
import io.github.xesam.web.bridge.core.Param;
import io.github.xesam.web.bridge.core.Response;

public class NativeHandlerDispatcher {

    private Bridge mBridge;
    private Map<String, NativeHandler> mNativeHandlers = new HashMap<>();
    private Map<String, NativeCallback> mCallbacks = new HashMap<>();

    public NativeHandlerDispatcher(Bridge bridge) {
        this.mBridge = bridge;
    }

    public void register(NativeHandler handler) {
        mNativeHandlers.put(handler.getName(), handler);
    }

    public Listener registerCallback(NativeCallback callback) {
        Listener listener = new Listener();
        listener.setName(mCallbacks.size() + "@" + new Date().getTime());
        mCallbacks.put(listener.getName(), callback);
        return listener;
    }

    public void dispatchCall(Action action, Param param, Listener listener) {
        NativeHandler handler = mNativeHandlers.get(action.getName());
        if (handler != null) {
            WebCallback webCallback = mBridge.pushRemoteCallback(listener);
            handler.handle(param, webCallback);
        }
    }

    public void dispatchCallback(Action action, Response response) {
        NativeCallback callback = mCallbacks.remove(action.getName());
        if (callback != null) {
            callback.call(response);
        }
    }
}
