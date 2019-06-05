class Web {
    constructor(bridge) {
        this.bridge = bridge;
        this.callbacks = [];
        this.handler = () => {
            console.log("web handle");
        };
    }

    registerHandler(handler) {
        this.handler = handler;
    }

    invoke(action, payload = null, listener = null) {
        if (action.type === TYPE.CALL) {
            let nativeCallback = this.bridge.pushNativeCallback(listener);
            this.handler(action, payload, nativeCallback);
        } else {
            let callback = this.popCallback(action);
            if (callback) {
                callback(payload);
            } else {
                console.error("local not found");
            }
        }
    }

    pushCallback(callback) {
        this.callbacks.push(callback);
        return `${this.callbacks.length - 1}`;
    }

    popCallback(action = {}) {
        let callback = this.callbacks[action.name];
        delete this.callbacks[action.name];
        return callback;
    }
}
