package io.github.xesam.web.bridge._native;

import java.util.HashMap;
import java.util.Map;

import io.github.xesam.web.bridge.Bridge;
import io.github.xesam.web.bridge._web.WebCallback;
import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Listener;
import io.github.xesam.web.bridge.core.Param;

class NativeHandlerDispatcher {

    private Bridge mBridge;
    private Map<String, NativeHandler> mNativeHandlers = new HashMap<>();

    public NativeHandlerDispatcher(Bridge bridge) {
        this.mBridge = bridge;
    }

    public void register(NativeHandler handler) {
        mNativeHandlers.put(handler.getName(), handler);
    }

    public void dispatch(Action action, Param param, Listener listener) {
        NativeHandler handler = mNativeHandlers.get(action.getName());
        if (handler != null) {
            WebCallback webCallback = mBridge.pushWebCallback(listener);
            handler.handle(param, webCallback);
        }
    }

    public void clear() {
        mNativeHandlers.clear();
    }
}
