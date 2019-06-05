class Bridge {
    static get _VERSION() {
        return {
            _version: "0.0.1"
        };
    }

    static get _NAME() {
        return "_WEB_BRIDGE";
    }

    constructor(native, host, name = Bridge._NAME) {
        this.web = new Web(this);
        this.native = new Native(this, native);
        host[name] = (action, payload = null, callback = null) => {
            this.web.invoke(action, payload, callback);
        };
    }

    on(handler) {
        this.web.on(handler);
    }

    pushWebCallback(callback) {
        return this.web.pushCallback(callback);
    }

    pushNativeCallback(callback) {
        return this.native.pushCallback(callback);
    }

    invoke(actionName, payload = null, listener) {
        let action = {
            ...Bridge._VERSION,
            name: actionName,
            type: TYPE.CALL
        };
        this.native.invokeCall(action, payload, listener);
    }
}
