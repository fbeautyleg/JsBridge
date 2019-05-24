const Host = require('./Host');
const Bridge = require('../Bridge');
const Sdk = require('./Sdk');

const bridge = new Bridge(Host.window.BridgeInterface);
bridge.bind(global);

let sdk = new Sdk(bridge);

sdk.getUser({id: 12345678}).then(data => {
    console.log(data);
}).catch(err => {
    console.error(err);
});

sdk.getLocation().then(data => {
    console.log(data);
}).catch(err => {
    console.error(err);
});

sdk.getXXX().then(data => {
    console.log(data);
}).catch(err => {
    console.error(err);
});
