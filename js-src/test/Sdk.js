const Api = require('../src/Api');

class Sdk extends Api {

    constructor(bridge) {
        super(bridge);
    }

    getUser(params) {
        return this.invoke('getUser', params);
    }

    getLocation() {
        return this.invoke('getLocation');
    }

    getXXX() {
        return this.invoke('getXXX');
    }
}

module.exports = Sdk;
