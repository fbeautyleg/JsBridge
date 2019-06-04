package io.github.xesam.web.bridge._native;

import io.github.xesam.web.bridge._web.WebCallback;
import io.github.xesam.web.bridge.core.Param;

public interface NativeHandler {
    String getName();

    void handle(Param param, WebCallback callback);
}
