const TYPE = require('./Type');

class Local {

    constructor(bridge) {
        this.bridge = bridge;
        this.callbacks = [];
        this.handler = () => {
            console.log('local handle');
        };
    }

    on(handler) {
        this.handler = handler;
    }

    invoke(action, payload = null, callback = null) {
        if (action.type === TYPE.CALL) {
            let innerCbk = this.bridge.pushRemoteCallback(callback);
            this.handler(action, payload, innerCbk);
        } else {
            let callback = this.popCallback(action);
            if (callback) {
                callback(payload);
            } else {
                console.error('local not found');
            }
        }
    }

    pushCallback(callback) {
        this.callbacks.push(callback);
        return this.callbacks.length - 1;
    }

    popCallback(action = {}) {
        let callback = this.callbacks[action.name];
        delete this.callbacks[action.name];
        return callback;
    }
}

module.exports = Local;
