class Bridge {
    constructor(native, host, name = WEB.BRIDGE_NAME) {
        this.web = new Web(this);
        this.native = new Native(this, native);
        host[name] = (action, payload = null, callback = null) => {
            this.web.invoke(action, payload, callback);
        };
    }

    registerHandler(handler) {
        this.web.registerHandler(handler);
    }

    pushWebCallback(callback) {
        return this.web.pushCallback(callback);
    }

    pushNativeCallback(listener) {
        return this.native.pushCallback(listener);
    }

    invoke(actionName, payload = null, webCallback) {
        let action = {
            [NATIVE.BRIDGE_VERSION]: VERSION,
            [NATIVE.ACTION_NAME]: actionName,
            [NATIVE.ACTION_TYPE]: NATIVE.TYPE_CALL
        };
        this.native.invokeCall(action, payload, webCallback);
    }
}
