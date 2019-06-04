class Api {

    constructor(bridge) {
        this.bridge = bridge;
    }

    invoke(actionName, params) {
        return new Promise((resolve, reject) => {
            this.bridge.invoke(actionName, params, (data) => {
                if (data.err) {
                    reject(data.err);
                } else {
                    resolve(data.data);
                }
            });
        });
    }
}

