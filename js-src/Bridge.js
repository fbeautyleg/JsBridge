class Bridge {
    static get _VERSION() {
        return {
            _protocol_version: '0.0.1'
        };
    };

    static get _NAME() {
        return 'Bridge';
    };

    constructor(bridgeInterface) {
        this.bridge = bridgeInterface;
        this.callbacks = [];
    }

    addCallback(callback) {
        this.callbacks.push(callback);
        return this.callbacks.length - 1;
    }

    send(service_name, service_action, params = null, callback) {
        let service = {
            ...Bridge._VERSION,
            name: service_name,
            action: service_action,
        };
        let callbackId = this.addCallback(callback);
        this.bridge.send(JSON.stringify(service), JSON.stringify(params), JSON.stringify({id: callbackId}));
    }

    receive(cbk, err, data) {
        let callback = this.callbacks[cbk.id];
        delete this.callbacks[cbk.id];
        if (callback) {
            callback(err, data);
        } else {
            console.error('not found');
        }
    }

    bind(global, name = Bridge._NAME) {
        global[name] = this;
    }
}

module.exports = Bridge;
