package io.github.xesam.web.bridge._native;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.github.xesam.web.bridge.Bridge;
import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Listener;
import io.github.xesam.web.bridge.core.Response;

public class NativeQueen {

    private Bridge mBridge;
    private Map<String, NativeCallback> mCallbacks = new HashMap<>();

    public NativeQueen(Bridge bridge) {
        this.mBridge = bridge;
    }

    public Listener push(NativeCallback callback) {
        Listener listener = new Listener();
        listener.setName(mCallbacks.size() + "@" + new Date().getTime());
        mCallbacks.put(listener.getName(), callback);
        return listener;
    }

    public void dispatch(Action action, Response response) {
        NativeCallback callback = mCallbacks.remove(action.getName());
        if (callback != null) {
            callback.call(response);
        }
    }

    public void clear(){
        mCallbacks.clear();
    }
}
