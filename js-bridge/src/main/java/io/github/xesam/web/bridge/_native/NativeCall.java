package io.github.xesam.web.bridge._native;

import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Protocol;

public class NativeCall extends Action {

    public NativeCall(Action action){
        this(action.getName(), action.getVersion());
    }

    public NativeCall(String name, String version) {
        super(Protocol.Native.TYPE_CALL, name, version);
    }

}
