class Bridge {
    static get _VERSION() {
        return {
            _bridge_version: "0.0.1"
        };
    };

    static get _NAME() {
        return '_WEB_BRIDGE';
    };

    constructor(native, host, name = Bridge._NAME) {
        this.local = new Local(this);
        this.remote = new Remote(this, native);
        host[name] = (action, payload = null, callback = null) => {
            this.local.invoke(action, payload, callback);
        }
    }

    on(handler) {
        this.local.on(handler);
    }

    pushLocalCallback(callback) {
        return this.local.pushCallback(callback);
    }

    pushRemoteCallback(callback) {
        return this.remote.pushCallback(callback);
    }

    invoke(actionName, payload = null, listener) {
        let action = {
            ...Bridge._VERSION,
            name: actionName,
            type: TYPE.CALL
        };
        this.remote.invoke(action, payload, listener);
    }
}
