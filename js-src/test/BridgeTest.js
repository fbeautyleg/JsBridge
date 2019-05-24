const Host = require('./Host');
const Bridge = require('../Bridge');

const bridge = new Bridge(Host.window.BridgeInterface);
bridge.bind(global);

let cbk = (err, data) => {
    console.log(`err= ${JSON.stringify(err)}, data = ${JSON.stringify(data)}`);
};

bridge.send('default', 'getUser', {id: 12345678}, cbk);
bridge.send('default', 'getLocation', null, cbk);
bridge.send('default', 'getXXX', null, cbk);
