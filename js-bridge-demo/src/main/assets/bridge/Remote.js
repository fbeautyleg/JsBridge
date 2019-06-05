class Remote {
    constructor(bridge, native) {
        this.bridge = bridge;
        this.native = native;
    }

    invoke(action, payload = null, callback = null) {
        let cbk = null;
        if (action.type === TYPE.CALL) {
            let cbkName = this.bridge.pushLocalCallback(callback);
            cbk = { name: cbkName };
        }

        console.log(action, payload, cbk);
        this.native.invoke(
            JSON.stringify(action),
            JSON.stringify(payload),
            JSON.stringify(cbk)
        );
    }

    pushCallback(callback) {
        let action = {
            type: TYPE.CALLBACK,
            name: callback && callback.name
        };
        return payload => {
            this.invoke(action, payload);
        };
    }
}
