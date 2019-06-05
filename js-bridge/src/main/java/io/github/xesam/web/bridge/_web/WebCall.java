package io.github.xesam.web.bridge._web;

import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Protocol;

public class WebCall extends Action {

    public WebCall(String name) {
        super(Protocol.Web.TYPE_CALL, name, Protocol.VERSION);
    }

}
