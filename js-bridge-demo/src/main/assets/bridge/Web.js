class Web {
    constructor(bridge) {
        this.bridge = bridge;
        this.callbacks = {};
        this.handler = () => {
            console.log("web handle");
        };
    }

    registerHandler(handler) {
        this.handler = handler;
    }

    invoke(action, payload = null, listener = null) {
        if (action[WEB.ACTION_TYPE] === WEB.TYPE_CALL) {
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
        let id = `${Date.now()}#${Math.floor(Math.random() * 1000)}`;
        this.callbacks[id] = callback;
        return id;
    }

    popCallback(action = {}) {
        let id = action[WEB.ACTION_NAME];
        let callback = this.callbacks[id];
        delete this.callbacks[id];
        return callback;
    }
}
