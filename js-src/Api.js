class Api {

    constructor(bridge) {
        this.bridge = bridge;
    }

    send(service_name, service_action, params) {
        return new Promise((resolve, reject) => {
            this.bridge.send(service_name, service_action, params, (err, data) => {
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
