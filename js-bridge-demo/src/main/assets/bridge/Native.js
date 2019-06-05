class Native {
    constructor(bridge, native) {
        this.bridge = bridge;
        this.native = native;
    }

    _invoke(action, payload, listener = null) {
        this.native.invoke(
            JSON.stringify(action),
            JSON.stringify(payload),
            JSON.stringify(listener)
        );
    }

    invokeCall(action, payload, callback) {
        let cbkName = this.bridge.pushWebCallback(callback);
        let listener = { name: cbkName };
        this._invoke(action, payload, listener);
    }

    invokeCallback(action, payload = null) {
        this._invoke(action, payload);
    }

    pushCallback(callback) {
        let action = {
            type: TYPE.CALLBACK,
            name: callback && callback.name
        };
        return payload => {
            this.invokeCallback(action, payload);
        };
    }
}
