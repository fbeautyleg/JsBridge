class Api {

    constructor(bridge) {
        this.bridge = bridge;
    }

    send(serviceName, serviceAction, params) {
        return new Promise((resolve, reject) => {
            this.bridge.send(serviceName, serviceAction, params, (err, data) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(data);
                }
            });
        });
    }
}

module.exports = Api;
