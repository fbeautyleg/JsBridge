package io.github.xesam.web.bridge._web;

import io.github.xesam.web.bridge.core.Action;
import io.github.xesam.web.bridge.core.Response;

public class WebCallback {

    private Web mWeb;
    private Action mAction;

    public WebCallback(Web web, Action action) {
        this.mWeb = web;
        this.mAction = action;
    }

    public void call(Response response) {
        mWeb.invokeCallback(mAction, response);
    }
}
