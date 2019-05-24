const Api = require('../Api');

class Sdk extends Api {

    constructor(bridge) {
        super(bridge);
    }

    getUser(params) {
        return this.send('default', 'getUser', params);
    }

    getLocation() {
        return this.send('default', 'getLocation');
    }

    getXXX() {
        return this.send('default', 'getXXX');
    }
}

module.exports = Sdk;
