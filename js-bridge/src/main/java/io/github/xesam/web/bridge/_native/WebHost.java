package io.github.xesam.web.bridge._native;

import io.github.xesam.web.bridge.core.Marshallable;

public interface WebHost {
    void evaluateJavascript(Marshallable action, Marshallable payload, Marshallable listener);
}
